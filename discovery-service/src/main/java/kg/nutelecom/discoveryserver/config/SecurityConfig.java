//package kg.nutelecom.discoveryserver.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
////@EnableWebSecurity
//public class SecurityConfig   {
//
//
////    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder
////                .inMemoryAuthentication()
////                .withUser("eureka")
////                .password("password")
////                .authorities("USER");
////    }
////
////
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(request -> request.requestMatchers(
////                        "/eureka/**",       // Для маршрутов Eureka
////                        "/actuator/**"      // (Опционально) для Actuator
////                ).permitAll().anyRequest().authenticated());
////
////
////        httpSecurity.oauth2ResourceServer((oauth2) -> oauth2
////                .jwt(Customizer.withDefaults()));
////
////        httpSecurity
////                .httpBasic(Customizer.withDefaults())
////                .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////
////        return httpSecurity.build();
////    }
////
//}
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }