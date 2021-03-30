package org.example.DAO;

import org.example.HibernateUtil;
import org.example.Model.StudentEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDaoImpl implements StudentDAO{
    Session session;
    @Override
    public void addStudent(StudentEntity studentEntity) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(studentEntity);
        session.getTransaction().commit();
        System.out.println("add studentEntity");
    }

    @Override
    public StudentEntity getStudentById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        StudentEntity studentEntity = session.find(StudentEntity.class, id);
        session.getTransaction().commit();
        return studentEntity;
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<StudentEntity> studentList = session.createQuery("From StudentEntity ").list();
        session.getTransaction().commit();
        return studentList;
    }

    @Override
    public void deleteStudent(int id) {
        StudentEntity studentEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        studentEntity = session.find(StudentEntity.class, id);
        if (studentEntity != null){
            session.delete(studentEntity);
            session.flush();
            System.out.println("delete studentEntity");
        }else{
            System.out.println("studentEntity does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public StudentEntity updateStudent(StudentEntity student) {
        StudentEntity studentEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        studentEntity = session.find(StudentEntity.class, student.getId());
        if (studentEntity != null){
            studentEntity.setFirstName(student.getFirstName());
            studentEntity.setLastName(student.getLastName());
            studentEntity.setEmail(student.getEmail());
            studentEntity.setPassword(student.getPassword());
            studentEntity.setPhone(student.getPhone());
            studentEntity.setRole(student.getRole());
            studentEntity.setReservationMax(student.getReservationMax());
            session.flush();
            session.getTransaction().commit();
            System.out.println("studentEntity update");
        }else{
            session.getTransaction().commit();

            System.out.println("studentEntity does not exist");

        }
        return studentEntity;
    }
}
