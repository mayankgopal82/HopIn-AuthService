package AuthService.AuthService.Service;

import AuthService.AuthService.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String secret;


    private String createToken(Map<String,Object> payload,String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+expiry*1000L);

        // Using Keys.hmacShaKeyFor for HS256
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().claims(payload)
                .issuedAt(now)
                .expiration(expiryDate)
                .subject(username)
                .signWith(key)
                .compact();
    }

    private String extractUserName (String token){
        return extractClaim(token,Claims::getSubject);
    }
    private boolean validateToken(String token, User user){
        return user.getEmail().equals(extractUserName(token)) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return extractExpiry(token).before(new Date());
    }
    private Date extractExpiry(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // Creating signing key from the secret
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    //Extract all claims (payload) from the token
    private Claims extractAllClaims(String token) {
        SecretKey key = getSignKey();
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    @Override
    public void run(String... args) throws Exception {
        User user  = new User();
        user.setEmail("mb@gmail.com");
        user.setMobile("5423424234");

        Map<String,Object> mp = new HashMap<>();
        mp.put("email",user.getEmail());
        mp.put("phone",user.getMobile());

        String token= createToken(mp,user.getEmail());
        System.out.println("Created Token is " +token);

        if(validateToken(token,user)){
            System.out.println("The Token is valid");
        }else{
            System.out.println("Invalid Token");
        }
    }}
g