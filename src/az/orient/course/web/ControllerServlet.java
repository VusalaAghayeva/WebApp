package az.orient.course.web;

import az.orient.course.dao.*;
import az.orient.course.dao.impl.*;
import az.orient.course.model.*;
import az.orient.course.service.*;
import az.orient.course.service.impl.*;
import az.orient.course.util.Constants;
import jdk.jfr.events.FileWriteEvent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ControllerServlet", urlPatterns = "/cs")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
        PrintWriter pw = response.getWriter();
        String action = null;
        String address = null;
        StudentDao studentDao = new StudentDaoImpl();
        StudentService studentService = new StudentServiceImpl(studentDao);
        TeacherDao teacherDao = new TeacherDaoImpl();
        TeacherService teacherService = new TeacherServiceImpl(teacherDao);
        LessonDao lessonDao = new LessonDaoImpl();
        LessonService lessonService = new LessonServiceImpl(lessonDao);
        PaymentDao paymentDao = new PaymentDaoImpl();
        PaymentService paymentService = new PaymentServiceImpl(paymentDao);
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action").trim();
                System.out.println(action);// trim istifade edin. artiq spacelari silsin deye
            } // bu if burda menasi yoxdu . onsuzda action null olacaq. if ishlemese

            if (action.equalsIgnoreCase("getStudentList")) {
                List<Student> studentList = studentService.getstudentList();
                request.setAttribute("studentList", studentList);
                address = "/WEB-INF/pages/studentList.jsp";
            } else if (action.equals("getStudentListAjax")) { // equals istifade etmek yaxshidi
                List<Student> studentList = studentService.getstudentList();
                request.setAttribute("studentList", studentList);
                address = "/WEB-INF/pages/studentListAjax.jsp";//table olan sehifeye yonlendirirem deye,studentListAjax-dan bir -br elementleri goturruk jsp-de
            } else if (action.equalsIgnoreCase("registerStudent")) {

            } else if (action.equalsIgnoreCase("editStudent")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                Student student = studentService.getStudentById(studentId);
                request.setAttribute("student", student);
                address = "/WEB-INF/pages/editStudent.jsp";


            } else if (action.equalsIgnoreCase("deleteStudent")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                boolean isDelete = studentService.deleteStudent(studentId);
                response.setContentType("text/html");//asagidaki setir islesin deye
                if (isDelete) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("searchStudentData")) {
                String keyword = request.getParameter("keyword");
                List<Student> studentList = studentService.searchStudentData(keyword);
                request.setAttribute("studentList", studentList);
                address = "/WEB-INF/pages/studentListAjax.jsp";
            } else if (action.equalsIgnoreCase(Constants.SEARCH_PAYMENT_DATA)) {
                String keyword = request.getParameter("keyword");
                List<Payment> paymentList = paymentService.searchPaymentData(keyword);
                List<Teacher> getTeacherlist = teacherService.getTeacherList();
                List<Lesson> getLessonList = lessonService.getLessonList();
                request.setAttribute("paymentList", paymentList);
                request.setAttribute("getTeacherlist", getTeacherlist);
                request.setAttribute("getLessonList", getLessonList);
                address = "/WEB-INF/pages/paymentList.jsp";
            } else if (action.equalsIgnoreCase(Constants.ADVANCED_SEARCH_PAYMENT_DATA)) {
                String min1Amount = request.getParameter("minAmount");
                String max1Amount = request.getParameter("maxAmount");
                Double minAmount = null;
                Double maxAmount = null;
                Long advLessonCombo = null;
                if (advLessonCombo != null) {
                    advLessonCombo = Long.parseLong(request.getParameter("advLessonCombo"));
                }
                Long advTeacherCombo = null;
                if (advTeacherCombo != null) {
                    advTeacherCombo = Long.parseLong(request.getParameter("advTeacherCombo"));

                }
                if (min1Amount != null && !min1Amount.isEmpty()) {
                    minAmount = Double.parseDouble(min1Amount);

                }
                if (max1Amount != null && !max1Amount.isEmpty()) {
                    maxAmount = Double.parseDouble(max1Amount);

                }
                String beginDate = request.getParameter("beginDate");
                String endDate = request.getParameter("endDate");
                AdvancedSearch advancedSearch = new AdvancedSearch();
                advancedSearch.setLessonId(advLessonCombo);
                advancedSearch.setTeacherId(advTeacherCombo);
                advancedSearch.setMinAmount(minAmount);
                advancedSearch.setMaxAmount(maxAmount);
                advancedSearch.setBeginDate(beginDate);
                advancedSearch.setEndDate(endDate);
                List<Payment> paymentList = paymentService.advancedSearchPaymentData(advancedSearch);
                request.setAttribute("paymentList", paymentList);
                address = "WEB-INF/pages/paymentData.jsp";

            } else if (action.equalsIgnoreCase(Constants.GET_TEACHER_COMBO_BY_LESSON_ID)) {
                Long lessonId = Long.parseLong(request.getParameter("lessonId"));
                System.out.println(lessonId);
                List<Teacher> teacherList = teacherService.getTeacherComboByLessonId(lessonId);
                request.setAttribute("teacherList", teacherList);
                address = "WEB-INF/pages/teacherCombo.jsp";
            } else if (action.equalsIgnoreCase("editPayment")) {
                System.out.println("Salam");
                try {
                    List<Student> getStudentList = studentService.getstudentList();
                    List<Teacher> getTeacherlist = teacherService.getTeacherList();
                    List<Lesson> getLessonList = lessonService.getLessonList();
                    request.setAttribute("getStudentList", getStudentList);
                    request.setAttribute("getTeacherlist", getTeacherlist);
                    request.setAttribute("getLessonList", getLessonList);
                    Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                    Payment payment = paymentService.getPaymentById(paymentId);
                    request.setAttribute("payment", payment);
                    address = "/WEB-INF/pages/editPayment.jsp";
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } else if (action.equalsIgnoreCase("updatePayment")) {
                Long studentCombo = Long.parseLong(request.getParameter("studentCombo"));
                Long teacherCombo = Long.parseLong(request.getParameter("teacherCombo"));
                Long lessonCombo = Long.parseLong(request.getParameter("lessonCombo"));
                Double amount = Double.parseDouble(request.getParameter("amount"));
                Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                Payment payment = new Payment();
                payment.setId(paymentId);
                Student student = new Student();
                student.setId(studentCombo);
                Teacher teacher = new Teacher();
                teacher.setId(teacherCombo);
                Lesson lesson = new Lesson();
                lesson.setId(lessonCombo);
                payment.setStudent(student);
                payment.setTeacher(teacher);
                payment.setLesson(lesson);
                payment.setAmount(amount);
                boolean isAdded = paymentService.updatePayment(payment);
                response.setContentType("text/html");//asagidaki setir islesin deye
                if (isAdded) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("updateStudent")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String addres = request.getParameter("address");
                String dob = request.getParameter("dob");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                Student student = new Student();
                student.setName(name);
                student.setSurname(surname);
                student.setAdress(addres);
                student.setPhone(phone);
                student.setEmail(email);
//                if(dob!=null) {
//                    student.setDob(df.parse(dob));
                student.setDob(dob);

                boolean isUpdate = studentService.updateStudent(student, studentId);
                response.setContentType("text/html");//asagidaki setir islesin deye
                if (isUpdate) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("getTeacherList")) {

            } else if (action.equals("getStudentCombo")) {
                List<Student> getStudentList = studentService.getstudentList();
                request.setAttribute("getStudentList", getStudentList);
                address = "/WEB-INF/pages/studentCombo.jsp";
            } else if (action.equalsIgnoreCase("getTeacherCombo")) {
                List<Teacher> getTeacherlist = teacherService.getTeacherList();
                request.setAttribute("getTeacherlist", getTeacherlist);
                address = "/WEB-INF/pages/teacherCombo.jsp";

            } else if (action.equalsIgnoreCase("getLessonCombo")) {
                List<Lesson> getLessonList = lessonService.getLessonList();
                request.setAttribute("getLessonList", getLessonList);
                address = "/WEB-INF/pages/lessonCombo.jsp";

            } else if (action.equals("newPayment")) {
                List<Student> getStudentList = studentService.getstudentList();
                List<Teacher> getTeacherlist = teacherService.getTeacherList();
                List<Lesson> getLessonList = lessonService.getLessonList();
                request.setAttribute("getStudentList", getStudentList);
                request.setAttribute("getTeacherlist", getTeacherlist);
                request.setAttribute("getLessonList", getLessonList);
                address = "/WEB-INF/pages/newPayment.jsp";
            } else if (action.equals("addPayment")) {

                Long studentCombo = Long.parseLong(request.getParameter("studentCombo"));
                Long teacherCombo = Long.parseLong(request.getParameter("teacherCombo"));
                Long lessonCombo = Long.parseLong(request.getParameter("lessonCombo"));
                Double amount = Double.parseDouble(request.getParameter("amount"));
                Payment payment = new Payment();
                Student student = new Student();
                student.setId(studentCombo);
                Teacher teacher = new Teacher();
                teacher.setId(teacherCombo);
                Lesson lesson = new Lesson();
                lesson.setId(lessonCombo);
                payment.setStudent(student);
                payment.setTeacher(teacher);
                payment.setLesson(lesson);
                payment.setAmount(amount);
                boolean isAdded = paymentService.addPayment(payment);
                response.setContentType("text/html");//asagidaki setir islesin deye
                if (isAdded) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equals("getPaymentList")) {
                List<Payment> paymentList = paymentService.getPaymentList();
                List<Teacher> getTeacherlist = teacherService.getTeacherList();
                List<Lesson> getLessonList = lessonService.getLessonList();
                request.setAttribute("paymentList", paymentList);
                request.setAttribute("getTeacherlist", getTeacherlist);
                request.setAttribute("getLessonList", getLessonList);
                address = "/WEB-INF/pages/paymentList.jsp";
            } else if (action.equals("addStudent")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String addres = request.getParameter("address");
                String dob = request.getParameter("dob");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                Student student = new Student();
                student.setName(name);
                student.setSurname(surname);
                student.setAdress(addres);
                student.setPhone(phone);
                student.setEmail(email);
//                if(dob!=null) {
//                    student.setDob(df.parse(dob));
                student.setDob(dob);

                boolean isAdded = studentService.addStudent(student);
                response.setContentType("text/html");//asagidaki setir islesin deye
                if (isAdded) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("forgetView")) {
                address = "forget.jsp";
            } else if (action.equalsIgnoreCase("forgetPassword")) {
                String email = request.getParameter("email");
                if (email != null && !email.isEmpty()) {
                    boolean isExist=loginService.checkMail(email);
                    if(isExist)
                    {
                        String token = UUID.randomUUID().toString();
                        boolean isUpdated = loginService.updateTokenForEmail(token, email);//bu metod uun evvelce token yaratmaliyiq.
                        if (isUpdated) {
                            String changeMailUrl = "http://localhost:8080/Example_war_exploded/cs?action=changePasswordView&token=" + token;
                            boolean isSendMail = util.Methods.sendMail(email, "Change mail", changeMailUrl);
                            if (isSendMail) {
                                request.setAttribute("message", "succes");
                                address = "success.jsp";
                            } else {
                                request.setAttribute("invalid", "Mail gonderilmedi!");
                                address = "forget.jsp";
                            }
                        } else {
                            request.setAttribute("invalid", "Gizli kod yaradila bilmedi!");
                            address = "success.jsp";
                        }
                    }
                    else
                    {
                        request.setAttribute("invalid", "Bele bir mail movcud deyil!");
                        address = "forget.jsp";
                    }

                }

            } else if (action.equalsIgnoreCase("changePasswordView")) {
                String token = request.getParameter("token");
                request.setAttribute(token, "token");
                address = "changePassword.jsp";
            } else if (action.equalsIgnoreCase("changePassword")) {
                String password = request.getParameter("password");
                String token = request.getParameter("token");
                boolean isChange = loginService.changePasswordWithToken(password, token);
                if (isChange) {
                   request.setAttribute("message","succes");
                   address="login.jsp";
                } else {
                      request.setAttribute("invalid","Sifreni deyisen zaman xeta bash verdi!");
                      address="changePassword.jsp";
                }
            } else {
                System.out.println("Email bosdur!");
                System.out.println("action value not match");
                System.out.println("debug info: action = " + action);
                System.out.println("action is null = " + (action == null));
            }

        } catch (Exception ex) {
            System.out.println("[WARN]\tError ocurred when retrive student list. stack trace");
            ex.printStackTrace();
        }
        if (address != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
            requestDispatcher.forward(request, response);


        }

    }
}
