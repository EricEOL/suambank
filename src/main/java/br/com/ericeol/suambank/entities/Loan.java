package br.com.ericeol.suambank.entities;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;

public class Loan {

    private final Account account;
    private final Double initialValue;
    private Double currentValue;
    private final Double valueInstallments;
    private final int installments;
    private int installmentsPaid;
    private GregorianCalendar nextPaymentDate;

    public Loan(Account account, Double initialValue, int installments) {
        this.account = account;
        this.initialValue = initialValue;

        DecimalFormat dformat = new DecimalFormat("0.00");

        this.currentValue = Double.parseDouble(dformat.format(initialValue * this.calculateRate(installments)));
        this.valueInstallments = Double.parseDouble(dformat.format((initialValue * this.calculateRate(installments))/installments));

        this.installments = installments;

        GregorianCalendar paymentDate = new GregorianCalendar();
        paymentDate.add(GregorianCalendar.MONTH, 1);

        this.nextPaymentDate = paymentDate;
    }

    public String payInstallment() {
        if(installmentsPaid < installments) {
            try {
                this.account.withdraw(valueInstallments);
            } catch (Exception e) {
                System.out.println("Não é possível realizar o pagamento da parcela porque não há saldo suficente na conta");
                return e.getMessage();
            }

            currentValue -= valueInstallments;
            installmentsPaid += 1;

            this.updateNextPaymentDate();
            this.getAccount().getBank().verifyLoan(this);
            return "A parcela nº " + installmentsPaid + " foi paga. Faltam " + (installments - installmentsPaid) + " installments para quitação";
        } else {
            return "Não há nenhuma parcela a ser paga nesse EMPRÉSTIMO, já está quitado";
        }
    }

    public void updateNextPaymentDate() {
        nextPaymentDate.add(GregorianCalendar.MONTH, 1);
    }

    public String paymentDateFormated() {
        String paymentDate =
                nextPaymentDate.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                nextPaymentDate.get(GregorianCalendar.MONTH) + "/" +
                nextPaymentDate.get(GregorianCalendar.YEAR);

        return paymentDate;
    }

    public Account getAccount() {
        return account;
    }

    public Double calculateRate(int installments) {
        if(installments < 15) return 1.10;
        return 1.14;
    }

    public Double getInitialValue() {
        return initialValue;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public Double getValueInstallments() {
        return valueInstallments;
    }

    public int getInstallments() {
        return installments;
    }

    public int getInstallmentsPaid() {
        return installmentsPaid;
    }

    public GregorianCalendar getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(GregorianCalendar paymentDate) {
        this.nextPaymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "account=" + account +
                ", initialValue=" + initialValue +
                ", currentValue=" + currentValue +
                ", valueInstallments=" + valueInstallments +
                ", installments=" + installments +
                ", installmentsPaid=" + installmentsPaid +
                ", nextPaymentDate=" + this.paymentDateFormated() +
                '}';
    }
}
