package com.globits.qlvbcc.serviceimpl;

import com.globits.qlvbcc.domain.Diploma;
import com.globits.qlvbcc.domain.NumOfDiploma;
import com.globits.qlvbcc.domain.School;
import com.globits.qlvbcc.domain.Student;
import com.globits.qlvbcc.dto.DiplomaDto;
import com.globits.qlvbcc.dto.SchoolDto;
import com.globits.qlvbcc.dto.StudentDto;
import com.globits.qlvbcc.repository.DiplomaRepository;
import com.globits.qlvbcc.repository.SchoolRepository;
import com.globits.qlvbcc.repository.StudentRepository;
import com.globits.qlvbcc.service.DiplomaService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiplomaServiceImpl implements DiplomaService {

    @Autowired
    DiplomaRepository diplomaRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<DiplomaDto> getAll() {
        return diplomaRepository.getAll();
    }

    @Override
    public DiplomaDto addDiploma(DiplomaDto dto) {

        Diploma diploma = new Diploma();
        diploma.setName(dto.getName());
        diploma.setCode(dto.getCode());
        diploma.setType(dto.getType());
        if(dto.getSchool() != null && dto.getSchool().getId()!= null){
            School school = schoolRepository.findSchoolById(dto.getSchool().getId());
            diploma.setSchoolDip(school);
        }
        if(dto.getStudent() != null && dto.getStudent().getId() != null){
            Student student = studentRepository.findStudentById(dto.getStudent().getId());
            diploma.setStudent(student);
        }

        diplomaRepository.save(diploma);

        return dto;
    }

    @Override
    public DiplomaDto updateDiploma(DiplomaDto dto, Long id) {

        Diploma diploma = diplomaRepository.findDiplomaById(id);
        diploma.setName(dto.getName());
        diploma.setCode(dto.getCode());
        diploma.setType(dto.getType());

        diplomaRepository.save(diploma);

        return dto;
    }

    @Override
    public DiplomaDto removeDiploma(Long id) {
        diplomaRepository.deleteById(id);
        return null;
    }

    @Override
    public String removeAll() {

        diplomaRepository.deleteAll();

        return "Removed all";
    }

    @Override
    public DiplomaDto getDiploma(Long id) {
        return diplomaRepository.findDiplomaDtoById(id);
    }

    @Override
    public String updateStudent(Long id, StudentDto dto) {

//        Student student = null;
//        if(dto.getId() != null && dto.getId() > 0){
//            student = studentRepository.findStudentById(dto.getId());
//        } else{
//            student = new Student();
//            student.setName(dto.getName());
//            student.setCode(dto.getCode());
//        }
//        Diploma diploma = diplomaRepository.findDiplomaById(id);
//
//        Set<Diploma> diplomas = null;
//        if(student.getDiplomas() != null && !student.getDiplomas().isEmpty()){
//            diplomas = student.getDiplomas();
//        } else{
//            diplomas = new HashSet<Diploma>();
//        }
//        diplomas.add(diploma);
//
//        if(student.getDiplomas() != null){
//            student.getDiplomas().clear();
//            student.getDiplomas().addAll(diplomas);
//        } else{
//            student.setDiplomas(diplomas);
//        }
//
//        studentRepository.save(student);
//        student = studentRepository.findStudentById(dto.getId());
//        diploma.setStudent(student);
//        diplomaRepository.save(diploma);

        Student student = studentRepository.findStudentById(dto.getId());
        Diploma diploma = diplomaRepository.findDiplomaById(id);
        diploma.setStudent(student);
        diplomaRepository.save(diploma);
        return "Updated";
    }

    @Override
    public String updateSchool(Long id, SchoolDto dto) {

        School school = null;
        if(dto.getId() != null && dto.getId() > 0){
            school = schoolRepository.findSchoolById(dto.getId());
        } else{
            school = new School();
            school.setName(dto.getName());
            school.setCode(dto.getCode());
        }
        Diploma diploma = diplomaRepository.findDiplomaById(id);

        List<Diploma> diplomas = null;
        if(school.getDiplomas() != null && !school.getDiplomas().isEmpty()){
            diplomas = school.getDiplomas();
        } else{
            diplomas = new ArrayList<>();
        }
        diplomas.add(diploma);

        if(school.getDiplomas() != null){
            school.getDiplomas().clear();
            school.getDiplomas().addAll(diplomas);
        } else{
            school.setDiplomas(diplomas);
        }

        schoolRepository.save(school);
        school = schoolRepository.findSchoolById(dto.getId());
        diploma.setSchoolDip(school);
        diplomaRepository.save(diploma);

        return "Updated";
    }

    @Override
    public Long numOfStudentGotDiploma() {

        Set<Long> set = new HashSet<>();
        List<DiplomaDto> list = diplomaRepository.getAll();
        for(DiplomaDto dto : list){
            set.add(dto.getStudent().getId());
        }

        return (long) set.size();
    }

    @Override
    public NumOfDiploma maxNumOfDiploma() {

        Set<String> set = new HashSet<>();
        List<DiplomaDto> list = diplomaRepository.getAll();
        for(DiplomaDto dto : list){
            set.add(dto.getType());
        }

        List<NumOfDiploma> nums = new ArrayList<NumOfDiploma>();
        for(String s : set){
            NumOfDiploma num = new NumOfDiploma();
            num.setType(s);
            num.setNum(diplomaRepository.getNumOfDiplomaByType(s));
            nums.add(num);
        }
        Collections.sort(nums);

        return nums.get(0);
    }

    @Override
    public List<NumOfDiploma> numOfDiplomas() {

        Set<String> set = new HashSet<>();
        List<DiplomaDto> list = diplomaRepository.getAll();
        for(DiplomaDto dto : list){
            set.add(dto.getType());
        }

        List<NumOfDiploma> nums = new ArrayList<NumOfDiploma>();
        for(String s : set){
            NumOfDiploma num = new NumOfDiploma();
            num.setType(s);
            num.setNum(diplomaRepository.getNumOfDiplomaByType(s));
            nums.add(num);
        }
        Collections.sort(nums);

        return nums;
    }

    @Override
    public String clearInfo(Long id) {

        Diploma diploma = diplomaRepository.findDiplomaById(id);
        diploma.setSchoolDip(null);
        diploma.setStudent(null);
        diplomaRepository.save(diploma);

        return "Cleared";
    }

}
