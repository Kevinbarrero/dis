package ru.itis;


import javax.swing.*;

import static ru.itis.service.BlockRestServices.getChain;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(getChain());

        JFrame table = new Table();
        table.setSize(600,600);
        table.setLocationRelativeTo(null);
        table.setVisible(true);
    }

}
