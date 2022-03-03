package com.example.irrigationsystem.repositories;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.example.irrigationsystem.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepo extends JpaRepository<Plot, Integer>,
    EntityGraphJpaSpecificationExecutor<Plot> {
    Plot findById(Integer unitId, EntityGraph entityGraph);

}
