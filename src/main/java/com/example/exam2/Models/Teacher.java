package com.example.exam2.Models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {




    @NotEmpty(message = "id should be not empty")
    private String ID ;

    @NotEmpty(message = "name should be not empty")
    private String name;


    @NotNull(message = "salary should be not null")
    @Positive(message = "salary should be a number")
    private double salary;

}
