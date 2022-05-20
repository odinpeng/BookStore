package web.older;
/*web层*/
/*视图展现层：获取客户端请求，调用Service层处理业务，响应客户端*/
/*处理注册页面业务逻辑*/
import pojo.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求参数*/
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code"); //验证码

        /*判断验证码是否正确(忽略大小写), 目前固定为： 6n6np*/
        String identifyingCode = "6n6np";
        boolean b = identifyingCode.equalsIgnoreCase(code);
        if (b) {
            /*验证码输入正确*/
            /*检查用户名是否可用*/
            boolean b1 = userService.existsUsername(username);
            if (b1) {
                /*b1==true:用户名已存在, 不可用，跳回注册页面 regist.jsp*/
                System.out.println("用户名已存在！");
                /*将错误信息保存到request域中，保存用户名和邮箱*/
                req.setAttribute("msg","用户名已存在！");

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                /*b1==false: 用户名不存在，可用, 将用户信息保存到数据库*/
                System.out.println("注册成功！");
                userService.registerUser(new User(null, username, password, email));
                /*跳转到注册成功的页面: /pages/user/regist_success.jsp*/
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else {
            /*验证码输入错误，跳回注册页面 regist.jsp*/
            System.out.println("验证码错误！");
            /*将错误信息保存到request域中，保存用户名和邮箱*/
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
