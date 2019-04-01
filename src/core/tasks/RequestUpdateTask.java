package core.tasks;

import com.google.gson.Gson;

import core.CustomBot;
import core.Launcher;
import model.telegram.available_methods.TelegramBaseMethod;
import http.TelegramSyncHTTPSRequester;
import model.telegram.available_methods.TelegramMethodGetUpdates;
import model.telegram.available_types.TelegramResponse;


import java.util.concurrent.atomic.AtomicInteger;

public class RequestUpdateTask implements Runnable {
    private static final String TAG="REQUEST THREAD";

    private boolean initialRun;
    private CustomBot botInfo;
    private final AtomicInteger lastUpdateId;


    private TelegramSyncHTTPSRequester requester;


    public RequestUpdateTask(boolean initialRun, AtomicInteger lastUpdateId, CustomBot botInfo) {
        this.initialRun = initialRun;
        this.botInfo = botInfo;
        this.lastUpdateId = lastUpdateId;

    }

    @Override
    public void run() {
        TelegramBaseMethod telegramUpdateMethod=new TelegramMethodGetUpdates();
        try {
            if (initialRun){
                telegramUpdateMethod.updateFieldValue(TelegramMethodGetUpdates.OFFSET,-1);
            }else{
                telegramUpdateMethod.updateFieldValue(TelegramMethodGetUpdates.OFFSET,lastUpdateId);
            }
            requester=new TelegramSyncHTTPSRequester(botInfo,telegramUpdateMethod);
            String requestResult=requester.sendRequest();

            if (requestResult!=null){

                Gson gson=new Gson();
                TelegramResponse result=gson.fromJson(requestResult,TelegramResponse.class);
                if (initialRun){
                    if (result.getResult().size()!=0){

                        lastUpdateId.set(result.getResult().get(0).getUpdateId());

                    }

                }else {
                    if(result.getResult().size()==0){
                        //NO UPDATES COMMING
                    }else{
                        //UPDATES
                        lastUpdateId.set(result.getResult().get(result.getResult().size()-1).getUpdateId()+1);
                        for (int i=0;i<result.getResult().size();i++){
                            botInfo.getProcessThreadPool().execute(
                                    new ProcessUpdateTask(botInfo,result.getResult().get(i)));
                        }
                    }
                }

            }else{
                Launcher.LOGGER.consoleLog(TAG,true,"REQUEST DENIED");
            }
        } catch (Exception e) {

            Launcher.LOGGER.consoleLog(TAG,true,"COULD NOT SEND REQUEST "+e.getMessage());
        }
    }
}
