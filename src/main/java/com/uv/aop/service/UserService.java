package com.uv.aop.service;

import org.springframework.stereotype.Service;

/*
 * @author liuwei
 * @date 2018/12/10 18:41
 *
 */
@Service
public class UserService {

    public String getName(String id) {
        return "Tom";
    }
}
