package service;

import impl.*;
import pojo.*;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements  OrderService{
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    /**
     * 创建订单(保存订单和订单项)
     * @param cart
     * @param userId
     * @return
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        /*生成订单号，唯一： 时间戳+用户id*/
        String orderId=System.currentTimeMillis()+""+userId;
        /*创建一个订单对象*/
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        /*保存订单*/
        orderDao.saveOrder(order);

        /*遍历购物车中的商品项cart转化为订单项order保存到数据库*/
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            /*获取每个购物车中的商品项*/
            CartItem cartItem = entry.getValue();
            /*转化为每一个订单项*/
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            /*保存订单项*/
            orderItemDao.saveOrderItem(orderItem);

            /*保存订单时，要更改图书的销量和库存*/
            /*获取对应图书对象*/
            Book book = bookDao.queryBookById(cartItem.getId());
            /*更改销量*/
            book.setSales(book.getSales()+cartItem.getCount());
            /*更改库存*/
            book.setStock(book.getStock()- cartItem.getCount());
            /*保存数据到t_book数据库*/
            bookDao.updateBook(book);
        }
        /*结账后，清空购物车*/
        cart.clear();
        return orderId;
    }
}
