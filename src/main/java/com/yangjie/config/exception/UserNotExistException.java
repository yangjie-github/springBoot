package com.yangjie.config.exception;

/**
 * @author yangjie
 * @date 2019/3/27 19:00
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在");
    }
}
