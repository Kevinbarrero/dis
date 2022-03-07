package ru.itis.Gui;

import ru.itis.Certificates.Certificate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static ru.itis.Certificates.Encript.*;

public class Gui extends JPanel

        implements ActionListener {
    static private final String newline = "\n";
    JButton openfilebutton;
    JTextArea log;
    JFileChooser fc;
    String directory;
    Certificate maincert;
    public Gui() {
        super(new BorderLayout());

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        openfilebutton = new JButton("Open Directory...");
        openfilebutton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openfilebutton);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        //create interface for new keyfile
        JButton companykeysubmit = new JButton("Submit");
        companykeysubmit.setBounds(50,150,100,30);
        JFrame companyKeyFrame = new JFrame("Company Key");
        JLabel companyKeyLabel = new JLabel("Name Key");
        JTextField companynamekey = new JTextField();
        companynamekey.setBounds(50,100,200,30);
        companyKeyLabel.setBounds(50,50,200,30);
        companyKeyFrame.setPreferredSize(new Dimension(390,300));
        companyKeyFrame.setLocation(100,150);

        companyKeyFrame.add(companykeysubmit);
        companyKeyFrame.add(companynamekey);
        companyKeyFrame.add(companyKeyLabel);
        companyKeyFrame.setLayout(null);

        //new button for certificate(Company)
        JButton certificatesubmit = new JButton("submit");
        certificatesubmit.setBounds(100, 790, 100, 30);
        //frame for certificate
        JFrame certificateFrame = new JFrame("Company Certificate");
        certificateFrame.setLayout(null);
        //params for certificate
        JLabel nameCertLabel = new JLabel("Name");
        nameCertLabel.setBounds(50,50,200,30);
        JTextField nameCertField = new JTextField();
        nameCertField.setBounds(50,100,200,30);

        JLabel countryCertLabel = new JLabel("Country");
        countryCertLabel.setBounds(50,150,200,30);
        JTextField countryCertField = new JTextField();
        countryCertField.setBounds(50,200,200,30);

        JLabel stateCertLabel = new JLabel("State");
        stateCertLabel.setBounds(50,250,200,30);
        JTextField stateCertField = new JTextField();
        stateCertField.setBounds(50,300,200,30);

        JLabel cityCertLabel = new JLabel("City");
        cityCertLabel.setBounds(50,350,200,30);
        JTextField cityCertField = new JTextField();
        cityCertField.setBounds(50,400,200,30);

        JLabel organizationCertLabel = new JLabel("Organization");
        organizationCertLabel.setBounds(50,450,200,30);
        JTextField organizationCertField = new JTextField();
        organizationCertField.setBounds(50,500,200,30);

        JLabel sectionCertLabel = new JLabel("Section");
        sectionCertLabel.setBounds(50,550,200,30);
        JTextField sectionCertField = new JTextField();
        sectionCertField.setBounds(50,600,200,30);

        JLabel emailCertLabel = new JLabel("Email");
        emailCertLabel.setBounds(50,650,200,30);
        JTextField emailCertField = new JTextField();
        emailCertField.setBounds(50,700,200,30);

        certificateFrame.setPreferredSize(new Dimension(300,870));
        certificateFrame.setLocation(100, 150);
        certificateFrame.add(certificatesubmit);
        certificateFrame.add(nameCertLabel);
        certificateFrame.add(nameCertField);
        certificateFrame.add(countryCertLabel);
        certificateFrame.add(countryCertField);
        certificateFrame.add(stateCertLabel);
        certificateFrame.add(stateCertField);
        certificateFrame.add(cityCertLabel);
        certificateFrame.add(cityCertField);
        certificateFrame.add(organizationCertLabel);
        certificateFrame.add(organizationCertField);
        certificateFrame.add(sectionCertLabel);
        certificateFrame.add(sectionCertField);
        certificateFrame.add(emailCertLabel);
        certificateFrame.add(emailCertField);
        certificateFrame.setResizable(false);


        JButton usercertificatesubmit = new JButton("submit");
        usercertificatesubmit.setBounds(100, 790, 100, 30);
        //frame for certificate
        JFrame usercertificateFrame = new JFrame("User Certificate");
        usercertificateFrame.setLayout(null);
        //params for certificate
        JLabel usernameCertLabel = new JLabel("Name");
        usernameCertLabel.setBounds(50,50,200,30);
        JTextField usernameCertField = new JTextField();
        usernameCertField.setBounds(50,100,200,30);

        JLabel usercountryCertLabel = new JLabel("Country");
        usercountryCertLabel.setBounds(50,150,200,30);
        JTextField usercountryCertField = new JTextField();
        usercountryCertField.setBounds(50,200,200,30);

        JLabel userstateCertLabel = new JLabel("State");
        userstateCertLabel.setBounds(50,250,200,30);
        JTextField userstateCertField = new JTextField();
        userstateCertField.setBounds(50,300,200,30);

        JLabel usercityCertLabel = new JLabel("City");
        usercityCertLabel.setBounds(50,350,200,30);
        JTextField usercityCertField = new JTextField();
        usercityCertField.setBounds(50,400,200,30);

        JLabel userorganizationCertLabel = new JLabel("Organization");
        userorganizationCertLabel.setBounds(50,450,200,30);
        JTextField userorganizationCertField = new JTextField();
        userorganizationCertField.setBounds(50,500,200,30);

        JLabel usersectionCertLabel = new JLabel("Section");
        usersectionCertLabel.setBounds(50,550,200,30);
        JTextField usersectionCertField = new JTextField();
        usersectionCertField.setBounds(50,600,200,30);

        JLabel useremailCertLabel = new JLabel("Email");
        useremailCertLabel.setBounds(50,650,200,30);
        JTextField useremailCertField = new JTextField();
        useremailCertField.setBounds(50,700,200,30);

        usercertificateFrame.setPreferredSize(new Dimension(300,870));
        usercertificateFrame.setLocation(100, 150);
        usercertificateFrame.add(usercertificatesubmit);
        usercertificateFrame.add(usernameCertLabel);
        usercertificateFrame.add(usernameCertField);
        usercertificateFrame.add(usercountryCertLabel);
        usercertificateFrame.add(usercountryCertField);
        usercertificateFrame.add(userstateCertLabel);
        usercertificateFrame.add(userstateCertField);
        usercertificateFrame.add(usercityCertLabel);
        usercertificateFrame.add(usercityCertField);
        usercertificateFrame.add(userorganizationCertLabel);
        usercertificateFrame.add(userorganizationCertField);
        usercertificateFrame.add(usersectionCertLabel);
        usercertificateFrame.add(usersectionCertField);
        usercertificateFrame.add(useremailCertLabel);
        usercertificateFrame.add(useremailCertField);
        usercertificateFrame.setResizable(false);

        JFrame userKeyFrame = new JFrame("User Key");
        JButton userkeysubmit = new JButton("Submit");
        userkeysubmit.setBounds(50,150,100,30);
        JLabel userKeyLabel = new JLabel("Name Key");
        JTextField usernamekey = new JTextField();
        usernamekey.setBounds(50,100,200,30);
        userKeyLabel.setBounds(50,50,200,30);
        userKeyFrame.setPreferredSize(new Dimension(390,300));
        userKeyFrame.setLocation(100,150);
        userKeyFrame.add(userkeysubmit);
        userKeyFrame.add(usernamekey);
        userKeyFrame.add(userKeyLabel);
        userKeyFrame.setLayout(null);
        usercertificatesubmit.addActionListener(actionEvent ->{
            Certificate usercer = maincert;
            usercer.setNameKey(usernamekey.getText());
            usercer.setNameCert(usernameCertField.getText());
            usercer.setSection(usersectionCertField.getText());
            usercer.setEmail(usernameCertField.getText());
            try {
                generateUserCertificate(usercer, directory);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            usercertificateFrame.dispose();
        });
        userkeysubmit.addActionListener(actionEvent -> {
            try {
                generatekey(usernamekey.getText(), directory);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            userKeyFrame.dispose();
            usercertificateFrame.pack();
            usercertificateFrame.setVisible(true);

        });
        certificatesubmit.addActionListener(actionEvent -> {
            maincert =  new Certificate(companynamekey.getText(), nameCertField.getText(), countryCertField.getText(), stateCertField.getText(), cityCertField.getText(), organizationCertField.getText(), sectionCertField.getText(), nameCertField.getText());
            try {
                generateMainCertificate(maincert, directory);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            certificateFrame.dispose();
            userKeyFrame.pack();
            userKeyFrame.setVisible(true);
        });

        companykeysubmit.addActionListener(actionEvent -> {
            //make new key
            System.out.println(companynamekey.getText());
            companyKeyFrame.dispose();
            try {
                generatekey(companynamekey.getText(), directory);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            certificateFrame.pack();
            certificateFrame.setVisible(true);
        });

        //Handle open button action.
        if (e.getSource() == openfilebutton) {
            int returnVal = fc.showOpenDialog(Gui.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getAbsolutePath() + "." + newline);
                directory = file.getAbsolutePath();
                companyKeyFrame.pack();
                companyKeyFrame.setVisible(true);

                System.out.println(directory);

            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Handle save button action.
        } else {
            log.append("Save command cancelled by user." + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());
    }
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GuiProbe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}