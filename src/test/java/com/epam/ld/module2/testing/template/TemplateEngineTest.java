package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TemplateEngineTest {
    TemplateEngine templateEngine = new TemplateEngine();

    @Test
    public void generateMessageTest() {
        //given
        Template template = new Template();
        Client client = new Client();
        client.setAddresses("test@mail.ru");
        client.setMailText("Test text #{tag}");
        client.setReceiverName("John");
        client.setSenderName("Tom");
        String expectedResult = "Hello John. Test text #{tag}. Best regard from Tom";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void generateMessageErrorTest() {
        Template template = new Template();
        Client client = new Client();
        String expectedResult = "Hello John. Test text #{tag}. Best regard from Tom";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.fail(result);
    }

    @Test
    public void generateMessageRedundantValues() {

    }

    @Test
    public void supportMessageWithTag() {
        Template template = new Template();
        Client client = new Client();
        String expectedResult = "Hello John. Test text #{tag}. Best regard from Tom";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.assertEquals(expectedResult, result);
    }
}
