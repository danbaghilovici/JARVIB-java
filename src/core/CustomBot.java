package core;


import inter.Initiable;
import model.telegram.available_types.TelegramUser;
import utils.DataBaseDemo;
import utils.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CustomBot extends TelegramUser implements Initiable {
    private final String TAG;

    private final String botToken;
    private BotUpdater botUpdater;
    private final ExecutorService processThreadPool;


    public CustomBot(String token,TelegramUser user,boolean individualPool) {
        super(user.getId(),user.isBot(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getLanguageCode());
        this.botToken = token;
        if (individualPool){
            processThreadPool= Executors.newFixedThreadPool(Launcher.getNumberThreadsIndividualPool());
        }else{
            processThreadPool= Launcher.getProcessThreadPool();
        }
        TAG="BOT "+this.getFirstName()+" ("+this.getId()+")";
    }

    public ExecutorService getProcessThreadPool() {
        return processThreadPool;
    }

    public String getBotToken() {
        return botToken;
    }



    public void init(){

        botUpdater =new BotUpdater(this);
        Launcher.LOGGER.consoleLog(TAG,"INIT SUCCESS");
    }

    @Override
    public void destroy() {


    }

    public void start(){
        init();
        try{

            botUpdater.start();
        }catch (Exception e){
            Launcher.LOGGER.consoleLog(TAG,true,"FAILED TO START");
        }

    }

    @Override
    public void stop() {

    }

    @Override
    public void terminate() {

    }
}
