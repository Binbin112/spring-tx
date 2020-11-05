package com.smart.tx.service.impl;

import com.smart.tx.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddresssServiceimpl implements AddressService {
    @Override
    public String saveAddress() {
        return null;
    }
}
