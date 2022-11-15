<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16.08.2020
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

</script>
<table id="paymentTableId" class="display" style="width:100%">
    <thead><tr>
        <th>#</th>
        <th>Student Fullname</th>
        <th>Lesson Name</th>
        <th>Teacher Fullname</th>
        <th>Amount(AZN)</th>
        <th>Payment Date</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody> <c:forEach items="${paymentList}" var="pl">
        <tr>
            <td>${pl.id}</td>
            <td>${pl.student.name} ${pl.student.surname}</td>
            <td>${pl.lesson.lessonName}</td>
            <td>${pl.teacher.name} ${pl.teacher.surname}</td>
            <td>${pl.amount}</td>
            <td>${pl.payDate}</td>
            <td><a href="javascript:editPayment('${pl.id}')">Edit</a></td>
            <td><a href="javascript:deletePayment('${pl.id}')">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>