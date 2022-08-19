package az.elgunsh.bslibrary.repo;

import az.elgunsh.bslibrary.dao.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
