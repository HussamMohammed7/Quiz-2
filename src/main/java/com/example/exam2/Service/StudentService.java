package com.example.exam2.Service;


import com.example.exam2.Models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service


public class StudentService {

    ArrayList<Student>students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students ;
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id ,Student student) {

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;

    }

    public boolean removeStudent(String id ) {
        if (students.isEmpty()){
            return false;
        }

        for(Student student : students) {
            if (student.getID().equals(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;

    }

    public Student getStudentByName(String name){
        for(Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }

        return null;
    }

    public ArrayList<Student> getStudentsByMajor(String major) {

        ArrayList<Student> studentsMajor = new ArrayList<>();
        for(Student student : students) {
            if(student.getMajor().equalsIgnoreCase(major)){
                studentsMajor.add(student);
            }
        }
        return studentsMajor;
    }








}
