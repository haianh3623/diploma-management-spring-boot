package com.globits.qlvbcc.rest;

import com.globits.qlvbcc.dto.StudentDto;
import com.globits.qlvbcc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class RestStudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/get-all")
    public List<StudentDto> getAll(){
        return studentService.getAll();
    }

    @PostMapping("/add")
    public StudentDto addStudent(@RequestBody StudentDto dto){
        return studentService.addStudent(dto);
    }

    @PostMapping("/update/{id}")
    public StudentDto updateStudent(@RequestBody StudentDto dto, @PathVariable Long id){
        return studentService.updateStudent(dto, id);
    }

    @DeleteMapping("/remove/{id}")
    public StudentDto removeStudent(@PathVariable Long id){
        return studentService.removeStudent(id);
    }

    @GetMapping("/get/{id}")
    public StudentDto getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

}
