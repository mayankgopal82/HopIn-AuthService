package AuthService.AuthService.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupPassengerRequestDto {

    @NotNull
    @NotEmpty
    private String email;
    private String mobile;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String firstName;
    private String lastName;
}
