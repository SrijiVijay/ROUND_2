//package com.kgisl.main;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//	}
//}

//@EnableWebSecurity
//public class ServcieRegistryConfig extends WebSecurityConfigurerAdapter { 
//	
//	protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().ignoringAntMatchers("/eureka/**");
//        super.configure(http);
//    }
//}

//    @Configuration
//    public static class WebSecurity extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(AuthenticationManagerBuilder manageBuilder) throws Exception {
//            manageBuilder.inMemoryAuthentication().withUser("admin").password("{noop}admin123").roles("");
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            /**
//             * Needed to add and().csrf().disabled() otherwise clients will trow 403 when trying to autenticate
//             *
//             * Reference: https://github.com/spring-cloud/spring-cloud-netflix/issues/2754
//             */
//            http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//        }
//    }

//@Override
//public void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin")
//			.password("admin").authorities("admin123");
//}
