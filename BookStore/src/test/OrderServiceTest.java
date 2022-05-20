package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import service.OrderService;
import service.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {
    private OrderService orderService =  new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(2,"java核心技术卷一",1,new BigDecimal(108),new BigDecimal(108)));

        String order = orderService.createOrder(cart, 1);
        System.out.println(order);
    }
}