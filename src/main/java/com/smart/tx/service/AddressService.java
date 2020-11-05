package com.smart.tx.service;

import java.rmi.ServerException;

/**
 * 前后端分离
 */
public interface AddressService {
    String saveAddress() throws ServerException;
}
