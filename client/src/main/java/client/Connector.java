package client;

import exceptions.ConnectionException;
import exceptions.DeserializationException;
import requestes.CommandRequest;
import response.Result;

import java.io.IOException;

public class Connector {
    private final Client client;
    private volatile boolean isConnected;

    public Connector() {
        this.client = new Client();
        isConnected = false;
        System.out.println("Connector created");
    }

    public void connect() throws ConnectionException {
        client.connect();
        isConnected = true;
    }

    public Result executeCommandOnServer(CommandRequest request) throws ConnectionException {
        try {
            return client.executeCommandOnServer(request);
        } catch (IOException | DeserializationException e) {
            isConnected = false;
            throw new ConnectionException();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}
