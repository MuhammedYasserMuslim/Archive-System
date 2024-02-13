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
    @JsonBackReference
    List<Decision> decision;
    List<DecisionDto> decisions;

    public SubjectDto(int num, String head, List<Decision> decision) {
        this.num = num;
        this.head = head;
        this.decision = decision;
    }

    public List<DecisionDto> getDecisions() {
        List<DecisionDto> dtos = new ArrayList<>();
        for (Decision d : this.decision) {
            DecisionDto dto = new DecisionDto(d.getNum(), d.getSummary());
            dtos.add(dto);
        }
        return dtos;
    }
}
