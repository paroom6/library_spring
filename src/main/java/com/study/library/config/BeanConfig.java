package com.study.mvc.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;


//Bean 수동 등록(2개이상 등록가능)
@Configuration
public class BeanConfig {
    @Bean//component는 클래스위에만 작성 - Bean의 경우 메서드위에 작성가능
    public ObjectMapper objectMapper() {//이미 완성된 클래스를 ioc에 넣는 법
        return new ObjectMapper();
    }
}
