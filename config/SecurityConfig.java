package tz.go.tcra.lims.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Value("${spring.ldap.urls}")
    private String ldapUrls;

    @Value("${spring.ldap.base.dn}")
    private String ldapBaseDn;
    
    @Autowired
    private JwtConfig jwtFilter;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //authentication configurer
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {          
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
    }
    
    //authorization configurer
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(
                    "/api/v1/authenticate",
                    "/v1/authenticate",
                    "/api/v1/account/signup",
                    "/v1/account/signup",
                    "/v1/account/activation",
                    "/licensing/v1/account/activation",
                    "/licensing/v3/api-docs", 
                    "/v3/api-docs", 
                    "/favicon.ico", 
                    "/configuration/ui", 
                    "/swagger-resources/**", 
                    "/configuration/security",
                    "/swagger-ui/**", 
                    "/swagger-ui.html", 
                    "/webjars/**"
            )
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider = new ActiveDirectoryLdapAuthenticationProvider(ldapBaseDn, ldapUrls);
        activeDirectoryLdapAuthenticationProvider.setConvertSubErrorCodesToExceptions(true);
        activeDirectoryLdapAuthenticationProvider.setUseAuthenticationRequestCredentials(true);
        
        return activeDirectoryLdapAuthenticationProvider;
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers=new ArrayList();
        providers.add(authenticationProvider());
        providers.add(activeDirectoryLdapAuthenticationProvider());
        
        return new ProviderManager(providers);
    }
}
