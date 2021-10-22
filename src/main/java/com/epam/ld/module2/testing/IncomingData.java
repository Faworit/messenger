package com.epam.ld.module2.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IncomingData {

    public Client workWithFile(String from) {
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

    public Client workWithConsole() {
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
