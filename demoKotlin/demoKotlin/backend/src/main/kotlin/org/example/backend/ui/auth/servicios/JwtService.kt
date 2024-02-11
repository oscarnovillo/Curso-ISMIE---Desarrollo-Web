package org.example.backend.ui.auth.servicios

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Service
class JwtService(
    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String,
    @Value("\${application.security.jwt.expiration}")
    val jwtExpiration: Long,

    @Value("\${application.security.jwt.refresh-token.expiration}")
    val refreshExpiration: Long,
) {



    fun generateToken(user: UserDetails): String {
        val extraClaims = mapOf(
            Pair("auth",user.authorities)
        )
        return buildToken(extraClaims, user, jwtExpiration)

    }
    fun generateRefreshToken(user: UserDetails): String {

        return buildToken(emptyMap(), user, refreshExpiration)
    }


    private fun buildToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
        expiration: Long
    ): String {
        return Jwts
            .builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .and()
            .claims(extraClaims)
            .signWith(getSignInKey())
            .compact()
    }

    private fun getSignInKey(): SecretKey {
        val digest: MessageDigest
        try {
            digest = MessageDigest.getInstance("SHA-512")
            digest.update(secretKey.toByteArray(StandardCharsets.UTF_8))
            val key2 = SecretKeySpec(
                digest.digest(), 0, 64, "AES"
            )
            return Keys.hmacShaKeyFor(key2.encoded)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun extractUsername(jwt: String): String  =
        extractClaim<String>(jwt) { obj: Claims -> obj.subject }

    fun extractAuthorities(jwt: String): Collection<GrantedAuthority>{

        val auth = extractClaim(jwt) { claims: Claims ->
            claims["auth"] as ArrayList<LinkedHashMap<String, Any>>
        }
        return auth[0].values.stream().map { a: Any ->
            SimpleGrantedAuthority(
                a.toString()
            )
        }.collect(Collectors.toSet())
    }


    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim<Date>(token) { obj: Claims -> obj.expiration }
    }
    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }
    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .payload


    }


}
