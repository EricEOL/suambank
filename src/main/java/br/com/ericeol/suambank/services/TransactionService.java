package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.transaction.Transaction;
import br.com.ericeol.suambank.entities.transaction.TransactionStatus;
import br.com.ericeol.suambank.entities.transaction.TransactionsType;
import br.com.ericeol.suambank.entities.TransfersType;
import br.com.ericeol.suambank.entities.forms.DepositTransactionForm;
import br.com.ericeol.suambank.entities.forms.TransferTransactionForm;
import br.com.ericeol.suambank.repositories.AccountRepository;
import br.com.ericeol.suambank.repositories.TransactionRepository;
import br.com.ericeol.suambank.utils.RealFormatNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Service
public class TransactionService {

    @Autowired
    AccountRepository repository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    EmailService emailService;

    @Transactional
    public TransactionDTO deposit(DepositTransactionForm depositTransactionForm) {
        try {
            Account account = repository.findByAgencyAndAccountNumber(depositTransactionForm.getAgencyNumber(), depositTransactionForm.getAccountNumber());
            account.deposit(depositTransactionForm.getValue());

            Transaction transaction = new Transaction(
                    account,
                    account.getAgencyNumber(),
                    account.getAccountNumber(),
                    depositTransactionForm.getValue(),
                    TransactionsType.DEPOSIT,
                    TransactionStatus.RECEBIDO
            );

            transactionRepository.save(transaction);

            return new TransactionDTO(transaction);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public TransactionDTO withdraw(Long accountId, Double value) {
        try {
            Account account = repository.findById(accountId).get();
            account.withdraw(value);

            Transaction transaction = new Transaction(
                    account,
                    account.getAgencyNumber(),
                    account.getAccountNumber(),
                    value,
                    TransactionsType.WITHDRAW,
                    TransactionStatus.RETIRADO
            );

            transactionRepository.save(transaction);

            return new TransactionDTO(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public TransactionDTO transfer(TransferTransactionForm transferTransactionForm) {
        try {
            Account senderAccount = repository.getById(transferTransactionForm.getAccountId());

            Boolean passwordMatches = new BCryptPasswordEncoder().matches(transferTransactionForm.getPassword(),senderAccount.getClient().getPassword());

            if(!passwordMatches)
                throw new RuntimeException("Essa senha não confere");

            Account destinationAccount = repository.findByAgencyAndAccountNumber(transferTransactionForm.getDestinationAgencyNumber(), transferTransactionForm.getDestinationAccountNumber());

            senderAccount.transfer(destinationAccount, transferTransactionForm.getValue(), TransfersType.valueOf(transferTransactionForm.getTransfersType()));

            Transaction transactionSending = new Transaction(
                    senderAccount,
                    destinationAccount.getAgencyNumber(),
                    destinationAccount.getAccountNumber(),
                    transferTransactionForm.getValue(),
                    TransactionsType.TRANSFER,
                    TransactionStatus.ENVIADO
            );

            Transaction transactionReceived = new Transaction(
                    destinationAccount,
                    destinationAccount.getAgencyNumber(),
                    destinationAccount.getAccountNumber(),
                    transferTransactionForm.getValue(),
                    TransactionsType.TRANSFER,
                    TransactionStatus.RECEBIDO
            );

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transactionSending);
            transactions.add(transactionReceived);

            transactionRepository.saveAll(transactions);

            try {
                emailService.sendEmail(
                        senderAccount.getClient().getEmail(),
                        destinationAccount.getClient().getEmail(),
                        "Transação no valor de " + RealFormatNumber.format(transferTransactionForm.getValue()) + " efetuada com sucesso.");
            } catch (Exception e) {
                System.out.println(e);
            }

            return new TransactionDTO(transactionSending);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
