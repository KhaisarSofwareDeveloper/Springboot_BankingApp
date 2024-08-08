package com.javguides.banking_app.service.impl;

import com.javguides.banking_app.dto.AccountDto;
import com.javguides.banking_app.entity.Account;
import com.javguides.banking_app.mapper.AccountMapper;
import com.javguides.banking_app.repository.AccountRepository;
import com.javguides.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
       Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountdto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Id does not exists"));
        return AccountMapper.mapToAccountdto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountdto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insuffucient Amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountdto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountdto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        accountRepository.deleteById(id);

    }
}
