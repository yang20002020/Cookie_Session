<%--
  Created by IntelliJ IDEA.
  User: 26286
  Date: 2023/5/26
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String msg = "";
    if(request.getAttribute("msg") != null){
        // 有错误信息：显示错误信息:
        msg = (String)request.getAttribute("msg");
    }
%>
<h1>登录页面</h1>
<h3> <font color="red"><%= msg%></font></h3>
<form action="/Cookie_web/LoginServlet" method="post">
    <table border="1" width="500">
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkcode" size="6"/></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="remember" value="true"/></td>
            <td>记住用户名</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"/></td>
        </tr>
    </table>
</form>
</body>
</html>
