package az.elgunsh.bslibrary.repo;

import az.elgunsh.bslibrary.dao.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
