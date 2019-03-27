package pl.mkpk.newhorizon.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableTransactionManagement
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${users-query}")
    private String usersQuery;

    @Value("${roles-query}")
    private String rolesQuery;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                //.usersByUsernameQuery("select u.email, u.password, u.active from user u where u.email=?")
                .authoritiesByUsernameQuery(rolesQuery)
                //.authoritiesByUsernameQuery("select u.email, r.role from user u " +
                //        "inner join user_role ur on(u.user_id=ur.user_id) " +
                //        "inner join role r on(ur.role_id=r.role_id) where u.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
                .antMatchers("/user/**").hasAnyAuthority("DIETETICAN","ADMIN","USER").anyRequest().authenticated()
                .antMatchers("/dietetican/**").hasAnyAuthority("ADMIN","DIETETICAN").anyRequest().authenticated()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin()
                    .loginPage("/login").failureUrl("/login?error=true")
                    .defaultSuccessUrl("/templates")
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");//.and().exceptionHandling();
    }
}
