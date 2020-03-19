package com.acloudglobal.ctsbewx.configration;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Hibernate校验配置文件
 *
 * @author yupl@acloudchina.com
 * @date 2019-12-10 11:06 上午
 * @since V2.0.0
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                                                      .configure()
                                                      .failFast(true)
                                                      .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
