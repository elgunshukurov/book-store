package az.elgunsh.bslibrary.controller;

import az.elgunsh.bslibrary.dao.security.Role;
import az.elgunsh.bslibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

