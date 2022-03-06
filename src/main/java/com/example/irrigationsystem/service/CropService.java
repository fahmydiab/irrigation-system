package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.Crop;
import com.example.irrigationsystem.model.Plot;
import com.example.irrigationsystem.repositories.CropRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public void createCrop() {

        Crop crop = new Crop(1);
        crop.setName("Cotton");
        cropRepo.save(crop);
    }
}
