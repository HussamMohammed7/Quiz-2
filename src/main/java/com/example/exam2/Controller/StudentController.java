package com.example.exam2.Controller;


import com.example.exam2.Api.ApiResponse;
import com.example.exam2.Models.Student;
import com.example.exam2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor


public class StudentController {


    public final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity getAllStudents() {

        if (studentService.getStudents().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Student list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                studentService.getStudents()
        );

    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Student added successfully")
        );


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (studentService.updateStudent(id, student)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Student updated successfully")
            );
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Student not found")
            );
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {


        if (studentService.removeStudent(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Student deleted successfully")
            );
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Student not found or empty")
            );
        }
    }


    @GetMapping("/get/student/{name}")
    public ResponseEntity getOneStudentName(@PathVariable String name){

       Student student = studentService.getStudentByName(name);
       if (student == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   new ApiResponse("Student not found ")
           );
       }

       return ResponseEntity.status(HttpStatus.OK).body(
               student
       );

    }
    @GetMapping("/get/students/major/{major}")
    public ResponseEntity getStudentsMajor (@PathVariable String major){

        if (studentService.getStudentsByMajor(major).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("No student Study this major")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                studentService.getStudentsByMajor(major)
        );

    }





}
