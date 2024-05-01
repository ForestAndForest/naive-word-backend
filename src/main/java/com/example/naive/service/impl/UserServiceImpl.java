package com.example.naive.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.example.naive.domain.User;
import com.example.naive.domain.vo.UserToken;
import com.example.naive.domain.vo.UserUpdate;
import com.example.naive.exception.*;
import com.example.naive.repository.UserRepository;
import com.example.naive.service.UserService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 29002
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, String> template;
    private final String USER_UPDATE_TIME_KEY = "user_update_time_hash";

    public UserServiceImpl(UserRepository userRepository, RedisTemplate<String, String> template) {
        this.userRepository = userRepository;
        this.template = template;
    }

    @Override
    public Object login(User user) {
        User checkedUser = checkUser(user);
        StpUtil.login(checkedUser.getUid());
        return new UserToken(checkedUser, StpUtil.getTokenValue());
    }

    @Override
    public Object signUp(User user) {
        if (
                Objects.isNull(user.getEmail()) || Objects.isNull(user.getPassword()) ||
                        user.getEmail().isBlank() || user.getPassword().isBlank()
        ) {
            throw new UserSignupException();
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserExistException();
        }
        String uid = UUID.randomUUID().toString();
        String hashedPassword = SaSecureUtil.sha256(user.getPassword());
        Long nowTimestamp = System.currentTimeMillis();
        user.setUid(uid);
        user.setPassword(hashedPassword);
        user.setCreateTime(nowTimestamp);
        user.setUpdateTime(nowTimestamp);
        user.setNickname("新用户" + uid.substring(0, 8));
        user.setEnable(true);
        StpUtil.login(user.getUid());
        setUserUpdateCache(uid, nowTimestamp);
        return new UserToken(userRepository.save(user), StpUtil.getTokenValue());
    }

    @Override
    public User checkUser(User user) {
        Optional<User> findUserOptional = userRepository.findByEmail(user.getEmail());
        if (findUserOptional.isEmpty()) {
            throw new UserNotExistException();
        }
        User findUser = findUserOptional.get();
        if (!findUser.isEnable()) {
            throw new UserBanedException();
        }
        if (!findUser.getPassword().equals(SaSecureUtil.sha256(user.getPassword()))) {
            throw new UserPasswordErrorException();
        }
        return findUser;
    }

    @Override
    public User getUserByUid(String uid) {
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isEmpty()) {
            throw new UserNotExistException();
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getUpdateUsers(List<UserUpdate> uidAndUpdateTimeList) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        Map<String, String> map = hashOperations.entries(USER_UPDATE_TIME_KEY);
        System.out.println(map);
        return
                uidAndUpdateTimeList.stream()
                        .map(userUpdate -> {
                            String storedTime = map.get(userUpdate.getUid());
                            if (Objects.nonNull(storedTime) && !storedTime.equals(String.valueOf(userUpdate.getTime()))) {
                                return getUserByUid(userUpdate.getUid());
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .toList();
    }

    @Override
    public User updateUser(User user) {
        StpUtil.checkLogin();
        String loginUid = (String) StpUtil.getLoginId();
        User findUser = userRepository.findByUid(user.getUid()).orElseThrow(UserExistException::new);

        if (!loginUid.equals(user.getUid())) {
            throw new UserNotPermissionException();
        }
        //不可更新字段
        user.setEmail(findUser.getEmail());
        user.setCreateTime(findUser.getCreateTime());
        user.setEnable(findUser.isEnable());
        user.setPassword(findUser.getPassword());
        user.setId(findUser.getId());
        user.setRole(findUser.getRole());
        user.setUpdateTime(System.currentTimeMillis());
        //缓存
        setUserUpdateCache(user.getUid(), user.getUpdateTime());
        return userRepository.save(user);
    }


    @Override
    public boolean logout() {
        StpUtil.logout();
        return true;
    }


    public void setUserUpdateCache(String uid, Long time) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        Map<String, String> map = hashOperations.entries(USER_UPDATE_TIME_KEY);
        map.put(uid, String.valueOf(time));
        hashOperations.putAll(USER_UPDATE_TIME_KEY,map);
    }
}
