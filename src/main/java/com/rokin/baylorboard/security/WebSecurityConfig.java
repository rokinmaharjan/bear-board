////tag::securityConfigOuterClass[]
//package com.rokin.baylorboard.security;
//
//import com.rokin.baylorboard.service.UserService;
//import org.springframework.context.annotation.Bean;
////tag::baseBonesImports[]
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web
//                        .configuration.WebSecurityConfigurerAdapter;
////end::securityConfigOuterClass[]
////end::baseBonesImports[]
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation
//             .authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web
//             .builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
////tag::securityConfigOuterClass[]
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//  @Autowired
//  UserService userService;
//
////  @Autowired
////  private AuthEntryPointJwt unauthorizedHandler;
////
////  @Bean
////  public AuthTokenFilter authenticationJwtTokenFilter() {
////    return new AuthTokenFilter();
////  }
//
//  @Override
//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
//  }
//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .authorizeRequests().antMatchers("/users/**").permitAll()
////            .antMatchers("/api/test/**").permitAll()
//            .anyRequest().authenticated();
//  }
//}
