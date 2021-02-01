package springbootsecuritylearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springbootsecuritylearning.handler.MyAccessDeniedHandler;
import springbootsecuritylearning.handler.MyAuthenticationFailureHandler;
import springbootsecuritylearning.handler.MyAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .usernameParameter("username")//customize request username,default is 'username',correspond login.html
                .passwordParameter("password")//customize request password,default is 'password',correspond login.html
                .loginPage("/login.html")
                .loginProcessingUrl("/login")//the same as form submit action
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))//customize success handler
                //.successForwardUrl("/toMain")//must be url, only accept post request
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
        //.failureForwardUrl("/toError");


        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**", "/**/*.js").permitAll()
                .antMatchers(HttpMethod.GET, "/images/**").permitAll()
                .antMatchers("/demo").permitAll()
                .antMatchers("/hahah").denyAll()
                .antMatchers("/hasAuthority").hasAnyAuthority("admin")
                .antMatchers("/hasRole").hasRole("abc")
//                .antMatchers("/main.html").hasIpAddress("127.0.0.1")
//                .regexMatchers(HttpMethod.GET, "/demo").permitAll()
//                .mvcMatchers("/demo").servletPath("/xxxx").permitAll()
                .anyRequest().authenticated();//all request can process after login

        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);//exception handler

        http.csrf().disable();
    }
}
