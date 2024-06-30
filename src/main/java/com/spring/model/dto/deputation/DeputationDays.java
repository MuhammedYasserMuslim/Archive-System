package com.spring.model.dto.deputation;

import com.spring.model.entity.Days;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DeputationDays {

    private Integer id;

    private String name;

    private List<Days> deputationDays;

    private Integer count;

    public DeputationDays(Integer id, String name, List<Days> deputationDays) {
        this.id = id;
        this.name = name;
        this.deputationDays = deputationDays;
    }

    public Integer getCount() {
        return deputationDays.size();
    }
}
