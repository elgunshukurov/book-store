package az.elgunsh.springsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // bunu cagirmaql defolt isleyen AuthenticationManager-i custom hala getiririk
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        /* Bu metodu modify elemekle defolt acilan form login-i deyismis oluruq */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        /* principle dan gelen username passworda esasen token yaradir ve onu bazadaki tokenle qarsilasdirir
        uygunsuzluq varsa exception atir.
           Diqqet bu jwt token deyil, sirf spring basa dussun deye hazirlanir */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        SecurityProperties applicationProperties = new SecurityProperties();
        Algorithm algorithm = Algorithm.HMAC256(applicationProperties.getJwtProperties().getSecret().getBytes());

        // jwt token generasiya olunur
        String accessToken = JWT.create().
                withSubject(user.getUsername()).
                withIssuer(request.getRequestURL().toString()).
                withExpiresAt(Date.from(Instant.now().plus(1, ChronoUnit.DAYS))).
                withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())).
                sign(algorithm);

        String refreshToken = JWT.create().
                withSubject(user.getUsername()).
                withIssuer(request.getRequestURL().toString()).
                withExpiresAt(Date.from(Instant.now().plus(3, ChronoUnit.DAYS))).
                withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).
                sign(algorithm);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), Map.of("access_token", accessToken, "refresh_token", refreshToken));
    }
}
