package com.globits.qlvbcc.domain;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SchoolDiploma implements Comparable<SchoolDiploma> {

    private Long id;
    private List<NumOfDiploma> diplomas;
    private Long numOfDiploma;

    @Override
    public int compareTo(SchoolDiploma other){
        return (int) (other.numOfDiploma - this.numOfDiploma);
    }

}
