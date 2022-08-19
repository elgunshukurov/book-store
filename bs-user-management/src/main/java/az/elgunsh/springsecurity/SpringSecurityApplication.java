package az.elgunsh.springsecurity;

import az.elgunsh.springsecurity.models.Role;
import az.elgunsh.springsecurity.models.User;
import az.elgunsh.springsecurity.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
@Slf4j
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService service) {
        return args -> {
            service.save(Role.builder().name("ROLE_USER").build());
            service.save(Role.builder().name("ROLE_ADMIN").build());

            service.save(User.builder().name("John")
                    .username("jdoe")
                    .password("1234")
                    .roles(new HashSet<>())
                    .build());

            service.addRoleTo("jdoe","ROLE_USER");
        };
    }


}
