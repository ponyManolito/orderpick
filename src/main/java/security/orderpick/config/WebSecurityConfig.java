package security.orderpick.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .httpBasic()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth
        .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(getUserQuery())
            .authoritiesByUsernameQuery(getAuthoritiesQuery());
    }
    
    private String getUserQuery() {
		return "SELECT name as username, password as password, 1 FROM users WHERE name = ?";
	}

	private String getAuthoritiesQuery() {
    	return "SELECT users.name as username, roles.name as authorities, 1 FROM authorities,users,roles "
    			+ "WHERE authorities.id_user=users.id and authorities.id_role=roles.id and users.name=?"; 
    }
}