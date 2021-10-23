package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.IncomingData;
import com.epam.ld.module2.testing.IncomingDataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    void expectedException() {
        Template template = new Template();
        Client client = new Client();
        client.setAddresses("test@mail.ru");
        client.setMailText("Test text #{tag}");
        client.setReceiverName("John");

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            templateEngine.generateMessage(template, client);
        });
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

    @ParameterizedTest
    @CsvSource({
            "est@mail.ru, John, I want to tell you interesting story, Tom"
    })
    void testWithParams(ArgumentsAccessor argumnt) {
        Template template = new Template();
        Client client = new Client();
        client.setAddresses(argumnt.getString(0));
        client.setMailText(argumnt.getString(2));
        client.setReceiverName(argumnt.getString(1));
        client.setSenderName(argumnt.getString(3));
        String expectedResult = "Hello John. I want to tell you interesting story. Best regard from Tom";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.assertEquals(expectedResult, result);

    }
}
