package br.com.ericeol.suambank.entities;

public class Client {
    private String cpf;
    private String name;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    public Client(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckingAccount getcheckingAccount() {
        return checkingAccount;
    }

    public void setCc(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public SavingsAccount getsavingsAccount() {
        return savingsAccount;
    }

    public void setCp(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
