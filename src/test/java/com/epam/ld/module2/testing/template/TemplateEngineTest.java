package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TemplateEngineTest {
    TemplateEngine templateEngine;

    @Test
    public void generateMessageTest() {
        //given
        Template template = new Template();
        Client client = new Client();
        String expectedResult = "from: test@mail.ru, to: petrov@mail.ru body: hello my friend";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void generateMessageErrorTest() {
        Template template = new Template();
        Client client = new Client();
        String expectedResult = "from: test@mail.ru, to: petrov@mail.ru body: hello my friend";

        //when
        String result = templateEngine.generateMessage(template, client);

        //then
        Assertions.fail(result);
    }
}
