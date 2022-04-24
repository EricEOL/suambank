package br.com.ericeol.suambank.utils;

import br.com.ericeol.suambank.entities.Account.Account;
import br.com.ericeol.suambank.repositories.AccountRepository;

public class GenerateAgencyNumber {

    public static Long generate(AccountRepository accountRepository) {
        Long accountsLenght = accountRepository.count();

        if (accountsLenght <= 0) {
            return 1000L;
        } else {
            Long lastAccountId = accountRepository.lastAccount();

            Account account = accountRepository.findById(lastAccountId).get();

            Long lineQuantity = accountRepository.countAccountsFromAgency(account.getAgencyNumber());

            if (lineQuantity >= 1000) return account.getAgencyNumber() + 1000L;
            else return account.getAgencyNumber();
        }
    }
}
