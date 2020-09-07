package com.lzy.springbootjwtcaptcha.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lzy.springbootjwtcaptcha.dao.User;



/**
 * @author lizhongyi
 */
@Service("TokenService")
public class TokenService {

    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    public String getToken(User user) {

        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        String token="";
        // 将 需要的信息 保存到 token 里面
        token= JWT.create().withAudience(user.getId(),user.getUsername(),user.getPower())

                // 设置token存活时间 10天
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }
}
