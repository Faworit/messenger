package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String generatedMessage = String.format(template.getMailTemplate(),
                                                client.getReceiverName(),
                                                client.getMailText(),
                                                client.getSenderName());
        System.out.println("Test: " + generatedMessage);
        return generatedMessage;
    }
}
