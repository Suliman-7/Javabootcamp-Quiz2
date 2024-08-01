package com.example.quiz2.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @NotNull(message = "teacher id should be not empty")
    private int  id;

    @NotEmpty(message = "teacher name should be not empty")
    private String name;

    @NotNull(message = "teacher salary should be not empty")
    private double salary;
}
