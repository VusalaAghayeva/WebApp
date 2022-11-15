package az.orient.course.dao;

import az.orient.course.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getstudentList() throws Exception;

    boolean addStudent(Student student) throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    boolean updateStudent(Student student1, Long studentId) throws Exception;

    boolean deleteStudent(Long id) throws Exception;

    List<Student> searchStudentData(String keyword) throws Exception;
}

