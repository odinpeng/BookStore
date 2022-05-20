package web;
/*购物车的Servlet类*/
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();

    /**
     * 添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求参数的id*/
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        /*调用bookService.queryBookById查询图书信息*/
        Book book = bookService.queryBookById(id);
        /*将图书信息，转化为CartItem对象*/
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        /*调用Cart.addItem添加图书信息, 保存到session域中*/
        Cart cart =(Cart)req.getSession().getAttribute("cart");
        /*若此时首次添加商品，session中无cart对象，则得到的cart对象为空，需要创建cart对象*/
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        /*将最后一个添加的商品名称保存到Session域中*/
        req.getSession().setAttribute("lastItem",cartItem.getName());

        /*获取请求头Referer的值*/
        String referer = req.getHeader("Referer");
        /*重定向到index.jsp指定page的页面*/
        resp.sendRedirect(referer);
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求参数id*/
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        /*从Session域获取cart对象*/
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart!=null){
            /*调用cart.deleteItem删除指定商品*/
            cart.deleteItem(id);
        }
        /*重定向到购物车页面，cart.jsp*/
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*从Session域中获取Cart对象*/
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            /*调用cart.clear()方法清空购物车*/
            cart.clear();
        }
        /*重定向到购物车页面,cart.jsp*/
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品数量，（同时修改了商品总价格）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求参数id的值*/
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        /*从Session域中获取Cart对象*/
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart!=null){
            /*调用cart.updateCount方法修改商品数量*/
            cart.updateCount(id,count);
        }
        /*重定向到购物车页面*/
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
