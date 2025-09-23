package AuthService.AuthService.Entity;

import AuthService.AuthService.Enum.Role;
import AuthService.AuthService.Enum.Status;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private String email;
    private String mobile;
    private String password;// hashed password
    private Role role;
    private Status status;

}
