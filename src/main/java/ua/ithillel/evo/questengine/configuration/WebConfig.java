//package ua.ithillel.evo.questengine.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public WebSecurityConfigurerAdapter webSecurity() {
//        return new WebSecurityConfigurerAdapter() {
//
//            @Override
//            protected void configure(HttpSecurity http) throws Exception {
//                http.headers().addHeaderWriter(
//                        new StaticHeadersWriter("Access-Control-Allow-Origin", "*"));
//            }
//        };
//    }
//}
