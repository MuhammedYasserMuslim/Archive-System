package com.spring.model.dto.deputation;

import com.spring.model.entity.Days;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private boolean notice;

    public DeputationDays(Integer id, String name, List<Days> deputationDays) {
        this.id = id;
        this.name = name;
        this.deputationDays = deputationDays;
    }

    public Integer getCount() {
        return deputationDays.size();
    }

    public boolean isNotice() {
        Set<Days> set = new HashSet<>(deputationDays);
        return this.deputationDays.size() > 2 || this.deputationDays.size() != set.size();
    }
}
