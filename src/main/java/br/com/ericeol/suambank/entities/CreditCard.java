package br.com.ericeol.suambank.entities;

import java.util.GregorianCalendar;
import java.util.List;

public class CreditCard {

    private Long number;
    private CheckingAccount checkingAccount;
    private GregorianCalendar expiring;
    private int verificationCode;
    private Double limit;
    //private List<Bill> bills;
    private Boolean compliant = true;
    private GregorianCalendar paymentDate;
    private Double defaultRate = 0.15;

    public CreditCard(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }
}
