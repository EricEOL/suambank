package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.Transaction;
import br.com.ericeol.suambank.entities.TransactionsType;
import br.com.ericeol.suambank.entities.TransfersType;
import br.com.ericeol.suambank.entities.forms.DepositTransactionForm;
import br.com.ericeol.suambank.entities.forms.TransferTransactionForm;
import br.com.ericeol.suambank.repositories.AccountRepository;
import br.com.ericeol.suambank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    AccountRepository repository;

    @Autowired
    TransactionRepository transactionRepository;

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
                    TransactionsType.DEPOSIT
            );

            transactionRepository.save(transaction);

            return new TransactionDTO(
                    depositTransactionForm.getAgencyNumber(),
                    depositTransactionForm.getAccountNumber(),
                    depositTransactionForm.getValue(),
                    TransactionsType.DEPOSIT,
                    "sucess"
            );

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
                    TransactionsType.WITHDRAW
            );

            transactionRepository.save(transaction);

            return new TransactionDTO(account, value, TransactionsType.WITHDRAW, "success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public TransactionDTO transfer(TransferTransactionForm transferTransactionForm) {
        try {
            Account senderAccount = repository.getById(transferTransactionForm.getAccountId());
            Account destinationAccount = repository.findByAgencyAndAccountNumber(transferTransactionForm.getDestinationAgencyNumber(), transferTransactionForm.getDestinationAccountNumber());

            senderAccount.transfer(destinationAccount, transferTransactionForm.getValue(), TransfersType.valueOf(transferTransactionForm.getTransfersType()));

            Transaction transaction = new Transaction(
                    senderAccount,
                    destinationAccount.getAgencyNumber(),
                    destinationAccount.getAccountNumber(),
                    transferTransactionForm.getValue(),
                    TransactionsType.TRANSFER
            );

            transactionRepository.save(transaction);

            return new TransactionDTO(
                    transferTransactionForm.getDestinationAgencyNumber(),
                    transferTransactionForm.getDestinationAccountNumber(),
                    transferTransactionForm.getValue(),
                    TransactionsType.TRANSFER,
                    "success"
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
