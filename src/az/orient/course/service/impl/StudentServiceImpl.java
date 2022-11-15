package az.orient.course.service.impl;

import az.orient.course.dao.StudentDao;
import az.orient.course.model.Student;
import az.orient.course.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getstudentList() throws Exception {
        return studentDao.getstudentList();
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(Student student1, Long studentId) throws Exception {
        return studentDao.updateStudent(student1,studentId);
    }

    @Override
    public boolean deleteStudent(Long id) throws Exception {
        return studentDao.deleteStudent(id);
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        return studentDao.searchStudentData(keyword);
    }
}
