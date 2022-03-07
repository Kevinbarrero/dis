package ru.itis.Certificates;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encript {
    public static void main(String[] args) throws Exception {
        /*
        String pathname = "/home/holdandup/IdeaProjects/dis/s2_lab2/src/main/java/ru/itis/Certificates";
        String namekey = "jefesito";
        //generateCertificate();
        //generacion de clave(jefe)

        generatekey(namekey, pathname);
        //generacion de certificado(jefe)
        Certificate itis = new Certificate(namekey, "certItis", "Ru", "Tatarstan", "Kazan", "Itis","Itis", "Kevin", "Kevinbar98@gmail.com");
        generateMainCertificate(itis,pathname);
        //generacion de clave(usuario)
        generatekey("trabajador", pathname);
        //generacion de certificado(usuario)
        Certificate user = itis;
        user.setNameKey("trabajador");
        user.setNameCert("trabajador");
        user.setEmail("soyuntrabajadortrabado@gmail.com");
        user.setSection("Itistech");
        generateUserCertificate(user,pathname);

         */
    }
    public static void generatekey(String name, String path) throws IOException, InterruptedException {
            List<String> commands = new ArrayList<>();
            commands.add("openssl");
            commands.add("genrsa");
            commands.add("-out");
            commands.add(name + ".key");
            commands.add("2048");
            commandController(commands, path);


    }
    public static void generateUserCertificate(Certificate cer, String path) throws IOException, InterruptedException {
        String certhelper = "openssl req -new -utf8 -key " + cer.getNameKey() + ".key -out " + cer.getNameCert() + ".csr " + cer.subjParams();
        List<String> command = Arrays.asList(certhelper.split(" "));
        commandController(command,path);
    }
    public static void generateMainCertificate(Certificate cer, String path) throws IOException, InterruptedException {
        String certhelper = "openssl req -x509 -utf8 -new -key " + cer.getNameKey() + ".key -days 10000 -out " + cer.getNameCert() + ".crt " + cer.subjParams();
        System.out.println(certhelper);
        List<String> command = Arrays.asList(certhelper.split(" "));
        commandController(command, path);
    }
    public static void commandController(List<String> commands, String path) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(commands).directory(new File(path));
        pb.redirectErrorStream(true);
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println("tasklist: " + line);

        }
        process.waitFor();
    }

}
