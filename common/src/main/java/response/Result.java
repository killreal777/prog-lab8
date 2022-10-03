package response;

import io.Format;
import io.TextFormatter;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean success;
    private String message;
    private Object javaObject;

    public Result(boolean success, String message, Object javaObject) {
        this.success = success;
        this.message = message;
        this.javaObject = javaObject;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.javaObject = null;
    }

    public Result() {
        this.success = false;
        this.message = "";
    }

    public void writeMessageLine(String line) {
        if (!message.equals(""))
            message = message + "\n";
        message = message + line;
    }

    public void setGoodResult(String message) {
        this.message = message;
        this.success = true;
    }

    public void setBadResult(String message) {
        this.message = message;
        this.success = false;
    }

    public void setJavaObject(Object javaObject) {
        this.javaObject = javaObject;
    }

    public boolean isSuccessful() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageColorful() {
        if (success)
            return TextFormatter.format(message, Format.GREEN);
        else
            return TextFormatter.format(message, Format.RED);
    }

    public Object getJavaObject() {
        return javaObject;
    }
}
