package com.jonatan.currencyconverter.model;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ManageApi {
    private String currency;
    private String otherCurrency;
    public static ManageApi instance;

    Dotenv dotenv = Dotenv.load();
    private String apiKey = dotenv.get("API_KEY");
    private String apiUri = "https://v6.exchangerate-api.com/v6/" + this.apiKey + "/";

    public ManageApi() {

    }

    public ManageApi(String currency) {
        this.currency = currency;
    }

    public ManageApi(String currency, String otherCurrency) {
        this.currency = currency;
        this.otherCurrency = otherCurrency;
    }

    public static ManageApi getInstance() {
        if (instance == null) {
            synchronized (ManageApi.class) {
                if (instance == null) {
                    instance = new ManageApi();
                }
            }
        }
        return instance;
    }

    public Currency currencyConverter(String currency, String otherCurrency) throws IOException, InterruptedException {

        HttpResponse<String> response = connectToCurrencyApi(apiUri + (otherCurrency.isEmpty() ? "latest/" + currency : "pair/" + currency + "/" + otherCurrency));

        if (response.statusCode() == 200) {
//            System.out.println("Exito: " + response.body());//.substring(response.body().indexOf("ARS"),response.body().indexOf("ARS")+12));
            return new Gson().fromJson(response.body(), Currency.class);

        } else {
            System.out.println("Error code: " + response.statusCode());
            return null;
        }

    }

    public Currency currencyConverter(String currency) throws IOException, InterruptedException {
        return currencyConverter(currency, "");
    }

    public HttpResponse connectToCurrencyApi(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public List<Money> getSupportedCodes() {
        try {
            HttpResponse<String> response = connectToCurrencyApi(apiUri + "codes");
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                CurrencySerializer currencySerializer = gson.fromJson(response.body(), CurrencySerializer.class);
                List<Money> listMoney = new ArrayList<>();
                // Crear una lista de Currency a partir de los datos deserializados
                for (List<String> code : currencySerializer.getSupportedCodes()) {
                    Money money = new Money(code.get(0), code.get(1));
                    listMoney.add(money);
                }
                return listMoney;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
