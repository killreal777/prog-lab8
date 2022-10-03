package server;

import requestes.CommandRequest;
import exceptions.ConnectionException;
import exceptions.DeserializationException;
import response.Result;
import serialization.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Function;

public class Server extends ServerMultiThread {
    private final Function<CommandRequest, Result> executeCommandFunction;
    private final Serializer<CommandRequest> commandRequestSerializer;
    private final Serializer<Result> resultSerializer;
    private Result result;

    public Server(Function<CommandRequest, Result> executeCommandFunction)
            throws IOException {
        super("localhost", 7700);
        this.executeCommandFunction = executeCommandFunction;
        this.commandRequestSerializer = new Serializer<>();
        this.resultSerializer = new Serializer<>();
    }

    @Override
    protected void handleRequestBuffer(ByteBuffer requestBuffer) {
        try {
            CommandRequest request = commandRequestSerializer.deserialize(requestBuffer.array());
            result = executeCommandFunction.apply(request);
        } catch (DeserializationException e) {
            throw new ConnectionException();
        }
    }

    @Override
    protected ByteBuffer prepareResponseBuffer() {
        return ByteBuffer.wrap(resultSerializer.serialize(result));
    }
}
