package AuthService.AuthService.Client;

import AuthService.AuthService.DTO.SignupPassengerRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PassengerClient {

    public Boolean registerPassenger(SignupPassengerRequestDto passengerDto){

        //interservice communication to passenger service to register
        //have to make it asynchronous
        return true;
    }
}
