package com.acloudglobal.ctsbewx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(value = "com.acloudglobal.ctsbewx")
@ImportAutoConfiguration(value = {
        DataSourceAutoConfiguration.class,
        DispatcherServletAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
//        JacksonAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class,
//        PersistenceExceptionTranslationAutoConfiguration.class,
        ServerPropertiesAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        ValidationAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class
})
public class CtsBeWxApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CtsBeWxApplication.class, args);
        log.info("\n----------------------------------------------------------\n\t" +
                         "Application {} is running!" +
                         "\n---------------------------------------------------------",
                 applicationContext.getId());
    }

}
