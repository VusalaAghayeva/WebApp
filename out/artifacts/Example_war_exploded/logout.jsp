<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 18.08.2020
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
session.removeAttribute("login");
session.invalidate();
response.sendRedirect("login.jsp");//sehifeye yonlendirir (forward ise nese yazini gonderir)
%>