package e_commerce.config;

import e_commerce.models.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.GET, "/", "/index", "/api/produtos").permitAll()
                                                .requestMatchers("/login", "/register", "/perform_login", "/css/**",
                                                                "/js/**", "/images/**")
                                                .permitAll()

                                                // only ADMIN can access admin dashboard and create/edit/delete products (web CRUD)
                                                .requestMatchers("/produtosAdmin", "/produtosAdmin/**").hasRole(Role.ADMIN.name())
                                                .requestMatchers(HttpMethod.GET, "/produtos/novo", "/produtos/editar/**")
                                                .hasRole(Role.ADMIN.name())
                                                .requestMatchers(HttpMethod.POST, "/produtos/salvar",
                                                                "/produtos/atualizar/**")
                                                .hasRole(Role.ADMIN.name())
                                                .requestMatchers(HttpMethod.GET, "/produtos/excluir/**")
                                                .hasRole(Role.ADMIN.name())

                                                // APIs
                                                .requestMatchers(HttpMethod.POST, "/api/produtos")
                                                .hasRole(Role.ADMIN.name())
                                                .requestMatchers("/api/comprar")
                                                .hasAnyRole(Role.USER.name(), Role.ADMIN.name())

                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/perform_login")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login?error")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll())
                                .httpBasic(Customizer.withDefaults());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
