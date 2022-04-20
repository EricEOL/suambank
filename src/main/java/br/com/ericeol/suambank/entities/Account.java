package br.com.ericeol.suambank.entities;

public abstract class Account {

    private int id;
    private Bank bank;
    private Double balance = 0d;
    private String agencyNumber = "0001";
    private String accountNumber;
    private Client client;

    public Account(Bank bank, Client client) {
        int nextAccount = bank.getAccounts().size() + 1;

        this.id = nextAccount;
        this.bank = bank;
        this.accountNumber =  "000" + nextAccount;
        this.client = client;
    }

    protected String deposit(Double value) {
        if(value <= 0) return "Deposite um value válido";

        balance += value;
        return "DEPÓSITO no value de " + value + " realizado com sucesso";
    }

    protected String withdraw(Double value) {
        if(value > this.balance || value <= 0) throw new RuntimeException("Não é possível realizar o saque desse valor");

        balance -= value;
        return "SAQUE no value de " + value + " realizado com sucesso";
    }

    protected String transfer(Account destinyAccount, Double value, TransfersType tipo ) {
        double valueComTaxa = value;

        if(tipo.equals(TransfersType.TED)) valueComTaxa = value + 15d;
        if(tipo.equals(TransfersType.DOC)) valueComTaxa = value + 10d;

        if(valueComTaxa > this.balance || value <= 0) return "Não é possível realizar uma transferência com esse value";

        this.withdraw(valueComTaxa);
        destinyAccount.deposit(value);

        return "TRANSFERÊNCIA no value de " + value + " para a Conta de " + destinyAccount.getClient().getName() + " realizada com sucesso";
    }

    protected int getId() {
        return id;
    }

    protected Bank getBank() {
        return bank;
    }

    protected Double getBalance() {
        return balance;
    }

    protected String getAgencyNumber() {
        return agencyNumber;
    }

    protected String getAccountNumber() {
        return accountNumber;
    }

    protected Client getClient() {
        return client;
    }
}
