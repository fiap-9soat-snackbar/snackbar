package com.snackbar.iam.presentation.dto;

import com.snackbar.iam.entity.IamRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String cpf;
    private IamRole role;

}
