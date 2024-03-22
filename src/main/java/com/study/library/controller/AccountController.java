package com.study.library.controller;

import com.study.library.Service.AccountService;
import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.EditPasswordReqDto;
import com.study.library.security.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping("/principal")
    public ResponseEntity<?> getPrincical() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }
    @ValidAspect
    @PutMapping("/password")
    public ResponseEntity<?> editPassword(@Valid@RequestBody EditPasswordReqDto editPasswordReqDto, BindingResult bindingResult) {
        accountService.editPassword(editPasswordReqDto);
        return ResponseEntity.ok(true);
    }


}
