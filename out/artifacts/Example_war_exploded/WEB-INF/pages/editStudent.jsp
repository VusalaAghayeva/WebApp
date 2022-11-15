<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06.08.2020
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#dobId1').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>
<div class="lblDesign" > <label for="sName1" >Name:</label></div>
<input type="text" id="sName1" required value="${student.name}"> <br>
<div class="lblDesign" > <label for="sSurname1">Surname:</label></div>
<input type="text" id="sSurname1" value="${student.surname}"> <br>
<div class="lblDesign" > <label for="sAddress1">Address:</label></div>
<input type="text"  id="sAddress1" value="${student.adress}"><br>
<div class="lblDesign" > <label for="dobId1">Date of Birth:</label></div>
<input type="text"  id="dobId1" value="${student.dob}"><br>
<div class="lblDesign" > <label for="phoneId1">Phone:</label></div>
<input type="text"  id="phoneId1" value="${student.phone}"><br>
<div class="lblDesign" > <label for="emailId1">Email:</label></div>
<input type="email"  id="emailId1" value="${student.email}"><br>
