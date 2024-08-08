package com.javguides.banking_app.mapper;

import com.javguides.banking_app.dto.AccountDto;
import com.javguides.banking_app.entity.Account;

public class AccountMapper {

    public static Account maptoAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
                );
        return account;
    }

    public static AccountDto mapToAccountdto(Account account
    ){
        AccountDto accountDto =  new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return  accountDto;
    }
}
