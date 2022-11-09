package com.example.bookscorner.security;

import com.example.bookscorner.repositories.CustomerRepository;
import com.example.bookscorner.security.filter.CustomAuthenticationFilter;
import com.example.bookscorner.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final UserDetailsService userDetailsService;
//    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), customerRepository);
        customAuthenticationFilter.setFilterProcessesUrl("/login");

        http.cors().and();

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

//        //Log in
//        http.authorizeRequests().antMatchers("/login/**").permitAll();
//        //Get carts
//        http.authorizeRequests().antMatchers(GET,"/checkout/**").hasAnyAuthority("CUSTOMER");
//        //Add books
//        http.authorizeRequests().antMatchers(POST,"/book/admin").hasAuthority("ADMIN");
//        //Get books with status
//        http.authorizeRequests().antMatchers(GET,"/book/admin").hasAuthority("ADMIN");

//        http.authorizeRequests().anyRequest().authenticated();


        //Log in
        http.authorizeRequests().antMatchers("/login/**").permitAll();

        //BookController
        //countAllBooks
        http.authorizeRequests().antMatchers(GET,"/book/totalnumber").hasAnyAuthority("ADMIN");
        //addNewBook
        http.authorizeRequests().antMatchers(POST,"/book").hasAnyAuthority("ADMIN");
        //getAllBooks (with disabled books)
        http.authorizeRequests().antMatchers(GET,"/book/allbooks").hasAnyAuthority("ADMIN");
        //updateBook
        http.authorizeRequests().antMatchers(PUT,"/book").hasAnyAuthority("ADMIN");
        //deleteBook
        http.authorizeRequests().antMatchers(DELETE,"/book/**").hasAnyAuthority("ADMIN");


        //CommentController
        //addComment
        http.authorizeRequests().antMatchers(POST,"/comment").hasAnyAuthority("CUSTOMER");


        //CustomerController
        //getAllCustomers
        http.authorizeRequests().antMatchers(GET,"/customer/admin").hasAnyAuthority("ADMIN");
        //banCustomer
        http.authorizeRequests().antMatchers(DELETE,"/customer/admin/**").hasAnyAuthority("ADMIN");
        //countAllCustomers
        http.authorizeRequests().antMatchers(GET,"/customer/totalnumber").hasAnyAuthority("ADMIN");


        //GenreController
        //addNewGenre
        http.authorizeRequests().antMatchers(POST,"/genre/admin").hasAnyAuthority("ADMIN");
        //deleteGenre
        http.authorizeRequests().antMatchers(DELETE,"/genre/admin/**").hasAnyAuthority("ADMIN");


        //OrderController
        //addOrder
        http.authorizeRequests().antMatchers(POST,"/order").hasAnyAuthority("CUSTOMER");





        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
