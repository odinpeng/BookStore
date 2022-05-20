package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(2,"java核心技术卷一",1,new BigDecimal(108),new BigDecimal(108)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(2,"java核心技术卷一",1,new BigDecimal(108),new BigDecimal(108)));

        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(2,"java核心技术卷一",1,new BigDecimal(108),new BigDecimal(108)));

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(1,"java编程思想",1,new BigDecimal(108),new BigDecimal(108)));
        cart.addItem(new CartItem(2,"java核心技术卷一",1,new BigDecimal(108),new BigDecimal(108)));

        cart.updateCount(2,3);
        System.out.println(cart);
    }
}