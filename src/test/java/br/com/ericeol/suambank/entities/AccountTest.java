package br.com.ericeol.suambank.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountTest {

    public Bank bank = new Bank("001", "unibank");
    public Client client = new Client("11111111111", "Rebecca Lopes");
    public Account account;

    @Test
    void shouldCreateANewAccount() {
        this.account = bank.newCheckingAccount(client);

        System.out.println("Agência: " + account.getAgencyNumber());
        System.out.println("Conta: " + account.getAccountNumber());

        Assertions.assertEquals("0001", account.getAgencyNumber());
        Assertions.assertEquals("0001", account.getAccountNumber());
    }

    @Test
    void shouldMakeADeposit() {
        this.account = bank.newCheckingAccount(client);

        String retorno = account.deposit(3000d);

        System.out.println(retorno);
        System.out.println("Balance: " + account.getBalance());

        Assertions.assertEquals(3000d, account.getBalance());
    }

    @Test
    void shouldMakeAWithdraw() {
        this.account = bank.newCheckingAccount(client);

        account.deposit(2000d);

        String retorno = account.withdraw(1000d);

        System.out.println(retorno);
        System.out.println("Balance: " + account.getBalance());

        Assertions.assertEquals(1000d, account.getBalance());
    }

    @Test
    void shouldMakeTransferBetweenAccountWithPIX() {
        this.account = bank.newCheckingAccount(client);
        Account account2 = bank.newSavingsAccount(client);

        account.deposit(3000d);
        account2.deposit(5500d);

        account.transfer(account2, 2500d, TransfersType.PIX);

        System.out.println("Balance - Conta1: " + account.getBalance());
        System.out.println("Balance - Conta2: " + account2.getBalance());

        Assertions.assertEquals(500d, account.getBalance());
        Assertions.assertEquals(8000d, account2.getBalance());
    }

    @Test
    void shouldMakeTransferBetweenAccountWithTED() {
        this.account = bank.newCheckingAccount(client);
        Account account2 = bank.newSavingsAccount(client);

        account.deposit(3000d);
        account2.deposit(5500d);

        account.transfer(account2, 2500d, TransfersType.TED);

        System.out.println("Balance - Conta1: " + account.getBalance());
        System.out.println("Balance - Conta2: " + account2.getBalance());

        Assertions.assertEquals(485d, account.getBalance());
        Assertions.assertEquals(8000d, account2.getBalance());
    }

    @Test
    void shouldMakeTransferBetweenAccountWithDOC() {
        this.account = bank.newCheckingAccount(client);
        Account account2 = bank.newSavingsAccount(client);

        account.deposit(3000d);
        account2.deposit(5500d);

        account.transfer(account2, 2500d, TransfersType.DOC);

        System.out.println("Balance - Conta1: " + account.getBalance());
        System.out.println("Balance - Conta2: " + account2.getBalance());

        Assertions.assertEquals(490d, account.getBalance());
        Assertions.assertEquals(8000d, account2.getBalance());
    }
}
