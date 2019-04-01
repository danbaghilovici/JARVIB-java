package http;


import core.CustomBot;
import model.telegram.available_methods.TelegramBaseMethod;

public class TelegramSyncHTTPSRequester extends SyncHTTPSRequester{
    public static final String REQUEST_AVAILABLE_METHODS[]={"GET","POST"};
    private static final String REQUEST_BASE_URL="https://api.telegram.org/bot";
    private final CustomBot botInfo;
    private final TelegramBaseMethod queryMethodBuilder;
    private final String token;

    public TelegramSyncHTTPSRequester(String token, TelegramBaseMethod requestMethod) {
        super();
        this.botInfo=null;
        this.queryMethodBuilder = requestMethod;
        this.token=token;
    }

    public TelegramSyncHTTPSRequester(CustomBot botInfo, TelegramBaseMethod requestMethod) {
        super();
        this.botInfo=botInfo;
        this.token=null;
        this.queryMethodBuilder = requestMethod;
    }

    private String buildRequestUrl(){
        return REQUEST_BASE_URL+ (botInfo==null?token:botInfo.getBotToken()) +queryMethodBuilder.buildQuery();
    }


    @Override
    public String sendRequest() throws Exception {
        super.setRequestUrl(buildRequestUrl());
        return super.sendRequest();
    }
}
