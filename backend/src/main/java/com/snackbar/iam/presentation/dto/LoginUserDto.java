package com.snackbar.iam.presentation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginUserDto {

    private String cpf;

    private String password;

    private Boolean anonymous = false;
}
