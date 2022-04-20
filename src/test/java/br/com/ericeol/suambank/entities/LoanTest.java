package br.com.ericeol.suambank.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

public class LoanTest {

    public Bank bank = new Bank("001", "unibank");
    public Client client = new Client("11111111111", "Rebecca Lopes");
    public Account account;

    @Test
    void shouldMakeANewLoan() {

        this.account = bank.newCheckingAccount(client);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);

        System.out.println(loan.toString());

        Assertions.assertEquals(10000d, loan.getInitialValue());
    }

    @Test
    void shouldMakeLoanIfBankAccount() {
        this.account = bank.newCheckingAccount(client);
        Loan loan = bank.takeOutLoan(account, 10000d, 15);

        Assertions.assertTrue(bank.getLoans().contains(loan));

        Bank bank2 = new Bank("002", "otherbank");
        Account account2 = new CheckingAccount(bank2, client);

        Assertions.assertThrows(RuntimeException.class, () -> bank.takeOutLoan(account2, 15000d, 12));
    }

    @Test
    void loanShouldBeOnBankLoanList() {
        this.account = bank.newCheckingAccount(client);
        Loan loan = bank.takeOutLoan(account, 10000d, 15);

        Assertions.assertTrue(bank.getLoans().contains(loan));
    }

    @Test
    void requestedAmountShouldBeInTheAccountBalance() {
        this.account = bank.newCheckingAccount(client);
        System.out.println("Balance na conta ANTERIOR ao empréstimo: " + this.account.getBalance());

        Loan loan = bank.takeOutLoan(account, 10000d, 15);

        System.out.println("Balance na conta APÓS empréstimo: " + this.account.getBalance());
        Assertions.assertEquals(10000d, this.account.getBalance());
    }

    @Test
    void shouldBePossiblePayAInstallmentOfLoan() {
        this.account = bank.newCheckingAccount(client);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);
        System.out.println("Balance na conta APÓS o empréstimo: " + this.account.getBalance());

        loan.payInstallment();
        System.out.println("Valor da parcela: " + loan.getValueInstallments());
        System.out.println("Balance na conta PAGANDO UMA PARCELA do empréstimo: " + this.account.getBalance());

        Assertions.assertEquals(9240d, this.account.getBalance());
    }

    @Test
    void shouldHaveIncreasedTheAmountOfInstallmentsPaid() {
        this.account = bank.newCheckingAccount(client);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);
        System.out.println("Parcelas pagas do empréstimo ANTES do pagamento: " + loan.getInstallmentsPaid());

        loan.payInstallment();

        System.out.println("Parcelas pagas do empréstimo APÓS do pagamento: " + loan.getInstallmentsPaid());

        Assertions.assertEquals(1, loan.getInstallmentsPaid());
    }

    @Test
    void shouldHaveDecreasedTheCurrentValueOfLoan() {
        this.account = bank.newCheckingAccount(client);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);
        System.out.println("Valor corrente do empréstimo ANTES do pagamento: " + loan.getCurrentValue());

        loan.payInstallment();

        System.out.println("Valor corrente do empréstimo APÓS do pagamento: " + loan.getCurrentValue());

        Assertions.assertEquals(10640d, loan.getCurrentValue());
    }

    @Test
    void shouldHaveChangedTheDateOfNextPayment() {
        this.account = bank.newCheckingAccount(client);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);

        GregorianCalendar gc = new GregorianCalendar(2022, 4, 1);
        loan.setNextPaymentDate(gc);

        System.out.println("Data do pagamento ANTES do 1º pagamento: " + loan.paymentDateFormated());

        loan.payInstallment();

        System.out.println("Data do pagamento APÓS o 1º pagamento: " + loan.paymentDateFormated());

        Assertions.assertEquals("1/5/2022", loan.paymentDateFormated());
    }

    @Test
    void shouldNotBePossibleToPayAnInstallmentWithoutSufficientBalanceInAccount() {
        this.account = bank.newCheckingAccount(client);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);
        account.withdraw(9800d);

        Assertions.assertEquals("Não é possível realizar o saque desse valor", loan.payInstallment());
    }

    @Test
    void shouldNotBePossibleToPayAFullyPaidLoan() {
        this.account = bank.newCheckingAccount(client);
        account.deposit(5000d);

        Loan loan = bank.takeOutLoan(account, 10000d, 15);

        for(int i = 1; i <= loan.getInstallments(); i++) {
            loan.payInstallment();
            System.out.println(i + "- " + account.getBalance());
        }

        System.out.println("16ª Tentativa (pagamento) - " + loan.payInstallment());
        System.out.println("16ª Tentativa (saldo na conta) - " + account.getBalance());

        System.out.println("Parcelas pagas: " + loan.getInstallmentsPaid() + " | Parcelas totais: " + loan.getInstallments());

        Assertions.assertEquals("Não há nenhuma parcela a ser paga nesse EMPRÉSTIMO, já está quitado", loan.payInstallment());

    }
}
