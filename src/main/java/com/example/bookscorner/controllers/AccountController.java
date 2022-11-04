package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.AccountRequestDto;
import com.example.bookscorner.dto.response.AccountResponseDto;
import com.example.bookscorner.dto.response.CustomerResponseDto;
import com.example.bookscorner.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "account")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponseDto>> getAccounts(){
        return ResponseEntity.ok().body(accountService.getAccounts());
    }

    @PostMapping() //ko dùng động từ. ĐÃ SỬA
    public ResponseEntity<CustomerResponseDto> addNewAccount(@RequestBody AccountRequestDto accountRequestDto){
        return ResponseEntity.ok().body(accountService.addNewAccount(accountRequestDto));
    }
}
