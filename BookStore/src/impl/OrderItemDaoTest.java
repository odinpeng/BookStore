package impl;

import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void saveOrder() {
        orderItemDao.saveOrderItem(new OrderItem(null,"Java编程思想",1,
                new BigDecimal(98),new BigDecimal(98),"123456789"));
    }
}