<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.07.2020
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
   $(function () {
     $('#dobId').datepicker({
         changeMonth: true,
         changeYear: true
     });
   });
</script>
<div class="lblDesign" > <label for="sName" >Name:</label></div>
<input type="text" id="sName" required> <br>
<div class="lblDesign" > <label for="sSurname">Surname:</label></div>
<input type="text" id="sSurname"> <br>
<div class="lblDesign" > <label for="sAddress">Address:</label></div>
<input type="text"  id="sAddress"><br>
<div class="lblDesign" > <label for="dobId">Date of Birth:</label></div>
<input type="text"  id="dobId"><br>
<div class="lblDesign" > <label for="phoneId">Phone:</label></div>
<input type="text"  id="phoneId"><br>
<div class="lblDesign" > <label for="emailId">Email:</label></div>
<input type="email"  id="emailId"><br>