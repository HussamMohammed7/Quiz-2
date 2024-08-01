package com.example.exam2.Controller;


import com.example.exam2.Api.ApiResponse;
import com.example.exam2.Models.Student;
import com.example.exam2.Models.Teacher;
import com.example.exam2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor

public class TeacherController {

    public final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity getAllTeachers() {

        if (teacherService.getTeachers().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Teachers list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                teacherService.getTeachers()
        );

    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Teacher added successfully")
        );


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @Valid @RequestBody Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (teacherService.updateTeacher(id, teacher)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Teacher updated successfully")
            );
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Teacher not found")
            );
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id) {


        if (teacherService.removeTeacher(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Teacher deleted successfully")
            );
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Teacher not found or empty")
            );
        }
    }

    @GetMapping("/get/teacher/{id}")
    public ResponseEntity getTeacher(@PathVariable String id) {
        if (teacherService.getTeachers().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Teachers list is empty")
            );
        }
        if (teacherService.getTeacherById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("Teacher not found")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                teacherService.getTeacherById(id)
        );
    }

    @GetMapping("/get/teachers/{salary}")
    public ResponseEntity getTeacherSalary(@PathVariable double salary) {

        if (teacherService.getTeachers().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Teachers list is empty")
            );
        }
        if (teacherService.getTeacherSalary(salary).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("No Teacher have this salary or above")
            );

        }
        return ResponseEntity.status(HttpStatus.OK).body(
                teacherService.getTeacherSalary(salary)
        );
    }



}
