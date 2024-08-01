package com.example.exam2.Service;

import com.example.exam2.Models.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers ;
    }


    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean updateTeacher(String id ,Teacher teacher) {

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getID().equals(id)) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;

    }

    public boolean removeTeacher(String id ) {
        if (teachers.isEmpty()){
            return false;
        }

        for(Teacher teacher : teachers) {
            if (teacher.getID().equals(id)) {
                teachers.remove(teacher);
                return true;
            }
        }
        return false;

    }
    public Teacher getTeacherById(String id) {
        if (teachers.isEmpty()){
            return null;
        }
        for(Teacher teacher : teachers) {
            if (teacher.getID().equals(id)) {
                return teacher;
            }
        }
        return null;

    }

    public ArrayList<Teacher> getTeacherSalary(double salary) {
        ArrayList<Teacher> teacherSalary = new ArrayList<>();
        if (teachers.isEmpty()){
            return null;
        }

        for(Teacher teacher : teachers) {
            if (teacher.getSalary() >= salary) {
                teacherSalary.add(teacher);
            }
        }
        return teacherSalary;

    }







}
