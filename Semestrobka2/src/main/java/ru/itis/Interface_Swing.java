package ru.itis;

import ru.itis.db.DataModel;
import ru.itis.service.BlockRestServices;

import javax.swing.*;

import static ru.itis.service.BlockRestServices.*;
import static ru.itis.service.DbWorker.EstablishConnection;

class Interface_swing extends JFrame {

    public Interface_swing() throws Exception {
        //create jframe
        JFrame f = new JFrame("Blockchain");
        f.setSize(1000, 1000);
        f.setLocation(100, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultLookAndFeelDecorated(true);
        //create labels
        JLabel label = new JLabel("Add data");
        JLabel label1 = new JLabel("add name");
        label.setBounds(50, 50, 200, 30);
        label1.setBounds(50, 120, 300, 30);
        //create textlines and button
        JTextField data = new JTextField();
        JTextField name = new JTextField();
        data.setBounds(50, 75, 200, 30);
        name.setBounds(50, 160, 200, 30);
        JButton send = new JButton("Submit");
        send.setBounds(50, 300, 100, 30);

        send.addActionListener(e -> {
            try {
                EstablishConnection();
                String datas = data.getText();
                String names = name.getText();
                //System.out.println(datas + " " + names);
                DataModel model = new DataModel(datas,names);
                System.out.println(model.getData() + "--" + model.getName());
                BlockRestServices service = new BlockRestServices();
                getChain();
                updateDB();
                createBlock(new DataModel(model.getData(),model.getName()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }});

        //JData

        String col[] = {"prevhash", "data", "name", "signature"};

        //DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        //JOptionPane.showMessageDialog(null, new JScrollPane(table));

        String[][] probe = new String[getChain().size()][3];

        for (int i = 0; i < getChain().size(); i++){
            String prevhash = getChain().get(i).getPrevhash();
            String datat = getChain().get(i).getData().getData();
            String namet = getChain().get(i).getData().getName();
            String signature = getChain().get(i).getSignature();
            String[] addto = {prevhash, datat, namet, signature};
            probe[i] = addto;
            //tableModel.addRow(addto);
            //z.add(addto);

        }
        //String[][] a = z.toArray(new String[0][]);
        //JTable table = new JTable(a,col);

////////end probe
        // add info
        f.add(label);
        f.add(data);
        f.add(label1);
        f.add(name);
        f.add(send);
        //f.add(new JScrollPane(table));
        f.setLayout(null);
        f.setVisible(true);




    }
}

