<%@ page import="java.util.List" %>
<%@ page import="az.orient.course.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 24.06.2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Student>studentList= (List<Student>) request.getAttribute("studentList");%>
<html>
<head>
    <title>Course</title>
    <script type="text/javascript" src="js/jquery/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet"type="text/css"href="css/layout.css"/>
</head>
<body>
<div id="container" >
    <jsp:include page="/static/header.jsp"></jsp:include>
    <jsp:include page="/static/menu.jsp"></jsp:include>
    <div id="content">
        <table border="1"  id="studentId" class="myClass" >
            <caption>Student table</caption>
            <tbody>
                <%for (Student student:studentList)
                {%>
                <tr>
                <td><%=student.getId()%></td>
                <td><%=student.getName()%></td>
                <td><%=student.getSurname()%></td>
                <td><%=student.getAdress()%></td>
                <td><%=student.getDob()%></td>
                </tr>
                <% }%>
            </tbody>
        </table>
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
    </div>
    <jsp:include page="/static/footer.jsp"></jsp:include>
</div>
</body>
</html>
