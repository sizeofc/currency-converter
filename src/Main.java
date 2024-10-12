import com.google.gson.Gson;
import com.jonatan.currencyconverter.model.Currency;
import com.jonatan.currencyconverter.model.ManageApi;
import com.jonatan.currencyconverter.model.Money;
import com.jonatan.currencyconverter.utility.Menu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManageApi manageApi = ManageApi.getInstance();
        Gson gson= new Gson().newBuilder()
                .setPrettyPrinting().create();


        try {
//            Currency newCurrency = manageApi.currencyConverter("ARS");

            Menu menu= new Menu();
//            System.out.println("\nINGRESE EL NOMBRE O ALGUNOS CARACTERES DE LA MONEDA QUE BASE A CONVERTIR");
//            Scanner scanner = new Scanner(System.in);
//            String currency= scanner.nextLine();
//            menu.searchCurrency(currency);


            } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
