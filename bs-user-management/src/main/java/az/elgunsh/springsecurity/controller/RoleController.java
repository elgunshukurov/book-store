package az.elgunsh.springsecurity.controller;

import az.elgunsh.springsecurity.models.Role;
import az.elgunsh.springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final UserService securityService;

    @PostMapping
    public Role save(@RequestBody Role role){
        return securityService.save(role);
    }


}

