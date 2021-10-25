package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.IncomingData;
import com.epam.ld.module2.testing.IncomingDataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;


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
        assertEquals(expectedResult, result);
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
        assertEquals(expectedResult, result);
    }

    @Test
    void workWithConsole() {
        IncomingData client = new IncomingDataMock();
        assertEquals("JohnMock", client.workWithConsole().getSenderName());
        assertEquals("TomMock", client.workWithConsole().getReceiverName());
        assertEquals("mock", client.workWithConsole().getAddresses());
        assertEquals("mock@gmail.com", client.workWithConsole().getMailText());
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
        assertEquals(expectedResult, result);
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        Template template = new Template();
        Client client1 = clientGenerator("est@mail.ru",
                "I want to tell you interesting story",
                "John",
                "Tom");
        Client client2 = clientGenerator("test@mail.ru",
                "Second story",
                "Bill",
                "Tom");

        String expectedResult1 = "Hello John. I want to tell you interesting story. Best regard from Tom";
        String expectedResult2 = "Hello Bill. Second story. Best regard from Tom";

        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> assertEquals(expectedResult1,
                        templateEngine.generateMessage(template, client1))),
                dynamicTest("2nd dynamic test", () -> assertEquals(expectedResult2,
                        templateEngine.generateMessage(template, client2)))
        );
    }

    Client clientGenerator(String address,
                           String mailText,
                           String receiverName,
                           String senderName) {
        Client client = new Client();
        client.setAddresses(address);
        client.setMailText(mailText);
        client.setReceiverName(receiverName);
        client.setSenderName(senderName);

        return client;
    }
}
