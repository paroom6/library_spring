package com.study.library.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EditPasswordReqDto {
    @NotBlank
    private String oldPassword;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "영문자, 숫자, 특수문자를 포함한 8 ~ 128자리 형식이여야 합니다.")
    private String newPassword;
    @NotBlank
    private String newCheck;
}
