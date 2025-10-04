package com.adam.wilkscalculator.controller;

import com.adam.wilkscalculator.model.Gender;
import com.adam.wilkscalculator.service.WilksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WilksController {

    @Autowired
    private WilksService wilksService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam Gender gender,
                            @RequestParam double bodyWeight,
                            @RequestParam double liftedWeight,
                            Model model) {
        double wilks = wilksService.calculateWilks(gender, bodyWeight, liftedWeight);
        model.addAttribute("result", wilks);
        return "result";
    }

    @PostMapping("/compare")
    public String compare(@RequestParam Gender gender1,
                          @RequestParam double weight1,
                          @RequestParam double result1,
                          @RequestParam Gender gender2,
                          @RequestParam double weight2,
                          Model model) {
        double required = wilksService.requiredLiftToWin(gender1, weight1, result1, gender2, weight2);
        model.addAttribute("requiredLift", required);
        return "result";
    }
}
