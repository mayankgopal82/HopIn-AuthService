package AuthService.AuthService.Controller;

import AuthService.AuthService.DTO.SignupPassengerRequestDto;
import AuthService.AuthService.DTO.SignupPassengerResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signup/passenger")
    public ResponseEntity<SignupPassengerResponseDto> signUp(@Valid @RequestBody SignupPassengerRequestDto request){
        return null;
    }

}
