package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

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
        /*设置url*/
        page.setUrl("client/bookServlet?action=page");
        /*将page保存到request域中*/
        req.setAttribute("page",page);
        /*请求转发到/pages/manager/book_manager.jsp*/
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 价格区间搜索，根据价格分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求的pageNo和pageSize,min,max*/
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize= WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE); //客户端没传入则用常量
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        /*调用BookService.pageByPrice获取page对象*/
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        /*设置Url*/
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        /*将page保存到request域中*/
        req.setAttribute("page",page);
        /*请求转发到/pages/manager/book_manager.jsp*/
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
