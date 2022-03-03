package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepo extends JpaRepository<Plot, Integer> {
}
