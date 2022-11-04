package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.AccountRequestDto;
import com.example.bookscorner.dto.response.AccountResponseDto;
import com.example.bookscorner.dto.response.CustomerResponseDto;

import java.util.List;

public interface AccountService {
    AccountResponseDto getAccount(String email);
    CustomerResponseDto addNewAccount(AccountRequestDto accountRequestDto);
    List<AccountResponseDto> getAccounts();
}
