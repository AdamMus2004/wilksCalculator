package com.adam.wilkscalculator.service;

import com.adam.wilkscalculator.model.Gender;
import org.springframework.stereotype.Service;

@Service
public class WilksService {

    public double calculateWilks(Gender gender, double bodyWeight, double liftedWeight) {
        double a, b, c, d, e, f;

        if (gender == Gender.MALE) {
            a = -216.0475144;
            b = 16.2606339;
            c = -0.002388645;
            d = -0.00113732;
            e = 7.01863E-06;
            f = -1.291E-08;
        } else {
            a = 594.31747775582;
            b = -27.23842536447;
            c = 0.82112226871;
            d = -0.00930733913;
            e = 4.731582E-05;
            f = -9.054E-08;
        }

        double coeff = 500 / (a + b * bodyWeight + c * Math.pow(bodyWeight, 2)
                + d * Math.pow(bodyWeight, 3) + e * Math.pow(bodyWeight, 4) + f * Math.pow(bodyWeight, 5));

        return coeff * liftedWeight;
    }

    public double requiredLiftToWin(Gender gender1, double weight1, double result1,
                                    Gender gender2, double weight2) {
        double wilks1 = calculateWilks(gender1, weight1, result1);

        double result2 = result1;
        while (calculateWilks(gender2, weight2, result2) <= wilks1) {
            result2 += 0.5;
        }
        return result2;
    }
}
