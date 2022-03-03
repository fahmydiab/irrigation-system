package com.example.irrigationsystem;

import com.example.irrigationsystem.service.PlotService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class IrrigationSystemApplication implements CommandLineRunner {

    private final PlotService plotService;
    private static final Logger log = LoggerFactory.getLogger(IrrigationSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IrrigationSystemApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        seedData();
    }

    private void seedData() {
        try {
            plotService.createPlot();
        }
        catch (Exception e) {
            log.warn("Plot already exists");
        }

    }

}
