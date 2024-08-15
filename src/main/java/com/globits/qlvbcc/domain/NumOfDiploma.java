package com.globits.qlvbcc.domain;

import lombok.Data;

@Data
public class NumOfDiploma implements Comparable<NumOfDiploma> {

    private String type;
    private Long num;

    @Override
    public int compareTo(NumOfDiploma other){
        return (int) (-this.num + other.num);
    }
}
