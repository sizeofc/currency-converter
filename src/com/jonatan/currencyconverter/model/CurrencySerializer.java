package com.jonatan.currencyconverter.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrencySerializer {
    @SerializedName("supported_codes")
    private List<List<String>> supportedCodes;

    public List<List<String>> getSupportedCodes() {
        return supportedCodes;
    }
}
