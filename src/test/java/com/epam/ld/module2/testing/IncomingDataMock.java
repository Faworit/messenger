package com.epam.ld.module2.testing;

public class IncomingDataMock extends IncomingData {

    @Override
    public Client workWithConsole() {
        Client client = new Client();
        client.setSenderName("JohnMock");
        client.setReceiverName("TomMock");
        client.setAddresses("mock");
        client.setMailText("mock@gmail.com");

        return client;
    }
}
