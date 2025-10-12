package com.labo.exams.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AreaTo{
    private String name;
    private List<TestCatalogTo> tests;
}
