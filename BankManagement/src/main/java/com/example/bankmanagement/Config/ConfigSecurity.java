package com.example.bankmanagement.Config;

import com.example.bankmanagement.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class ConfigSecurity {

    private  final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder( new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/register","api/v1/auth/login","api/v1/auth/update","api/v1/customer/register","api/v1/customer/update-my-info","api/v1/account/create-account","api/v1/account/view-my-account-details/**","api/v1/account/my-accounts","api/v1/account/deposit/**/to/**","api/v1/account/withdraw/**/from/**","api/v1/account/transfer/**/from/**/to/**").permitAll()
                .requestMatchers("api/v1/auth/delete/","api/v1/auth/get-All-Users","api/v1/customer/delete/**","api/v1/customer/get-all-customers","api/v1/employee/register-employee/**","api/v1/employee/get-all-employees","api/v1/employee/update-employee/**","api/v1/employee/delete/**","api/v1/account/get-all-accounts","api/v1/account/activate-account/**","api/v1/account/freeze/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
