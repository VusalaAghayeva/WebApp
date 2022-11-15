<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="az.orient.course.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20.07.2020
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#studentTableId').DataTable();
    })
</script>
<table id="studentTableId" class="display" style="width:100%">
    <thead>
    <tr>
        <td>#</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Address</td>
        <td>Date of Birth</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="sl">
        <tr>
            <td>${sl.id}</td>
            <td>${sl.name} </td>
            <td>${sl.surname}</td>
            <td>${sl.adress}</td>
            <c:choose>
                <c:when test="${not empty sl.dob}">
                    <td>${sl.dob}</td>
                </c:when>
                <c:otherwise>
                    <td>Yoxdur</td>
                </c:otherwise>
            </c:choose>
            <td><a href="javascript:editStudent('${sl.id}');">Edit</a></td>
            <td><a href="javascript: deleteStudent('${sl.id}','${sl.name} ${sl.surname}');">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td>#</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Address</td>
        <td>Date of Birth</td>
    </tr>
    </tfoot>
</table>

