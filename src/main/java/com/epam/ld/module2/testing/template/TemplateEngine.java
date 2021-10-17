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
    public String generateMessage(Template template, Client client) throws IllegalArgumentException {
        String generatedMessage = null;
        try {
            if (client.getReceiverName() != null && client.getMailText() != null && client.getSenderName() != null) {
               generatedMessage = String.format(template.getMailTemplate(),
                        client.getReceiverName(),
                        client.getMailText(),
                        client.getSenderName());
            } else {
                throw new IllegalArgumentException("not valid value");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("not valid value");
        }


        return generatedMessage;
    }
}
