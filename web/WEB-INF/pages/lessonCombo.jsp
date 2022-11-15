<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 30.07.2020
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option value="0" disabled>Select Lesson</option>
<c:forEach items="${getLessonList}" var="ll">
    <option value="${ll.id}">"${ll.lessonName}"</option>
</c:forEach>