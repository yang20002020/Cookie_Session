<%@ page import="domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%
           if (request.getSession().getAttribute("existUser")==null) {
        %>
                  <h1> 您还没有登录！请先去<a href="/Cookie_web/login.jsp">登录</a></h1>
        <%
           }else{
        %>
                    <h1>用户登录成功！</h1>
               <%
                     User existUser =(User) request.getSession().getAttribute("existUser");
                %>
                   <h3>您好:<%= existUser.getNickname()%> <a href="/Cookie_web/LogoutServlet">退出</a></h3>
        <%
           }
        %>

    </body>
</html>
