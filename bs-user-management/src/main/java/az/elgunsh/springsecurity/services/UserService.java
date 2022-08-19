package az.elgunsh.springsecurity.services;

import az.elgunsh.springsecurity.models.Role;
import az.elgunsh.springsecurity.models.User;

import java.util.List;

public interface UserService {
    User save(User user);
    Role save(Role role);
    void addRoleTo(String username, String roleName);
    User get(String username);
    List<User> list();
}
