package client;

import requestes.CommandRequest;
import response.Result;
import serialization.Serializer;

import java.io.IOException;

public class Client extends ClientIo {
    private final Serializer<CommandRequest> requestSerializer;
    private final Serializer<Result> resultSerializer;

    protected Client() {
        super("localhost", 7700);
        this.requestSerializer = new Serializer<>();
        this.resultSerializer = new Serializer<>();
    }

    protected Result executeCommandOnServer(CommandRequest request) throws IOException {
        sendRequest(requestSerializer.serialize(request));
        return resultSerializer.deserialize(getResponse());
    }
}
