package com.example.bookscorner.dto.request;

import lombok.*;

import javax.persistence.Column;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AccountRequestDto {
    private String email;
    private String password;
//    private String role;

//    private int customerId;
    private String name;
    private String gender;
    private Date birthday;
}
