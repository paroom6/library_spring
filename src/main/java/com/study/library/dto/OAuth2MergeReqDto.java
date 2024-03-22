package com.study.library.dto;

import com.study.library.entity.OAuth2;
import com.study.library.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OAuth2MergeReqDto {

    private String username;
    private String password; //1q2w3e4r!
    private String oauth2Name;
    private String providerName;


}
