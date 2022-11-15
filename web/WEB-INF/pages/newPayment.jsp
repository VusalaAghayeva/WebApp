<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29.07.2020
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="lblDesign">Student:</div>
<select id="studentComboId" class="comboDesign">
    <option value="0"  >Select Student</option>
    <c:forEach items="${getStudentList}" var="sl">
        <option value="${sl.id}">${sl.name} ${sl.surname}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Teacher:</div>
<select id="teacherComboId"class="comboDesign">
    <option value="0"  >Select Teacher</option>
    <c:forEach items="${getTeacherlist}" var="tl">
        <option value="${tl.id}">${tl.name} ${tl.surname}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Lesson:</div>
<select id="lessonComboId"class="comboDesign">
    <option value="0">Select Lesson</option>
    <c:forEach items="${getLessonList}" var="ll">
        <option value="${ll.id}">"${ll.lessonName}"</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Amount:</div>
<input type="text"id="amountId" class="comboDesign">