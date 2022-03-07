package ru.itis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class probe {
    public static void main(String[] args) throws IOException {
        // read the file
        List<Ticket> eur_Rub = new ArrayList<>();
        List<Ticket> usd_Rub = new ArrayList<>();
        ticker_List("/home/holdandup/IdeaProjects/dis/lab1/src/main/java/ru/itis/EURRUB_210801_210901.csv", eur_Rub);
        ticker_List("/home//holdandup/IdeaProjects/dis/lab1/src/main/java/ru/itis/USDRUB_210801_210901.csv", usd_Rub);
        // Sort Tickets
        Comparator<Ticket> comparator = Comparator.comparing(c -> Long.valueOf(c.getDate()));
        eur_Rub.sort(comparator);
        //stats List<Ticker>...
        System.out.println("EUR_RUB STATS");
        System.out.println("Median Score = "+median_score_trading(eur_Rub));
        System.out.println("Variant Score = "+ variant_score(eur_Rub));
        System.out.println("Deviation Score = "+desv_score(eur_Rub));

        System.out.println("USD_RUB STATS");
        System.out.println("Median Score = "+median_score_trading(usd_Rub));
        System.out.println("Variant Score = "+ variant_score(usd_Rub));
        System.out.println("Deviation Score = " + desv_score(usd_Rub));
        //correlation value
        System.out.println("Correlation between USD_RUB and EUR_RUB =" + linear_correlation(usd_Rub, eur_Rub));





    }

    public static double linear_correlation(List<Ticket> data1, List<Ticket> data2){
        boolean check = true;
        if (data1.size() != data2.size()){
            System.out.println("Row's Number is not the same");
            check = false;
        }
        double correlation = 0;
        double den = 0;
        if (check){

            for (int i = 0; i <data1.size() ; i++) {
                float x = data1.get(i).getClose();
                float y = data2.get(i).getClose();
                den+= x*y;
            }
            den = den/data1.size();
            correlation = den - (median_score_trading(data1)* median_score_trading(data2));

        }

        return correlation / (desv_score(data1)* desv_score(data2));
    }
    public static double desv_score(List<Ticket> trading){
        return Math.sqrt(variant_score(trading));
    }
    public static double variant_score(List<Ticket> trading){
        double x1;
        double sum = 0;
        for (int i = 0; i < trading.size(); i++) {
            x1 = trading.get(i).getClose() - median_score_trading(trading);
            x1 = Math.pow(x1,2);
            sum += x1;
        }
        return sum / trading.size();
    }
    public static float median_score_trading(List<Ticket> trading){
        float sum = 0;
        for (Ticket ticket : trading) {
            sum += ticket.getClose();
        }
        return sum/trading.size();
    }



    public static List<Ticket> ticker_List(String path, List<Ticket> trading) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        String row;
        int line_iterator = 0;
        while ((row = csvReader.readLine()) != null) {
            if (line_iterator == 0){
                line_iterator++;
                continue;
            }
            String[] data = row.split(",");
            trading.add(new Ticket(data[2], Float.parseFloat(data[4])));
        }
        csvReader.close();
        return trading;
    }
}
