<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="az.orient.course.model.Payment" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 01.08.2020
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <script type="text/javascript">
$(function () {
$('.datePicker').datepicker({
    changeMonth:true,
    changeYear:true
});
$('#paymentTableId').DataTable({
    searching:false
});
    $('#accordion').accordion({
        collapsible:true
    });
    $('#advSearchBtnId').click(function () {
              advancedSearchPaymentData();
    });
    $('#advLessonComboId').change(function () {
         getTeacherComboByLessonId($(this).val());
    });
})
</script>
<div id="accordion">
    <h3>Advanced Search</h3>
    <div>
      <select id="advLessonComboId" class="comboDesign">
          <option value="0">Select Lesson</option>
          <c:forEach items="${getLessonList}" var="ll">
              <option value="${ll.id}">"${ll.lessonName}"</option>
          </c:forEach>
      </select> &nbsp;
        <select id="advTeacherComboId" class="comboDesign">
            <option value="0" >Select Teacher</option>
            <c:forEach items="${getTeacherlist}" var="tl">
                <option value="${tl.id}">${tl.name} ${tl.surname}</option>
            </c:forEach>
        </select><br>
        <input type="text"placeholder="Min amount..."id="minAmountId"/>&nbsp;
        <input type="text"placeholder="Max amount..." id="maxAmountId"/><br>
        <input type="text"placeholder="Begin Date..."id="beginDateId"class="datePicker"/>&nbsp;
        <input type="text"placeholder="End Date..." id="endDateId" class="datePicker"/>&nbsp;
        <input type="button" id="advSearchBtnId"value="Search"/>
    </div>

</div>
<div id="paymentTableDivId">
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
</div>
