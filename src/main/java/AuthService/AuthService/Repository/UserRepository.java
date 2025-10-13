package AuthService.AuthService.Repository;

import AuthService.AuthService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface adUserRepository extends JpaRepository<User, UUID> {
}
