<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 30.07.2020
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option value="0"  disabled>Select Student</option>
<c:forEach items="${getStudentList}" var="sl">
<option value="${sl.id}">${sl.name} ${sl.surname}</option>
</c:forEach>
