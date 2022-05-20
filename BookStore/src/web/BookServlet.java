package web;
/*manager管理模块，处理图书管理（图书的增删改）*/
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 添加图书页面将获取的参数 发送请求给BookServlet程序
     * 通过WebUtils工具类，获取请求的参数，封装为Bean对象
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        /*获取请求的参数，封装为Bean对象*/
        Book book = WebUtils.copyParameterToBean(req.getParameterMap(), new Book());
        /*调用BookService.addBook添加书籍*/
        bookService.addBook(book);
        /*用此方式用户按下F5功能键会发起最后一次请求（add方法），会导致表单重复提交
        req.getRequestDispatcher("manager/bookServlet?action=list").forward(req,resp);*/

        /*使用表单重定向
        请求转发到
        manager/bookServlet?action=list ：再次调用list方法，将数据存储到request域中
        并跳转到/pages/manager/book_manager.jsp显示图书管理界面*/
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }

    /**
     * 实现在订单管理系统中对书籍的删除操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求id*/
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id,0);
        /*调用BookService.deleteBookById删除书籍*/
        bookService.deleteBookById(i);
        /*重定向请求转发到manager/bookServlet?action=list
         调用list方法展示/pages/manager/book_manager.jsp*/
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

     /**
     * update()的子方法
     * 获取要修改的图书的信息，将其保存到book_edit.jsp页面中
     *将获取的信息保存到book_edit.jsp页面的修改栏中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求id*/
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id,0);
        /*调用BookService.queryBookById方法获取此id对应的图书信息*/
        Book book = bookService.queryBookById(i);
        /*将查询到的信息保存到request域中*/
        req.setAttribute("book", book);
        /*跳转到/pages/manager/book_edit.jsp页面*/
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 实现在订单管理系统中对书籍的更改
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求的参数，封装为Bean对象*/
        Book book = WebUtils.copyParameterToBean(req.getParameterMap(), new Book());
        /*调用BookService.updateBook修改图书*/
        bookService.updateBook(book);
        /*请求重定向转发到manager/bookServlet?action=list
        调用list方法展示/pages/manager/book_manager.jsp*/
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 处理分页操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求的pageNo和pageSize*/
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize= WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE); //客户端没传入则用常量
        /*调用BookService.page获取page对象*/
        Page<Book> page = bookService.page(pageNo, pageSize);
        /*将page保存到request域中*/
        req.setAttribute("page",page);
        /*请求转发到/pages/manager/book_manager.jsp*/
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

        /**
         * 通过调用BookService.queryBooks查询t_book数据表中信息的方式
         * 将数据保存到request域中
         * 将表中数据展现到图书管理页面上
         * 跳转到(pages/manager/book_manager.jsp)
         *
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*通过BookService查询全部图书*/
        List<Book> books = bookService.queryBooks();
        /*将图书信息保存到request域中*/
        req.setAttribute("books", books);
        /*请求转发到 图书管理/pages/manager/book_manager.jsp页面中*/
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
