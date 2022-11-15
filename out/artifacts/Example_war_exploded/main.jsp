<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 24.06.2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course</title>
    <script type="text/javascript" src="js/jquery/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet"type="text/css"href="css/layout.css"/>
</head>
<body>

<div id="container" >
<jsp:include page="static/header.jsp"></jsp:include>
    <jsp:include page="static/menu.jsp"></jsp:include>
    <div id="content">
 <%-- <form action="cs?action=registerStudent" method="post">
  <div class="lblDesign" > <label for="sName" >Name:</label></div>
  <input type="text"name="studentName" id="sName" required> <br>
  <div class="lblDesign" > <label for="sSurname">Surname:</label></div>
  <input type="text"name="studentSurname" id="sSurname"> <br>
  <div class="lblDesign" > <label for="sAddress">Address:</label></div>
  <input type="text" name="address" id="sAddress"><br>
  <div class="lblDesign" > <label for="sPassword">Password:</label></div>
  <input type="password"name="pwd" id="sPassword"><br>
  <div class="lblDesign" > <label for="gender">Gender:</label></div>
  <input type="radio" name="gender" value="kishi" id="gender">Male &nbsp;
  <input type="radio" name="gender"value="qadin">Female
  <br>
  <div > <label >Foreign Language:</label>  </div>
  <div class="lblDesign" ><input type="checkbox"name="forignLanh" value="eng" />Engilish <br>
  <input type="checkbox"name="forignLanh" value="tr"/>Turkish<br>
  <input type="checkbox"name="forignLanh" value="deu"/>Dutch<br>
  <input type="checkbox"name="forignLanh"value="fr"/>French<br>
  <input type="checkbox"name="forignLanh" value="rus"/>Russian
  </div>
  <br>
  <div class="lblDesign" > <label for="sMail">Email:</label></div>
  <input type="email" name="email"id="sMail"/> <br>
  <div class="lblDesign" > <label for="sCountry">Country:</label></div>
  <select name="country" id="sCountry">
      <option value="0" disabled selected>Select country </option>
       <option value="1">Azerbaijan</option>
      <option value="2">Turkish</option>
      <option value="3">Russian</option>
      <option value="4">England</option>
      <option value="5">Spain</option>
      <option value="6">Italy</option>
     <option value="7">Saudia Arabian</option>
  </select>
  <br>
  <div class="lblDesign" > <label for="strCountry">Travel Country:</label></div>
  <select name="Travel country" id="strCountry" multiple>
    <option value="0 " disabled selected>Select country </option>
    <option value="1">Azerbaijan</option>
    <option value="2">Turkish</option>
    <option value="3">Russian</option>
    <option value="4">England</option>
    <option value="5">Spain</option>
    <option value="6">Italy</option>
    <option value="7">Saudia Arabian</option>
  </select>
  <br>
  <div class="lblDesign" > <label for="note">Note:</label></div>
  <textarea name="note" cols="20" rows="3" id="note">
  </textarea>
  <br>
  <input type="submit"value="Send"> &nbsp;
  <input type="reset" value="Reset">
  </form>
 --%>
    <%--        <table border="1"  id="studentId" class="myClass" style="display: none">--%>
<%--            <caption>Student table</caption>--%>
<%--            <tr>--%>
<%--                <td>Aysel</td>--%>
<%--                <td>Hasanli</td>--%>
<%--                <td>Dashkesen</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Inci</td>--%>
<%--                <td>Musayeva</td>--%>
<%--                <td>Quba</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Dunya</td>--%>
<%--                <td>Nezerli</td>--%>
<%--                <td>Saatli</td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <table border="1" id="teacherId" class="myClass" style="display: none">--%>
<%--            <caption>Teacher table</caption>--%>
<%--            <tr>--%>
<%--                <td>Asif</td>--%>
<%--                <td>Hasanli</td>--%>
<%--                <td>Dashkesen</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Ali</td>--%>
<%--                <td>Musayev</td>--%>
<%--                <td>Quba</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Ilkin</td>--%>
<%--                <td>Nezerli</td>--%>
<%--                <td>Saatli</td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </div>--%>
   <jsp:include page="static/footer.jsp"></jsp:include>

</div>

</body>
</html>
