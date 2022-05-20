package web;

import pojo.Cart;
import pojo.User;
import service.OrderService;
import service.OrderServiceImpl;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 创建订单，并生成订单项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*在Session域中获取cart对象*/
        Cart cart = (Cart)req.getSession().getAttribute("cart");

        /*获取userId*/
        User loginUser=(User)req.getSession().getAttribute("user");
        /*如果用户未登录，loginUser为空，跳转到登录页面*/
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            /*若用户已经登录，可获取到User对象，通过user对象获取用户id*/
            Integer userId = loginUser.getId();
            /*调用orderService.createOrder(Cart cart,Integer userId) 保存订单和订单项, 得到订单号*/
            String orderId = orderService.createOrder(cart, userId);
            /*将订单号保存到session域中（需要在checkout.jsp页面输出）*/
            req.getSession().setAttribute("orderId",orderId);
            /*重定向到/pages/cart/checkout.jsp*/
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }
    }
}
