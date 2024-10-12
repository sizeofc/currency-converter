package com.jonatan.currencyconverter.utility;

import com.jonatan.currencyconverter.model.Currency;
import com.jonatan.currencyconverter.model.ManageApi;
import com.jonatan.currencyconverter.model.Money;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private Scanner input;
    private int opc;

    public Menu() throws IOException, InterruptedException {

        input = new Scanner(System.in);
        showMenu();
    }

    public void showMenu() throws IOException, InterruptedException {

        do {
            System.out.println("[1] Convertir Monedas");
            System.out.println("[2] Ver Historial de conversiones");
            System.out.println("[3] Lista de monedas disponibles");
            System.out.println("[0] Salir");
            System.out.print("\n\n\n\nIngrese su opcion:");
            opc = input.nextInt();

            manageOptions(opc);
        } while (opc > 0 && opc < 4);
    }

    public void manageOptions(int opc) throws IOException, InterruptedException {
        switch (opc) {
            case 1:
                showFormSelectCurrency();
                break;
            case 2:
                var historial = new HistorialCurrency();
                historial.readHistory();
                break;
            case 3:
                showListAvalibleCurrency();
                break;
            case 0:
                return;
            default:
                System.out.println("Opcion incorrecta ");
        }
    }

    public void showListAvalibleCurrency() throws IOException, InterruptedException {
        ManageApi manageApi = new ManageApi();
        Currency newCurrency = manageApi.currencyConverter("ARS");

        List<Money> listMoney = manageApi.getSupportedCodes();
        //cantidad de monedas soportadas

        for (int i = 0; i < newCurrency.conversion_rates().size(); i++) {
            String nameMoney = listMoney.get(i).getSimbol();
            listMoney.get(i).setRate(newCurrency.conversion_rates().get(nameMoney));
            if ((i + 1) % 4 != 1) {
                System.out.print(nameMoney + ": " + listMoney.get(i).getDescription()
                        + " ".repeat(44 - listMoney.get(i).getDescription().length()));
            } else
                System.out.println();
        }
        System.out.println();
    }

    public void searchCurrency(String currency) throws IOException, InterruptedException {
        // Obtener la instancia del API usando Singleton
        ManageApi manageApi = ManageApi.getInstance();

        // Obtener la conversión de la moneda base (puedes ajustar "ARS" si necesitas otra moneda base)
        Currency newCurrency = manageApi.currencyConverter("ARS");

        // Obtener la lista de monedas soportadas
        List<Money> listMoney = manageApi.getSupportedCodes();

        // Filtrar las monedas cuya descripción contenga la palabra buscada
        List<Money> filteredMoney = listMoney.stream()
                .filter(money -> money.getDescription().toLowerCase().contains(currency.toLowerCase())) // Agregamos toLowerCase para ignorar mayúsculas/minúsculas
                .collect(Collectors.toList());

        System.out.println("Monedas encontradas: " + filteredMoney.size());
//        System.out.println(filteredMoney.size()>0?filteredMoney.get(0):"0");

        // Mostrar las monedas filtradas y sus tasas de conversión
        for (int i = 0; i < filteredMoney.size(); i++) {
            String nameMoney = filteredMoney.get(i).getSimbol();

            // Obtener la tasa de conversión de newCurrency
            Double rate = newCurrency.conversion_rates().get(nameMoney);

            // Asegurarnos de que la tasa de conversión no sea null (por si falta alguna tasa en el mapa)
            if (rate != null) {
                filteredMoney.get(i).setRate(rate);
            } else {
                System.out.println("Tasa de conversión no disponible para: " + nameMoney);
                continue;
            }


            System.out.println(nameMoney + ": " + filteredMoney.get(i).getDescription());


        }

    }

    public void showFormSelectCurrency() throws IOException, InterruptedException {

        while (true) {


        System.out.println("ingrese la moneda a convertir");
        String baseCurrency = input.next();
        searchCurrency(baseCurrency) ;
        System.out.println("Ingrese del resultado las iniciales de la moneda ejemplo ARS");
        baseCurrency = input.next();
        System.out.println("ingrese la otra moneda a la que quiere convertir");
        String targetCurrency = input.next();
        searchCurrency(targetCurrency);
        System.out.println("Ingrese del resultado las iniciales de la moneda ejemplo ARS");
        targetCurrency = input.next();
        ManageApi manageApi = ManageApi.getInstance();

        System.out.println("ingrese el importe a converir");
        double amount = Double.parseDouble(input.next());

        Currency currency = manageApi.currencyConverter(baseCurrency.toUpperCase(), targetCurrency.toUpperCase() + "/" + amount);
        if (currency != null) {
            System.out.println(baseCurrency + " A " + targetCurrency + "=" + currency.conversion_result());
            var historialCurrency = new HistorialCurrency();
            String lineToSave = "basecurrency:" + baseCurrency.toUpperCase() + ",targetCurrency:"
                    + targetCurrency.toUpperCase() + ",amount:" + amount
                    + ",convertion_result:" + currency.conversion_result() * amount;
            historialCurrency.save(lineToSave);
        }
        break;
    }
}


}

