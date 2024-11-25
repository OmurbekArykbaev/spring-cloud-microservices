package kg.nurtelecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
//@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

        serverHttpSecurity.csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange -> exchange.pathMatchers("/api/product")
//                        .hasRole("ROLE_MANAGER")
                                .permitAll()
                        .anyExchange()

                        .authenticated()
                ).oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults()));

        return serverHttpSecurity.build();

    }

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        var converter = new JwtAuthenticationConverter();
//        converter.setPrincipalClaimName("preferred_username");
//
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            var roles = (List<String>) jwt.getClaimAsMap("realm_access").get("roles");
//
//        });
//
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//
//
//         return  http
//                 .authorizeHttpRequests( c -> c.requestMatchers("/error").permitAll()
//                         .requestMatchers("/api/product").hasAnyRole()
//                         .anyRequest().authenticated())
//                 .build();
//
//    }
}
