package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.AccountRequestDto;
import com.example.bookscorner.dto.response.AccountResponseDto;

import java.util.List;

public interface AccountService {
    AccountResponseDto getAccount(String email);
    AccountResponseDto addNewAccount(AccountRequestDto accountRequestDto);
    List<AccountResponseDto> getAccounts();
}
