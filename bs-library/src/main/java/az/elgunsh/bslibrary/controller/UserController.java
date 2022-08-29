package az.elgunsh.bslibrary.controller;

import az.elgunsh.bslibrary.dao.security.User;
import az.elgunsh.bslibrary.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.list();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

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
