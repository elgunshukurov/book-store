package az.elgunsh.springsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@RequiredArgsConstructor
public class CustomHttpConfigurer extends AbstractHttpConfigurer<CustomHttpConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.cors().and().csrf().disable().
                authorizeRequests(auth -> {
                    auth.anyRequest().authenticated(); // gelen butun requestleri authorise edir
                }).
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    // custom filterlerimiz burda ise dusur
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.
                addFilter(new JwtAuthenticationFilter(authenticationManager)).
                addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    public static CustomHttpConfigurer customDsl(){
        return new CustomHttpConfigurer();
    }
}
