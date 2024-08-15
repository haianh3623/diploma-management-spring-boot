package com.globits.qlvbcc.rest;

import com.globits.qlvbcc.domain.NumOfDiploma;
import com.globits.qlvbcc.dto.DiplomaDto;
import com.globits.qlvbcc.dto.SchoolDto;
import com.globits.qlvbcc.dto.StudentDto;
import com.globits.qlvbcc.service.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diploma")
public class RestDiplomaController {

    @Autowired
    DiplomaService diplomaService;

    @GetMapping("/get-all")
    public List<DiplomaDto> getAll(){
        return diplomaService.getAll();
    }

    @PostMapping("/update/{id}")
    public DiplomaDto updateDiploma(@RequestBody DiplomaDto dto, @PathVariable Long id){
        return diplomaService.updateDiploma(dto, id);
    }

    @DeleteMapping("/remove-all")
    public String removeAll(){
        return diplomaService.removeAll();
    }

    @GetMapping("/get/{id}")
    public DiplomaDto getDiploma(@PathVariable Long id){
        return diplomaService.getDiploma(id);
    }

    @PostMapping("/add")
    public DiplomaDto addDiploma(@RequestBody DiplomaDto dto){
        return diplomaService.addDiploma(dto);
    }

    @PostMapping("/update/{id}/school")
    public String updateSchool(@RequestBody SchoolDto dto, @PathVariable Long id){
        return diplomaService.updateSchool(id, dto);
    }

    @PostMapping("/update/{id}/student")
    public String updateSchool(@RequestBody StudentDto dto, @PathVariable Long id){
        return diplomaService.updateStudent(id, dto);
    }

    @GetMapping("/num-of-student")
    public Long numOfStudent(){
        return diplomaService.numOfStudentGotDiploma();
    }

    @GetMapping("/max-num")
    public NumOfDiploma maxNumOfDiploma(){
        return diplomaService.maxNumOfDiploma();
    }

    @GetMapping("/num-of-diploma")
    public List<NumOfDiploma> numOfDiplomas(){
        return diplomaService.numOfDiplomas();
    }



    @PostMapping("/clear/{id}")
    public String  clearInfo(@PathVariable Long id){
        return diplomaService.clearInfo(id);
    }

    @DeleteMapping("/remove/{id}")
    public DiplomaDto remove(@PathVariable Long id){
        return diplomaService.removeDiploma(id);
    }

}
