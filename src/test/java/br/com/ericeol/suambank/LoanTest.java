package br.com.ericeol.suambank;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.entities.Account.AccountType;
import br.com.ericeol.suambank.entities.Bank.Bank;
import br.com.ericeol.suambank.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
/*

@SpringBootTest
public class LoanTest {

    @Test
    void shouldNotToBePossibleTakeOutLoanWithValueGreaterThan30000() {
        Bank bank = new Bank("001", "SUAMBANK");
        Client client = new Client("111.111.111-11", "Eric", "Padasdas223ca");
        Account account = new Account(bank, client, AccountType.CHECKING, 1L, 1234L);

        List<Account> accounts = Arrays.asList(account);
        bank.setAccounts(accounts);

        Assertions.assertThrows(RuntimeException.class, () -> bank.takeOutLoan(account, 30001d, 12));
    }

    @Test
    void shouldNotToBePossibleTakeOutLoanWithInstallmentsGreaterThan24() {
        Bank bank = new Bank("001", "SUAMBANK");
        Client client = new Client("111.111.111-11", "Eric", "Padasdas223ca");
        Account account = new Account(bank, client, AccountType.CHECKING, 1L, 1234L);

        List<Account> accounts = Arrays.asList(account);
        bank.setAccounts(accounts);

        Assertions.assertThrows(RuntimeException.class, () -> bank.takeOutLoan(account, 30000d, 25));
    }
}

 */
