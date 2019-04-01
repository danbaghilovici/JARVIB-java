package model.telegram.available_methods;

import http.QueryAttribute;


public class TelegramMethodSendMessage extends TelegramBaseMethod {
    public static final String CHAT_ID="chat_id";
    public static final String TEXT="text";
    public static final String PARSE_MODE="parse_mode";
    public static final String DISABLE_WEB_PAGE_PREVIEW="disable_web_page_preview";
    public static final String DISABLE_NOTIFICATION="disable_notification";
    public static final String REPLY_TO_MESSAGE_ID="reply_to_message_id";
    public static final String REPLY_MARKUP="disable_web_page_preview";

    private static final String METHOD_NAME="sendMessage";
    private static final QueryAttribute[] AVAILABLE_PARAMETERS={
            new QueryAttribute<Long>(CHAT_ID,true),
            new QueryAttribute<String>(TEXT,true),
            new QueryAttribute<String>(PARSE_MODE,false),
            new QueryAttribute<Boolean>(DISABLE_WEB_PAGE_PREVIEW,false),
            new QueryAttribute<Boolean>(DISABLE_NOTIFICATION,false),
            new QueryAttribute<Integer>(REPLY_TO_MESSAGE_ID,false),
            new QueryAttribute<Object>(REPLY_MARKUP,false),
    };

    public TelegramMethodSendMessage() {
        super(METHOD_NAME, AVAILABLE_PARAMETERS);
    }

}
