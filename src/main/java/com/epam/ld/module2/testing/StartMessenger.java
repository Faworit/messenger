package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.util.Scanner;

public class StartMessenger {

    public static void main(String[] args) {
        IncomingData incomingData = new IncomingData();
        Client client;
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
           client = incomingData.workWithConsole();
           messenger.sendMessage(client, template);
        } else {
           client = incomingData.workWithFile(args[0]);
           messenger.sendMessage(client, template, args[1]);
        }

    }


}
