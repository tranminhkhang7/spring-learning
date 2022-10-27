package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.AccountRequestDto;
import com.example.bookscorner.dto.response.AccountResponseDto;
import com.example.bookscorner.entities.Account;
import com.example.bookscorner.mappers.ObjectMapperUtils;
import com.example.bookscorner.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService { // add UserDetailsService

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private final ObjectMapperUtils objectMapperUtils;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            log.error("Account not found in the database.");
            throw new UsernameNotFoundException("Account not found in the database.");
        } else {
            log.info("Account found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
    }

    @Override
    public AccountResponseDto getAccount(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            AccountResponseDto accountResponseDto = mapper.map(account, AccountResponseDto.class);
            return accountResponseDto;
        } else {
            throw new IllegalStateException("This account does not exist.");
        }
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            throw new IllegalStateException("There is nothing here!");
        }
        List<AccountResponseDto> accountResponseDtoList = objectMapperUtils.mapAll(accounts,AccountResponseDto.class);
        return accountResponseDtoList;
    }

    public AccountResponseDto addNewAccount(AccountRequestDto accountRequestDto) {
        Account account = mapper.map(accountRequestDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        accountRepository.save(account);

        AccountResponseDto accountResponseDto = mapper.map(account, AccountResponseDto.class);
        return accountResponseDto;
    }
}
