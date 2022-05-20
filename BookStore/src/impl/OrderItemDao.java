package impl;

import pojo.Order;
import pojo.OrderItem;

public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);
}
