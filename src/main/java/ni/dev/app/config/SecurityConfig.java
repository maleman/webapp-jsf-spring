package ni.dev.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	DataSource dataSource;

	private final String[] STATIC_MATCHER = { "/jakarta.faces.resource/**", "/css/login.css", "/js/**",
			"/createuser.xhtml", "/h2-console/**" };


	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select userName, password, enabled from Users where username=?")
            .authoritiesByUsernameQuery("select userName, authority from Authorities where username=?");
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						(requests) -> requests.requestMatchers(STATIC_MATCHER).permitAll().anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login.xhtml")
						.failureUrl("/login.xhtml?error=true")
						.defaultSuccessUrl("/index.xhtml",true)
						.permitAll())
				.logout((logout) -> logout.logoutSuccessUrl("/login.xhtml").permitAll())
				.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable)); // fix h2-console login
		

		return http.build();
	}
	
}
