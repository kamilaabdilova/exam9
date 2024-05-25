//package com.example.searchjob.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//
//@Configuration
//public class LocaleConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//    private LocaleChangeInterceptor localeChangeInterceptor(){
//        var los = new LocaleChangeInterceptor();
//        loc.setParamName("lang");
//        return los;
//    }
//}
