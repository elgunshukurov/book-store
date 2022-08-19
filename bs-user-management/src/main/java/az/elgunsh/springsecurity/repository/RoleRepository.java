package az.elgunsh.springsecurity.repository;

import az.elgunsh.springsecurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
