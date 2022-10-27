package com.example.bookscorner.dto.request;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AccountRequestDto {
    private String email;
    private String password;
    private String role;
}
