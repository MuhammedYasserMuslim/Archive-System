package com.spring.model.dto.special;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.model.entity.Decision;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private int num;
    private String head;

   private List<Decision> decision;


}
