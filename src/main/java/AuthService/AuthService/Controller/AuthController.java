package AuthService.AuthService.Controller;

import AuthService.AuthService.DTO.AuthRequestDto;
import AuthService.AuthService.DTO.SignupPassengerRequestDto;
import AuthService.AuthService.DTO.SignupPassengerResponseDto;
import AuthService.AuthService.Service.AuthService;
import AuthService.AuthService.Service.JwtTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @PostMapping("/signup/passenger")
    public ResponseEntity<SignupPassengerResponseDto> signUp(@Valid @RequestBody SignupPassengerRequestDto request){

        SignupPassengerResponseDto response = authService.signupPassenger(request);

        return  ResponseEntity.ok(response);

    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody AuthRequestDto signInRequest, HttpServletResponse response){

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        if(authentication.isAuthenticated()){
            Map<String,Object> payload = new HashMap<>();
            payload.put("email",signInRequest.getEmail());
            String token = jwtTokenService.createToken(payload,signInRequest.getEmail());

            ResponseCookie cookie=  ResponseCookie.from("jwtToken",token).httpOnly(true).
                    secure(true).sameSite("Strict").path("/").build() ;

            response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());

            return new ResponseEntity(HttpStatus.OK);

        }else{
            throw new UsernameNotFoundException("User Not Found");
        }

    }
    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody Map<String,String> body){

        String token = body.get("token");
        if(jwtTokenService.validateToken(token)){
           Claims claim = jwtTokenService.extractAllClaims(token);
           return new ResponseEntity<Claims>(claim,HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid or Expired Token",HttpStatus.UNAUTHORIZED);
    }

}
