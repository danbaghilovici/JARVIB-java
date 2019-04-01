package core;

import com.google.gson.Gson;
import http.TelegramSyncHTTPSRequester;

import model.telegram.available_methods.TelegramMethodGetMe;
import model.telegram.available_types.TelegramBotInfo;
import utils.DataBaseDemo;
import utils.TokenReader;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    private static final boolean INDIVIDUAL_PROCESS_POOLS=false;
    private static final int BOT_ARRAY_INITIAL_CAPACITY=1;
    private static final int AVAILABLE_NUMBER_OF_CORES=Runtime.getRuntime().availableProcessors();
    private static final int NUMBER_THREADS_INDIVIDUAL_POOL=1;
    private static final String TOKENS_FILE ="src/raw/auth/tokens";

    private static ExecutorService processThreadPool;
    private static ArrayList<CustomBot> botList;
    private static TokenReader tokenReader;
    private static TelegramSyncHTTPSRequester httpRequester;
    private static Gson gson;
    private static boolean hadError;
    private static int status;


    public static int main(String[] args) {

        // Init
        init();

        // Get  Custom Bot's info
        getInfo();
        if (hadError){
            System.err.println("Error retrieving data.");
            destroy();
            status=-1;
            return status;
        }else{
            System.out.println("No error");
            start();
        }


        return status;

    }

    private static void init(){
        if (INDIVIDUAL_PROCESS_POOLS){
            processThreadPool= null;
        }else{
            processThreadPool= Executors.newFixedThreadPool(AVAILABLE_NUMBER_OF_CORES);
        }
        status=0;
        hadError=false;
        botList=new ArrayList<>(BOT_ARRAY_INITIAL_CAPACITY);
        tokenReader=new TokenReader(TOKENS_FILE);
        gson=new Gson();
        DataBaseDemo.init();
    }
    private static void getInfo(){
        ArrayList<String> tokensArray=tokenReader.read();
        for (String token:tokensArray){
            httpRequester =new TelegramSyncHTTPSRequester(token,new TelegramMethodGetMe());
            try{
                String requestResult= httpRequester.sendRequest();
                TelegramBotInfo result=gson.fromJson(requestResult, TelegramBotInfo.class);
                CustomBot customBot=new CustomBot(token,result.getResult(),INDIVIDUAL_PROCESS_POOLS);
                botList.add(customBot);
            }catch (Exception e){
                System.err.println(e.getMessage());
                hadError=true;
            }
        }

    }

    private static void start(){
        for (CustomBot bot:botList){
            bot.start();
        }

    }

    private static void stop(){

    }

    private static void terminate(){

    }

    private static void destroy(){
        botList=null;
        tokenReader=null;
        gson=null;
        processThreadPool=null;
    }

    public static boolean isIndividualProcessPools() {
        return INDIVIDUAL_PROCESS_POOLS;
    }

    public static ExecutorService getProcessThreadPool(){
        return processThreadPool;
    }

    public static int getNumberThreadsIndividualPool(){
        return NUMBER_THREADS_INDIVIDUAL_POOL;
    }

}
