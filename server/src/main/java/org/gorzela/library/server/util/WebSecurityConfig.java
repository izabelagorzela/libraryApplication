package org.gorzela.library.server.util;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         http.httpBasic().and().
                 authorizeRequests().antMatchers(HttpMethod.DELETE, "/Rest/library/reader/delete" + "/**").hasRole("ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/reader/get" + "/**").hasAnyRole("USER", "ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/reservation/get/byReaderId").hasAnyRole("USER", "ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/reservation/deleteById").hasAnyRole("USER", "ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/reservation/addNewReservation").hasAnyRole("USER", "ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/loan/get/byReaderAndReturnDate").hasAnyRole("USER", "ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/loan/update/one").hasAnyRole("USER", "ADMIN").and().
                 authorizeRequests().antMatchers("/Rest/library/reader/getInformation" + "/**").permitAll().and().
                 csrf().disable();


    }
}
