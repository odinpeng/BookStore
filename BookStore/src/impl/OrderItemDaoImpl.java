package impl;

import pojo.Order;
import pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao{
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item (name,count,price,total_price,order_id) values (?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
