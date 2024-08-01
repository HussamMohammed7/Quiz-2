package com.example.exam2.Models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {




    @NotEmpty(message = "id should be not empty")
    private String ID ;
    @NotEmpty(message = "name should be not empty")
    private String name;
    @NotNull(message = "age should be not null")
    @Positive(message = "age should be positive number")
    private int age ;
    @NotEmpty(message = "major should be not empty")
    private String major;


}
