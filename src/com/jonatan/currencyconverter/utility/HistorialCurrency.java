package com.jonatan.currencyconverter.utility;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialCurrency {

    private String fileName="historialCurrencyConverter.txt";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean save(String convertionResult){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Formatear la fecha y hora actual
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            String dateTime = dtf.format(LocalDateTime.now());

            // Escribir la línea de conversión con la fecha y salto de línea al final
            writer.write(convertionResult + ",date:" + dateTime + "\n");

            // Devolver true indicando que la escritura fue exitosa
            return true;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el historial de conversiones", e);
        }
    }

        public void readHistory() {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }

