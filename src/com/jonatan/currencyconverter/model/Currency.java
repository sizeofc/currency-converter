package com.jonatan.currencyconverter.model;

import java.util.List;
import java.util.Map;

public record Currency (
        String base_code,
        String target_code,
        Map<String,Double> conversion_rates,
        double conversion_result

){
}
