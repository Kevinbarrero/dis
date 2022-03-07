package ru.itis;

import javax.swing.*;

import static ru.itis.Gui.Gui.createAndShowGUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });



    }


}
