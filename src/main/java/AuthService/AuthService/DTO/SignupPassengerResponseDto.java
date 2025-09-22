package AuthService.AuthService.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupPassengerResponseDto {

    private String id;
    private String firstName;
    private String email;
    private String password; //encrypted
    private LocalDate createdAt;

}
