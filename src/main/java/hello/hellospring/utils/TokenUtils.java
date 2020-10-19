package hello.hellospring.utils;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Log4j2
public class TokenUtils {

    // 액세스 토큰
    @Value("${props.jwt.expire-access}")
    private static int jwtAccessExpiration;

    // 리프레쉬 토큰
    @Value("${props.jwt.expire-refresh}")
    private static int jwtRefreshExpiration;

    private static final String secretKey = "ThisIsA_SecretKeyForJwtExample";

    public static String generateJwtToken(UserVo userVo) {

        JwtBuilder builder = Jwts.builder()
                .setSubject(userVo.getUserEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(userVo))
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.ES256, createSigningKey());

        return builder.compact();
    }

    public static boolean isValidToken(String token) {
        try {

            Claims claims = getClaimsFormToken(token);

            log.info("expireTime : " + claims.getExpiration());
            log.info("email : " + claims.get("email"));
            log.info("role : " + claims.get("role"));

            return true;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired : " + exception);
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered : " + exception);
            return false;
        } catch (NullPointerException exception) {
            log.error("NullPointException : " + exception);
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split("")[1];
    }

    private static Date createExpireDateForOneYear() {
        // 토큰 만료 시간 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, jwtAccessExpiration);
        return c.getTime();
    }

    private static Map<String, Object> createHeader() {

        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(UserVO userVO) {

        // 공개 클레임에 사용자 이름과 이메일을 설정하여 정보 조회
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", userVO.getUserEmail());
        claims.put("role", userVO.getRole());

        return claims;
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    private static String getUserEmailFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("email");
    }

    private static UserRole getRoleFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (UserRole) claims.get("role");
    }

}
