package com.study.library.controller;

import com.study.library.Service.AuthService;
import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.SignupReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult){
        if(authService.isDuplicatedByUsername(signupReqDto.getUsername())) {
            ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
            bindingResult.addError(objectError);
        }
        authService.signup(signupReqDto);
        return ResponseEntity.created(null).body(true);
    }

}
