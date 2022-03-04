package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.Crop;
import com.example.irrigationsystem.repositories.CropRepo;
import com.example.irrigationsystem.repositories.PlotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropService {
    private final CropRepo cropRepo;

    @Transactional
    public Crop create(Crop crop) {
        crop.setCreatedDate(LocalDate.now());
        List<Crop> cropList = cropRepo.findByName(crop.getName());
        if(cropList.isEmpty())
            return cropRepo.save(crop);
        return cropList.get(0);
    }
}
