package service;

import pojo.Cart;
import pojo.Order;

public interface OrderService {

    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId);
}
