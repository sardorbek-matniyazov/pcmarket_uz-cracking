package basePackage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*
    SUPER_ADMIN, MODERATOR va OPERATOR rollari bo’lsin.
    SUPER_ADMIN har qanday ishni qila oladi;
    MODERATOR mahsulot qo’sha oladi va tahrirlay oladi ,ammo o’chira olmaydi;
    OPERATOR buyurtmalar bilan ishlaydi, mahsulotni o'chira olmaydi va tahrirlay olmaydi.
*/

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("SUPER_ADMIN").authorities(
                        "READ_ALL", "WRITE_ALL", "DELETE_ALL", "READ_ONE", "UPDATE_ALL"
                ).and()
                .withUser("moderator").password(passwordEncoder().encode("moderator")).roles("MODERATOR").authorities(
                        "READ_ALL", "WRITE_ALL", "READ_ONE", "UPDATE_ALL"
                ).and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR").authorities(
                        "READ_ALL", "WRITE_ALL", "READ_ONE"
                ).and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER").authorities(
                        "READ_ONE"
                );
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, new String[]{"/brand/all", "/category/all", "/product/all"}).hasAuthority(
                        "READ_ALL"
                )
                .antMatchers(HttpMethod.GET, new String[]{"/brand/*", "/category/*", "/product/*"}).permitAll()
                .antMatchers(HttpMethod.POST, new String[]{"/brand/*", "/category/*", "/product/*", "/message/*"}).hasAuthority(
                        "WRITE_ALL"
                )
                .antMatchers(HttpMethod.PUT, new String[]{"/brand/*", "/category/*", "/product/*", "/message/*"}).hasAuthority(
                        "UPDATE_ALL"
                )
                .antMatchers(HttpMethod.DELETE, new String[]{"/brand/*", "/category/*", "/product/*", "/message/*"}).hasAuthority(
                        "DELETE_ALL"
                )
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
