package com.spring.model.dto.special;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DecisionDto {

    int num;
    String summary;
    private String qarar;
}
