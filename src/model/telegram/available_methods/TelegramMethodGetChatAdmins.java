package model.telegram.available_methods;

import http.QueryAttribute;

public class TelegramMethodGetChatAdmins extends TelegramBaseMethod {
    public static final String CHAT_ID="chat_id";

    private static final String METHOD_NAME="getChatAdministrators";
    private static final QueryAttribute[] AVAILABLE_PARAMETERS={
            new QueryAttribute(CHAT_ID,true)
    };

    public TelegramMethodGetChatAdmins() {
        super(METHOD_NAME, AVAILABLE_PARAMETERS);
    }
}
