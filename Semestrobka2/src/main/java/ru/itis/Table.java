package ru.itis;

import ru.itis.db.BlockModel;
import ru.itis.db.DataModel;
import ru.itis.service.BlockRestServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

import static ru.itis.service.BlockRestServices.*;
import static ru.itis.service.DbWorker.EstablishConnection;

public class Table extends JFrame {

    private JTable table;
    DefaultTableModel model;
    JScrollPane roll;

    JLabel label = new JLabel("Add data");
    JLabel label1 = new JLabel("add name");
    JTextField data = new JTextField();
    JTextField name = new JTextField();
    JButton send = new JButton("Submit");


    public Table() throws SQLException {
        String[] columnas = {"prevhash", "data", "name", "signature","ts", "publickey", "proved"};
        table = new JTable();
        model = new DefaultTableModel();
        roll = new JScrollPane(table);
        this.setTitle("JTable");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        model.setColumnIdentifiers(columnas);
        roll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        roll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setModel(model);
        this.adddata(model);
        send.setBounds(470, 40, 100, 30);
        send.addActionListener(e -> {
            try {
                EstablishConnection();
                String datas = data.getText();
                String names = name.getText();
                //System.out.println(datas + " " + names);
                DataModel model = new DataModel(datas, names);
                System.out.println(model.getData() + "--" + model.getName());
                BlockRestServices service = new BlockRestServices();
                service.getChain();
                updateDB();
                createBlock(new DataModel(model.getData(), model.getName()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        roll.setBounds(2,80,550,480);
        data.setBounds(10, 40, 200, 30);
        name.setBounds(250, 40, 200, 30);
        label.setBounds(10, 10, 200, 30);
        label1.setBounds(250, 10, 300, 30);
        this.getContentPane().add(roll);
        this.getContentPane().add(label);
        this.getContentPane().add(label1);
        this.getContentPane().add(data);
        this.getContentPane().add(name);
        this.getContentPane().add(send);
        this.pack();


    }

    private void adddata(DefaultTableModel model) throws SQLException {
        //ArrayList<String> probed =
        model.setRowCount(0);
        Object[] addrow = {"prevhash", "data", "name", "signature","ts", "publickey" ,"verification"};
        for(BlockModel x: getChain()) {
            addrow[0] = x.getPrevhash();
            addrow[2] = x.getData().getName();
            addrow[1] = x.getData().getData();
            addrow[3] = x.getSignature();
            addrow[4] = x.getTs();
            addrow[5] = x.getPublickey();
            addrow[6] = x.getverification();
            model.addRow(addrow);
        }
    }
}
