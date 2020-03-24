package co.com.hotelyando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import co.com.hotelyando.core.authorization.JWTAuthorizationFilter;

/*
 * Clase que inicializa los componentes de spring
 */
@SpringBootApplication
public class HotelyandoApplication {
	
	/*
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(HotelyandoApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE");
			}
		};
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/role").permitAll()
				.antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/configuration/**").permitAll()
				.antMatchers("/swagger*/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.anyRequest().authenticated();
		}
		
	}
	

}
