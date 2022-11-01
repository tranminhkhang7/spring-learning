package com.example.bookscorner.dto.response;

import com.example.bookscorner.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private int accountId;
    private String email;
    private String password;
//    private List<Role> role;
}
