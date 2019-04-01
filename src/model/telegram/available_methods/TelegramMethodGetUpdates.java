package model.telegram.available_methods;


import http.QueryAttribute;

public class TelegramMethodGetUpdates extends TelegramBaseMethod {
    public static final String OFFSET="offset";
    public  static final String LIMIT="limit";
    public static final String TIMEOUT="timeout";
    public static final String ALLOWED_UPDATES="allowed_updates";



    private static final String METHOD_NAME="getUpdates";
    private static final QueryAttribute[] AVAILABLE_PARAMETERS={
            new QueryAttribute<Integer>(OFFSET,false),
            new QueryAttribute<Integer>(LIMIT,false),
            new QueryAttribute<Integer>(TIMEOUT,false),
            new QueryAttribute<Integer>(ALLOWED_UPDATES,false),
    };

    public TelegramMethodGetUpdates() {
        super(METHOD_NAME, AVAILABLE_PARAMETERS);
    }



}
