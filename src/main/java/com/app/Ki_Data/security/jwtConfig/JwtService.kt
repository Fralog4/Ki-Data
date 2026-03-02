package com.app.Ki_Data.security.jwtConfig

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${jwt.secret}") private val jwtSecret: String
) {

    fun extractUsername(jwtToken: String): String =
        extractClaim(jwtToken) { it.subject }


    fun generateToken(userDetails: UserDetails): String {

        val roles = userDetails.authorities.map { it.authority }

        val claims = mapOf<String, Any>("roles" to roles)

        return generateToken(claims, userDetails)
    }

    fun isTokenValid(jwtToken: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(jwtToken)
        return username == userDetails.username && !isTokenExpired(jwtToken)
    }

    private fun isTokenExpired(jwtToken: String): Boolean =
        extractExpiration(jwtToken).before(Date())

    private fun extractExpiration(jwtToken: String): Date =
        extractClaim(jwtToken) { it.expiration }


    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignInKey(), Jwts.SIG.HS256)
            .compact()
    }

    fun <T> extractClaim(jwtToken: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(jwtToken)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(jwtToken: String): Claims {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(jwtToken)
            .payload
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(jwtSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}