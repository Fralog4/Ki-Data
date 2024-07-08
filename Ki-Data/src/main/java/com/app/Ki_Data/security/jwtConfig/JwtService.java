package com.app.Ki_Data.security.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="pF0ivYyNzWeUQHDbTh4vYAYDn8WJ5wunlfcQBeCYDzCX7HGYRfkhD/51YhPSX9gXPd7awk1ht/PE319nVS6HIg/cdhMBKGwntvg0ONSjs/PMZSNBZ0PUcOJAxRGu2LvOysyz+6F6n8d1SWTJc4rjph8zWpThAc1qui6AN4FIdFVXfr0LLidug5OVtSDG5yN74tCTEWcY5yi58moNF4wo1tmW1/5xQou/ov1yQuFvTHlJCU/4h0/EA7b2eMXkFWZcq4x9sLSXErPwKlbPoULeVCMKel4djKzBlco3aQbfCr9NF8FyDjaS74rgKJAfjnFnjvFGX77exts34o+iAqrN49g0P2jeG/Y1WqHI3zBkFlI=\n";
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isTokenValid(String jwtToken, UserDetails userDetails){
        final String username=extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver){
        final Claims claims= extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String jwtToken){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
