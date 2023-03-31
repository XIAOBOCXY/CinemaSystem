package com.movie2.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public final class JwtTokenUtil {

    //在http header中的名字
    public final static String TOKEN_HEADER = "Authorization";

    //一个星期过期
    public final static long REMEMBER_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;//毫秒

    //一天过期
    public final static long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 应用密钥,可以通过读取配置文件的方式设置秘钥，所有的常量都可以这样处理，
    private static final String APP_SECRET = "movie.api";//自己改

    // 角色权限声明
    private static final String ROLE_CLAIMS = "roles";
    private static final String USER_NAME = "username";

    /**
     * 生成Token
     * https://blog.csdn.net/wlddhj/article/details/79726001
     * https://segmentfault.com/a/1190000039997736?sort=newest
     * https://www.cnblogs.com/codeDirectory/p/13769755.html
     * String username, 用户的登录名
     * List<String> roles, 拥有的角色信息
     * long expiration token 的过期时间 单位是毫秒
     */
    public static String createToken(String username, List<String> roles, long expiration) {
        Map<String, Object> map = new HashMap<>();

        map.put(ROLE_CLAIMS, roles);//设置角色
        map.put(USER_NAME,username);//保存用户名
        return Jwts.builder()
                .setSubject(username)//设置主题，
                .setClaims(map)// 注入参数
                .setIssuedAt(new Date())//发布时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))//过期时间
                .signWith(SignatureAlgorithm.HS512,APP_SECRET)//设置算法及密钥
                .compact();
    }

    /**
     * 获取token body
     */
    private static Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch ( ExpiredJwtException e ) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 从Token中获取username
     */
    public static String getUserName(String token) {
        //getTokenClaims(token).getSubject();//没有办法获得，始终是null
        //把username写入到body中可以
        return getTokenClaims(token).get(USER_NAME).toString();
    }

    /**
     * 从Token中获取用户角色
     */
    public static List<String> getTokenRoles(String token) {
        List<String> roles = new ArrayList<>();
        Object object = getTokenClaims(token).get(ROLE_CLAIMS);
        if (object instanceof ArrayList<?>) {
            for (Object o : (List<?>) object) {
                roles.add((String) o);
                System.out.println(o);
            }
        }
        return roles;
    }

    /**
     * 校验Token是否过期,
     * 返回true,已经过期
     * 返回false,没有过期
     */
    public static boolean isExpiration(String token) {
        return getTokenClaims(token).getExpiration().before(new Date());
    }
}