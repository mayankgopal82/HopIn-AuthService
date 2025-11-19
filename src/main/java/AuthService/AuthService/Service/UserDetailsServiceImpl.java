package AuthService.AuthService.Service;

import AuthService.AuthService.Entity.User;
import AuthService.AuthService.Repository.UserRepository;
import AuthService.AuthService.helper.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       Optional<User> user =  userDao.findByEmail(email);
       if(user.isPresent()){
           AuthUserDetails userDetails= new AuthUserDetails(user.get());
           return userDetails;
       }else{
           throw new UsernameNotFoundException("Cannot find the user by the given email");
       }
    }

}
