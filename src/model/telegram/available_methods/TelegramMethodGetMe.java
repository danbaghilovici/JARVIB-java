package model.telegram.available_methods;


import http.QueryAttribute;

public class TelegramMethodGetMe extends TelegramBaseMethod {
    private static final String METHOD_NAME="getMe";
    private static final QueryAttribute[] AVAILABLE_PARAMETERS=null;

    public  TelegramMethodGetMe() {
        super(METHOD_NAME, AVAILABLE_PARAMETERS);
    }

}
