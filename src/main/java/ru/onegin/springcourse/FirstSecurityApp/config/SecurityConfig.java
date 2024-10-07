package ru.onegin.springcourse.FirstSecurityApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.onegin.springcourse.FirstSecurityApp.security.AuthProviderImpl;

/**
 * @author onegines
 * @date 07.10.2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**").permitAll()        // Доступ ко всем URL /public/** разрешён для всех
                        .requestMatchers("/admin/**").hasRole("ADMIN")    // Доступ к /admin/** только для пользователей с ролью ADMIN
                        .requestMatchers("/user/**").hasRole("USER")      // Доступ к /user/** только для пользователей с ролью USER
                        .anyRequest().authenticated()                    // Все остальные запросы требуют аутентификации
                )
                .formLogin().permitAll()                             // Разрешить доступ к странице логина для всех
                .and()
                .logout().permitAll();                               // Разрешить выход для всех

        return http.build();
    }
}