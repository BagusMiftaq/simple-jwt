package com.enigmacamp.service;

import com.enigmacamp.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenImpl implements ValidateTokenService{
    @Value("${token.jwt-secret}")
    private String secret;
    @Override
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e){
            throw new UnauthorizedException("Expired Token");
        } catch (UnsupportedJwtException e){
            throw new UnauthorizedException("Token unsupport");
        } catch (MalformedJwtException e){
            throw new UnauthorizedException("Token Malformed");
        } catch (SignatureException e){
            throw new UnauthorizedException("Signature Unknown");
        } catch (IllegalArgumentException e){
            throw new UnauthorizedException("Token Invalid");
        }
    }
}
