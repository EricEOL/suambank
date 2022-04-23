package br.com.ericeol.suambank.services;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.DTO.TransactionDTO;
import br.com.ericeol.suambank.entities.TransactionsType;
import br.com.ericeol.suambank.entities.TransfersType;
import br.com.ericeol.suambank.entities.forms.DepositTransactionForm;
import br.com.ericeol.suambank.entities.forms.TransferTransactionForm;
import br.com.ericeol.suambank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    AccountRepository repository;

    @Transactional
    public TransactionDTO deposit(DepositTransactionForm depositTransactionForm) {
        try {
            Account account = repository.findByAgencyAndAccountNumber(depositTransactionForm.getAgencyNumber(), depositTransactionForm.getAccountNumber());

            account.deposit(depositTransactionForm.getValue());

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
