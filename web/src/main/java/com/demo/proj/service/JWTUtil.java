package com.demo.proj.service;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.proj.obj.MyUserF;
import org.apache.el.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTUtil {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 10L;//单位为秒 1800

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(MyUserF user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
//        System.out.printf("%d%s",user.getID(),user.getUserName());
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("ID", user.getID())//userId
                .withClaim("UserName", user.getUserName())//userName
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密

//        System.out.println(token);
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
//        DecodedJWT jwt = ((JWTVerifier) verifier).verify(token);
//        DecodedJWT jwt2=JWT.decode(token);
//        System.out.println(jwt.getClaim("ID").asInt());
//        System.out.println(jwt.getClaim("UserName").asString());
            //decodedJWT.getClaim("属性").asString()  获取负载中的属性值
        return token;
    }

    /**
     * 校验token并解析token
     * @return
     */
    public static Result verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = ((JWTVerifier) verifier).verify(token);

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("token解码异常");
            //解码异常则抛出异常
            return Result.fail(Result.getError(-10));
        }
//        System.out.println(jwt.getClaims());
        return Result.success(jwt.getClaims());
    }

}