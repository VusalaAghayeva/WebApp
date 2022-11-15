<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 08.08.2020
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#studentComboId1').val('${payment.student.id}');
        $('#teacherComboId1').val('${payment.teacher.id}');
        $('#lessonComboId1').val('${payment.lesson.id}');
    });
</script>
<div class="lblDesign">Student:</div>
<select id="studentComboId1" class="comboDesign">
    <option value="0" disabled>Select Student</option>
    <c:forEach items="${getStudentList}" var="sl">
        <option value="${sl.id}">${sl.name} ${sl.surname}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Teacher:</div>
<select id="teacherComboId1" class="comboDesign">
    <option value="0" disabled>Select Teacher</option>
    <c:forEach items="${getTeacherlist}" var="tl">
        <option value="${tl.id}">${tl.name} ${tl.surname}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Lesson:</div>
<select id="lessonComboId1" class="comboDesign">
    <option value="0"disabled>Select Lesson</option>
    <c:forEach items="${getLessonList}" var="ll">
        <option value="${ll.id}">"${ll.lessonName}"</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Amount:</div>
<input type="text" id="amountId1" class="comboDesign" value="${payment.amount}">