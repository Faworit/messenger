package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.IncomingData;
import com.epam.ld.module2.testing.IncomingDataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TemplateEngineTest {
    TemplateEngine templateEngine = new TemplateEngine();

    @Test
    void generateMessageTest() {
        //given
        Template template = new Template();
        Client client = new Client();
        client.setAddresses("test@mail.ru");
        client.setMailText("Test text");
        client.setReceiverName("John");
        client.setSenderName("Tom");
        String expectedResult = "Hello John. Test text. Best regard from Tom";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void generateMessageErrorTest() {
        Template template = new Template();
        Client client = new Client();
        client.setAddresses("test@mail.ru");
        client.setMailText("Test text #{tag}");
        client.setReceiverName("John");
        String expectedResult = "Hello John. Test text #{tag}. Best regard from Tom";

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            templateEngine.generateMessage(template, client);
        });
    }

    @Test
    void generateMessageRedundantValues() {

    }

    @Test
    void supportMessageWithTag() {
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
    void workWithConsole() {
        IncomingData client = new IncomingDataMock();
        Assertions.assertEquals("JohnMock", client.workWithConsole().getSenderName());
        Assertions.assertEquals("TomMock", client.workWithConsole().getReceiverName());
        Assertions.assertEquals("mock", client.workWithConsole().getAddresses());
        Assertions.assertEquals("mock@gmail.com", client.workWithConsole().getMailText());
    }
}
