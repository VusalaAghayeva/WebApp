<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23.06.2020
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Layout Example</title>
    <script type="text/javascript" src="js/jquery/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/combo.js"></script>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/main..css">
    <link rel="stylesheet"type="text/css"href="css/jquery.dataTables.min.css">
</head>
<body>
<%
    HttpSession session1=request.getSession(false);
    if(session1.getAttribute("login")==null || session1.getAttribute("login").equals(""))
    {
        response.sendRedirect("login.jsp");
    }
%>
<div class="ui-layout-north">
    <h1 style="text-align: center" class="classA">Course</h1>
<div style="float: right">
Welcome, ${login.name} ${login.surname}
    <a href="logout.jsp"><img src="images/logout.png" height="40px" width="40px"></a>
</div>
</div>
<div class="ui-layout-center">

</div>
<div class="ui-layout-west">
    <input type="button" value="Student data" class="btnDesign" id="studentDataBtnId"/> <br>
    <input type="button" value="Teacher data" class="btnDesign" id="teacherDataBtnId"/> <br>
    <input type="button" value="Lesson data" class="btnDesign" id="lessonBtnId"/> <br>
    <input type="button" value="Payment data" class="btnDesign" id="paymentBtnId"/> <br>
</div>
<div class="ui-layout-east">
    <input type="button" value="New" id="newBtnId" class="btnDesign1">
    <input type="text" id="keywordId" placeholder="Search" style="width: 64%"/>
    <input type="button"id="searchBtnId"value="Search" style="width: 33%"/>

</div>
<div class="ui-layout-south">
    <div style="text-align: center">Copyright @FuadPashabeyli</div>
</div>
<div id="newStudentDialogId"></div>
<div id="newTeacherDialogId"></div>
<div id="newPaymentDialogId"></div>
<div id="editStudentDialogId"></div>
<div id="updatePaymentDialogId"></div>
</body>
</html>
