package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.SysToken;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.common.DateUtils;
import cn.edu.swpu.info.college_website.dao.Impl.SysTokenRepositoryImpl;
import cn.edu.swpu.info.college_website.dao.Impl.UserRepositoryImpl;
import cn.edu.swpu.info.college_website.dao.TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShiroServiceImpl implements ShiroService {
    //12小时后失效
    private final static int EXPIRE = 12;


    private final UserRepositoryImpl userRepository;

    private final SysTokenRepositoryImpl sysTokenRepository;

    public ShiroServiceImpl(UserRepositoryImpl userRepository, SysTokenRepositoryImpl sysTokenRepositoryImpl) {
        this.userRepository = userRepository;
        this.sysTokenRepository = sysTokenRepositoryImpl;
    }

    /**
     * 根据username查找用户
     *
     * @param username
     * @return User
     */
    @Override
    public admin findByUsername(String username) {
        admin user = userRepository.findByUsername(username);
        return user;
    }


    @Override
    /**
     * 生成一个token
     *@param  [userId]
     *@return Result
     */
    public Map<String, Object> createToken(Integer userId) {
        Map<String, Object> result = new HashMap<>();
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        Date nows= DateUtils.asDate(now);
        Date expireTimes=DateUtils.asDate(expireTime);
        //判断是否生成过token
        SysToken tokenEntity = sysTokenRepository.findByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysToken();
            tokenEntity.setUserId(userId);
            //保存token
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(nows);
            tokenEntity.setExpireTime(expireTimes);
        } else {
            //更新token
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(nows);
            tokenEntity.setExpireTime(expireTimes);
        }
        sysTokenRepository.save(tokenEntity);
        result.put("token", token);
        result.put("expire", expireTime);
        return result;
    }

    /**
     * 更新数据库的token，使前端拥有的token失效
     * 防止黑客利用token搞事情
     *
     * @param token
     */
    @Override
    public void logout(String token) {
        SysToken byToken = findByToken(token);
        //生成一个token
        token = TokenGenerator.generateValue();
        //修改token
        byToken.setToken(token);
        //使前端获取到的token失效
        sysTokenRepository.save(byToken);
    }

    @Override
    public SysToken findByToken(String accessToken) {
        return sysTokenRepository.findByToken(accessToken);

    }

    @Override
    public admin findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }
}
