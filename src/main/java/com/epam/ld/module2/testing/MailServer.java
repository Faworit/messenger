package com.epam.ld.module2.testing;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        System.out.println("message was sent to address: " + addresses);
        System.out.println("text of message: " + messageContent);
    }

    public void send(String addresses, String messageContent, String fileName) {
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write("message was sent to address: " + addresses);
            writer.append('\n');
            writer.write("text of message: " + messageContent);

            writer.flush();

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

