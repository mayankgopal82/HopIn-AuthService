package AuthService.AuthService.Service;

import AuthService.AuthService.Client.PassengerClient;
import AuthService.AuthService.DTO.SignupPassengerRequestDto;
import AuthService.AuthService.Entity.User;
import AuthService.AuthService.Enum.Role;
import AuthService.AuthService.Enum.Status;
import AuthService.AuthService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {

    @Autowired
    PassengerClient passengerClient;

    @Autowired
    UserRepository userRepo;


    public void signupPassenger(SignupPassengerRequestDto request){

        request.setRole(Role.PASSENGER);
        passengerClient.registerPassenger(request);
        //need to do mapping here using mapstruct --for now
        //manually mapping
//        User user=   User.builder().role(request.getRole()).email(request.getEmail()).firstName(request.getFirstName())
//                        .lastName(request.getLastName()).mobile(request.getMobile()).password(request.getPassword()).build();
        // Mapping using setters instead of builder
        User user = new User();
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobile(request.getMobile());
        user.setPassword(request.getPassword());
        userRepo.save(user);

    }
}
