package com.example.quiz2.Controller;


import com.example.quiz2.Api.ApiResponse;
import com.example.quiz2.Model.Teacher;
import com.example.quiz2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher")

public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity getAllTeachers(){
        ArrayList<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/post")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable int id , @Valid @RequestBody Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = teacherService.updateTeacher(id,teacher);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher Not Found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable int id){
        boolean isDeleted = teacherService.deleteTeacher(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher Not Found"));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Teacher teacher = teacherService.getByID(id);

        if(teacher == null){
            return ResponseEntity.status(400).body(new ApiResponse("Teacher Not Found"));
        }
        return ResponseEntity.status(200).body(teacher);
    }

    @GetMapping("/getbysalary/{salary}")
    public ResponseEntity getBySalary(@PathVariable double salary){
        ArrayList<Teacher> teachers = teacherService.getBySalary(salary);
        if(teachers.size()==0){
            return ResponseEntity.status(400).body(new ApiResponse("Teacher Not Found"));
        }
        return ResponseEntity.status(200).body(teachers);
    }


}
