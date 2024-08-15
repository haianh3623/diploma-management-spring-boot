package com.globits.qlvbcc.serviceimpl;

import com.globits.qlvbcc.domain.*;
import com.globits.qlvbcc.dto.DiplomaDto;
import com.globits.qlvbcc.dto.SchoolDto;
import com.globits.qlvbcc.dto.StudentDto;
import com.globits.qlvbcc.repository.DiplomaRepository;
import com.globits.qlvbcc.repository.SchoolRepository;
import com.globits.qlvbcc.repository.StudentRepository;
import com.globits.qlvbcc.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    DiplomaRepository diplomaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<SchoolDto> getAll() {
        return schoolRepository.getAll();
    }

    @Override
    public SchoolDto addSchool(SchoolDto dto) {

        School school = new School();
        school.setName(dto.getName());
        school.setCode(dto.getCode());
        schoolRepository.save(school);

        return dto;
    }

    @Override
    public SchoolDto updateSchool(SchoolDto dto, Long id) {

        School school = schoolRepository.findSchoolById(id);
        school.setName(dto.getName());
        school.setCode(dto.getCode());

        if(dto.getDiplomas() != null && !dto.getDiplomas().isEmpty()){
            List<Diploma> diplomas = new ArrayList<>();
            List<DiplomaDto> dtos = dto.getDiplomas();
            Iterator<DiplomaDto> iters = dtos.iterator();
            while(iters.hasNext()){
                DiplomaDto item = iters.next();
                Diploma diploma = null;
                if(item.getId() != null){
                    diploma = diplomaRepository.findDiplomaById(item.getId());
                } else{
                    diploma = new Diploma();
                    diploma.setName(item.getName());
                    diploma.setCode(item.getCode());
                    diploma.setType(item.getType());
                }
                diploma.setSchoolDip(school);
                diplomaRepository.save(diploma);
                diplomas.add(diploma);
            }
            if(school.getDiplomas() != null){
                school.getDiplomas().clear();
                school.getDiplomas().addAll(diplomas);
            } else{
                school.setDiplomas(diplomas);
            }
        }
        schoolRepository.save(school);

        return dto;
    }

    @Override
    public SchoolDto removeSchool(Long id) {

        SchoolDto dto = schoolRepository.findSchoolDtoById(id);
        schoolRepository.deleteById(id);

        return dto;
    }

    @Override
    public String removeAll() {

        schoolRepository.deleteAll();

        return "Removed all";
    }

    @Override
    public SchoolDto getSchool(Long id) {
        return schoolRepository.findSchoolDtoById(id);
    }

    @Override
    public List<NumOfDiploma> typeOfDiploma(Long id) {

        List<String> set = new ArrayList<>();
        List<DiplomaDto> list = diplomaRepository.getAll();
        for(DiplomaDto dto : list){
            set.add(dto.getType());
        }

        List<NumOfDiploma> nums = new ArrayList<NumOfDiploma>();
        for(String s : set){
            NumOfDiploma num = new NumOfDiploma();
            num.setType(s);
            num.setNum(diplomaRepository.getNumOfDiplomaByTypeAndSchoolId(s, schoolRepository.findSchoolById(id)));
            if(num.getNum() != 0){
                nums.add(num);
            }
        }


        return nums;
    }

    @Override
    public SchoolDiploma maxNumOfDiploma() {

        List<SchoolDiploma> list = new ArrayList<>();
        for(SchoolDto dto : schoolRepository.getAll()){
            SchoolDiploma schoolDiploma = new SchoolDiploma();
            schoolDiploma.setId(dto.getId());
            schoolDiploma.setNumOfDiploma(diplomaRepository.getNumOfDiplomaBySchoolId(schoolRepository.findSchoolById(dto.getId())));
            list.add(schoolDiploma);
        }
        Collections.sort(list);

        return list.get(0);
    }

    @Override
    public List<SchoolDiploma> diplomaAwared() {

        Set<String> set = new HashSet<>();
        List<DiplomaDto> dtos = diplomaRepository.getAll();
        for(DiplomaDto dto : dtos){
            set.add(dto.getType());
        }

        List<SchoolDiploma> list = new ArrayList<>();
        for(SchoolDto dto : schoolRepository.getAll()){
            SchoolDiploma schoolDiploma = new SchoolDiploma();
            schoolDiploma.setId(dto.getId());
            schoolDiploma.setNumOfDiploma(diplomaRepository.getNumOfDiplomaBySchoolId(schoolRepository.findSchoolById(dto.getId())));
            List<NumOfDiploma> nums = new ArrayList<>();
            for(String s : set){
                NumOfDiploma num = new NumOfDiploma();
                num.setType(s);
                num.setNum(diplomaRepository.getNumOfDiplomaByTypeAndSchoolId(s, schoolRepository.findSchoolById(dto.getId())));
                nums.add(num);
            }
            Collections.sort(nums);
            schoolDiploma.setDiplomas(nums);


            list.add(schoolDiploma);
        }
        Collections.sort(list);

        return list;
    }

    @Override
    public DiplomaDto addDiploma(DiplomaDto dto) {
        return null;
    }
}
