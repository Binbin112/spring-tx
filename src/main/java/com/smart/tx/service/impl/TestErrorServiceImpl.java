package com.smart.tx.service.impl;

import com.smart.tx.common.StatusCode;
import com.smart.tx.exception.AccountException;
import com.smart.tx.service.TestErrorService;
import org.springframework.stereotype.Service;

@Service
public class TestErrorServiceImpl implements TestErrorService {
    @Override
    public String testService() {
        throw new AccountException(StatusCode.ACCOUNT_EXIST_ERROR);
    }
}
