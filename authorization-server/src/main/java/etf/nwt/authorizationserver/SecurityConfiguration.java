package etf.nwt.authorizationserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalService(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/", "/registration", "/registrationConfirm", "/resendRegistrationToken")
                .permitAll()

                .antMatchers("/edit/**", "/payment/**", "/plate/**", "/book/**", "/home", "/stop/**",
                        "/notification/**", "/include/**"
                )
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('PARK')").antMatchers("/admin/**")
                .access("hasRole('ADMIN') or hasRole('PARK')").antMatchers("/updatePassword")
                .hasAuthority("CHANGE_PASSWORD_PRIVILEGE")

                .and().formLogin()
                // .successHandler(customSuccessHandler).failureHandler(customAuthenticationFailureHandler)
                .and().rememberMe()
                .rememberMeParameter("remember-me") // .to(persistentTokenRepository())
                .tokenValiditySeconds(86400).and().exceptionHandling().accessDeniedPage("/Access_Denied").and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout=true").invalidateHttpSession(false).deleteCookies("JSESSIONID");

        http.csrf().disable();

    }

    @Qualifier("customUserDetailsService")
    @Bean
    UserDetailsService makeUserDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .passwordEncoder(p -> passwordEncoder().encode(p))
                        .username("adnan")
                        .password("password")
                        .roles("USER")
                        .authorities("authorotah")
                        .build()
        );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public InMemoryTokenStore persistentTokenRepository() {
        InMemoryTokenStore store = new InMemoryTokenStore();
        return store;
    }
}
