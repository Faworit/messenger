package com.epam.ld.module2.testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParser {

    public static Map<String, String> parseString(List<String> fromFileStrings) {
        Map<String, String> mailParameters = new HashMap<>();
        for (String fileString : fromFileStrings) {
            String[] words = fileString.split(" : ");
            mailParameters.put(words[0], words[1]);
        }

        return mailParameters;
    }
}
