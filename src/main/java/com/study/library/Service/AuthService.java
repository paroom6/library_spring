package com.study.library.Service;

import com.study.library.Repository.UserMapper;
import com.study.library.dto.SignupReqDto;
import com.study.library.entity.User;
import com.study.library.exception.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; //내장된 암호화 객체

    public boolean isDuplicatedByUsername(String username) {
        return userMapper.findUserByUsername(username) != null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void signup(SignupReqDto signupReqDto){
        int successCount = 0;
        User user = signupReqDto.toEntity(passwordEncoder);
        successCount += userMapper.saveUser(user);
        successCount += userMapper.saveRole(user.getUserId());
        if(successCount < 2) {
            throw new SaveException();
        }

    }
}
