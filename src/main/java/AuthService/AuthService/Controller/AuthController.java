package AuthService.AuthService.Controller;

import AuthService.AuthService.DTO.SignupPassengerRequestDto;
import AuthService.AuthService.DTO.SignupPassengerResponseDto;
import AuthService.AuthService.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @PostMapping("/signup/passenger")
    public ResponseEntity<SignupPassengerResponseDto> signUp(@Valid @RequestBody SignupPassengerRequestDto request){

        SignupPassengerResponseDto response = authService.signupPassenger(request);

        return  ResponseEntity.ok(response);

    }

}
