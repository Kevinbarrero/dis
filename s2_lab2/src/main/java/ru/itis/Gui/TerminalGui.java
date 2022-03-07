package ru.itis.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class TerminalGui extends JFrame implements ActionListener {
    static JTextField t;
    static JFrame f;
    static JButton b;
    static JLabel l;
    TerminalGui()
    {
    }
    public static void main(String[] args)
    {
        f = new JFrame("textfield");
        l = new JLabel("nothing entered");
        b = new JButton("submit");
        TerminalGui te = new TerminalGui();
        b.addActionListener(te);
        t = new JTextField(16);
        JPanel p = new JPanel();
        p.add(t);
        p.add(b);
        p.add(l);
        f.add(p);
        f.setSize(600, 600);
        //l.setPreferredSize(new Dimension(200,200));
        f.show();
    }
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            // set the text of the label to the text of the field

            l.setText(t.getText());

            // set the text of field to blank
            //t.setText("  ");
        }
    }
}
