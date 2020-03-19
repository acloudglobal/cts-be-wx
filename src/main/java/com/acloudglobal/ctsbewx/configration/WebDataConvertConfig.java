package com.acloudglobal.ctsbewx.configration;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Web数据格式统一转换
 *
 * @author yupl@acloudchina.com
 * @date 2019-12-11 6:21 下午
 * @since V2.0.0
 */
@Slf4j
@Configuration
public class WebDataConvertConfig {
    @Resource
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void convert() {
        ConfigurableWebBindingInitializer webBindingInitializer =
                (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (null != webBindingInitializer.getConversionService()) {
            GenericConversionService genericConversionService =
                    (GenericConversionService) webBindingInitializer.getConversionService();
            genericConversionService.addConverter(
                    new Converter<String, String>() {
                        public String convert(String s) {
                            return StringUtils.isNotEmpty(s) ? s.trim() : null;
                        }
                    });
        }
    }
}
