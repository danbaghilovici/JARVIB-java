package model.telegram.available_methods;

import http.QueryAttribute;

public class TelegramMethodDeleteMessage extends TelegramBaseMethod {

    public static final String CHAT_ID="chat_id";
    public static final String MESSAGE_ID="message_id";

    private static final String METHOD_NAME="deleteMessage";
    private static final QueryAttribute[] AVAILABLE_PARAMETERS={
            new QueryAttribute(CHAT_ID,true),
            new QueryAttribute(MESSAGE_ID,true)
    };

    public TelegramMethodDeleteMessage() {
        super(METHOD_NAME, AVAILABLE_PARAMETERS);
    }
}
