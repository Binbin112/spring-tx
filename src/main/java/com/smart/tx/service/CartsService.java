package com.smart.tx.service;

import java.util.List;

public interface CartsService {
    int clean();
    // UPDATE carts SET status = 0 WHERE user_id = 1   AND product_id IN () AND status=1;
    int cleanCartsByIds(int userId, List<Integer> ids);
}
