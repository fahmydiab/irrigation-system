package com.example.irrigationsystem.specification;

import com.example.irrigationsystem.filter.PlotFilter;
import com.example.irrigationsystem.model.Plot;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class PlotSpecification extends BaseSpecification<PlotFilter, Plot> {
    public Specification<Plot> getFilter(PlotFilter filter) {


        return (root, query, cb) -> {
            query.distinct(true);
            return where(between("area", filter.getAreaFrom(),filter.getAreaTo()))
                .and(between("crop", filter.getCropTo(),filter.getCropTo()))
                .toPredicate(root, query, cb);
        };
    }


}
