package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StartMessenger {

    public static void main(String[] args) {
        Client client = new Client();
        Template template = new Template();
        TemplateEngine templateEngine = new TemplateEngine();
        MailServer mailServer = new MailServer();
        Messenger messenger = new Messenger(mailServer, templateEngine);

        Scanner scanner = new Scanner(System.in);
        String typeWork;
        do {
            System.out.println("Choose type (number) of work application: \n 1 - console \n 2 - file");
            typeWork = scanner.nextLine();

            if (!typeWork.equals("1") && !typeWork.equals("2")) {
                System.out.println("you enter not correct parameter");
            }
        } while (!typeWork.equals("1") && !typeWork.equals("2"));

        if (typeWork.equals("1")) {
           client = workWithConsole();
           messenger.sendMessage(client, template);
        } else {
           client = workWithFile(args[0]);
           messenger.sendMessage(client, template, args[1]);
        }

    }

    private static Client workWithFile(String from) {
        Client client = new Client();
        List<String> linesFromFile = new ArrayList<>();
        Map<String, String> mailParameters;

        try{
            FileReader fr = new FileReader(from);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                linesFromFile.add(line);
                line = reader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mailParameters = FileParser.parseString(linesFromFile);

        client.setAddresses(mailParameters.get("address"));
        client.setMailText(mailParameters.get("mailText"));
        client.setReceiverName(mailParameters.get("receiverName"));
        client.setSenderName(mailParameters.get("senderName"));

        return client;
    }

    private static Client workWithConsole() {
        Client client = new Client();

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter address: ");
        client.setAddresses(in.nextLine());

        System.out.print("Please enter receiver's mame: ");
        client.setReceiverName(in.nextLine());

        System.out.print("Please enter text of mail: ");
        client.setMailText(in.nextLine());

        System.out.print("Please enter sender's name: ");
        client.setSenderName(in.nextLine());

        return client;
    }
}
