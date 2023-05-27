package controller;



import domain.User;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/*

 */
@WebServlet(urlPatterns ="/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入LoginServlet》》》》》》》》》》》》");
       try {
               // 1.接收数据
               // 处理中文乱码
               request.setCharacterEncoding("UTF-8");
               String username = request.getParameter("username");
               String password = request.getParameter("password");
               //接收验证码
               String checkcode1=request.getParameter("checkcode");
               //从session中获取一次性验证码的值：
               String checkcode2=(String)request.getSession().getAttribute("checkcode");
               //为了保证验证码使用一次：应该将session中的验证码值清空
               request.getSession().removeAttribute("checkcode");
               //校验一次性验证码  equalsIgnoreCase  忽略大小写
               if(!checkcode1.equalsIgnoreCase(checkcode2)){
                   request.setAttribute("msg","验证码输入错误");
                   request.getRequestDispatcher("/login.jsp").forward(request,response);
                   return;
               }
               // 2.封装数据
               User user = new User();
               user.setUsername(username);
               user.setPassword(password);
               // 3.处理数据
               UserModel userModel = new UserModel();
               User existUser = userModel.login(user);
               // 4.页面跳转
               if (existUser == null) {
                   // 登录失败
                   // 向request域中保存一个错误信息：
                   request.setAttribute("msg", "用户名或密码错误！");
                   // 使用请求转发进行页面跳转
                   request.getRequestDispatcher("/login.jsp").forward(request, response);
               } else {
                   // 登录成功
                   //保存用户信息,保存到会话当中
                   HttpSession session=request.getSession();
                   //保存数据
                   session.setAttribute("existUser",existUser);
                   // 重定向到成功页面
                   response.sendRedirect("/Cookie_web/success.jsp");
               }
       }catch (Exception e){
          e.printStackTrace();
          throw new RuntimeException(); //使程序终止
       }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost>>>>>>>>>>>>>>>>>>>>>");
        doGet(req, resp);
    }
}