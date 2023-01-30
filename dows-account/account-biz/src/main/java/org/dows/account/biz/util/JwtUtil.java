package org.dows.account.biz.util;

import cn.hutool.jwt.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 * @date 2023/1/30 13:37
 */
public class JwtUtil {
    private static final String DEFAULT_KEY = "findsoft@2022";

    public JwtUtil() {
    }

    public static String createJWT(Map<String, Object> claims, Date expireDate, String key){
        if (claims == null || claims.size() == 0) {
            throw new AccountException(EnumAccountStatusCode.JWT_CLAIMS_NOT_NULL_EXCEPTION);
        }
        key = key == null ? "findsoft@2022" : key;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        jwtBuilder.setExpiration(expireDate);
        jwtBuilder.signWith(signatureAlgorithm, key.getBytes());
        return jwtBuilder.compact();
    }

    public static Map<String, Object> parseJWT(String jwt, String key){
        try {
            key = key == null ? "findsoft@2022" : key;
            Claims claims = (Claims) Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwt).getBody();
            return (Map<String, Object>) claims;
        } catch (ExpiredJwtException var3) {
            throw new AccountException(EnumAccountStatusCode.JWT_CLAIMS_NOT_NULL_EXCEPTION);
        }
    }
}
