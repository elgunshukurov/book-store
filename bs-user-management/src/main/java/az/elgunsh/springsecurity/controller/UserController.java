package az.elgunsh.springsecurity.controller;

import az.elgunsh.springsecurity.models.User;
import az.elgunsh.springsecurity.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> list(){
        return userService.list();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    // qismen update eleyirikse patch yaza bilerik
    @PatchMapping
    public void addRole(@RequestBody AddRolesRequest request){
        userService.addRoleTo(request.getUsername(), request.getRoleName());
    }

    @Data
    class AddRolesRequest{
        private String username;
        private String roleName;
    }

}
