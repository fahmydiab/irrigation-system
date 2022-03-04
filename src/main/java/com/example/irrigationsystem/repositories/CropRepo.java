package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepo extends JpaRepository<Crop, Integer> {
    List<Crop> findByName(String name);
}
