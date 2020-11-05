package com.smart.tx.service.impl;

import com.smart.tx.common.StatusCode;
import com.smart.tx.exception.AccountException;
import com.smart.tx.service.AddressService;
import com.smart.tx.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 使用原则
 * 在service使用事务
 * 在需要的方法上使用
 *
 * @Transactional 注意:
 * 禁止在类上使用
 */
// @Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    AddressService addressService;

    public String register() {
//        throw new AccountException(StatusCode.ACCOUNT_EXIST_ERROR);
        return null;
    }

    /**
     * 开启了事务
     */
    @Transactional
    @Override
    public String add() {
        return null;
    }

    @Transactional(propagation = Propagation.NEVER)
    @Override
    public String modify() {
        return null;
    }
}
