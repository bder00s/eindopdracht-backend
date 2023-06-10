package nl.novi.eindopdrachtbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final static String SECRET_KEY = "yabbadabbadooyabbadabbadooyabbadabbadooyabbadabbadoo";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // USERNAME UIT TOKEN HALEN
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //VERLOOP TIJDSTIP UIT TOKEN HALEN
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // CHECKEN OF TOKEN AL VERLOPEN IS
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // SIGNING KEY UIT TOKEN HALEN ??
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    // ALLE INFO UIT TOKEN HALEN
    private <T> T extractClaim(String token, Function<Claims, T>
            claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    // TOKEN MAKEN
    private String createToken(Map<String, Object> claims, String subject){
    long validPeriod = 1000 * 60 * 60 * 24 * 2; // 2 dagen
    long currentTime = System.currentTimeMillis();
    // TOKEN WORDT NU GEBAKKEN
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(currentTime))
            .setExpiration(new Date(currentTime + validPeriod))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }


    // TOKEN GENEREREN
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }


    // TOKEN VALIDEREN
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


}
