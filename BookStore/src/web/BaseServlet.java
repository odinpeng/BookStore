package web;
/*所有Servlet程序的根父类，实现了接收客户端请求，并通过反射的方式调用子类中的方法*/
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    /**
     * 接收客户端发送来的POST请求，通过请求的hidden属性调用对应的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*根据客户端隐藏属性，判断是哪个客户端发送来的请求*/
        String action = req.getParameter("action");
        try {
            /*通过反射调用客户端请求的方法，*/
            Method declaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e); //将异常抛给Filter过滤器
        }
    }

    /**
     * 调用doPost方法，使两种请求干同一件事
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
