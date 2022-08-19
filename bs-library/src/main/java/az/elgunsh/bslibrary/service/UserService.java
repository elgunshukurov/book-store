package az.elgunsh.bslibrary.service;

import az.elgunsh.bslibrary.dao.security.Role;
import az.elgunsh.bslibrary.dao.security.User;

import java.util.List;

public interface UserService {
    User save(User user);
    Role save(Role role);
    void addRoleTo(String username, String roleName);
    User get(String username);
    List<User> list();
}
