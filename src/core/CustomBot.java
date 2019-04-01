package core;


import inter.Initiable;
import model.telegram.available_types.TelegramUser;
import utils.DataBaseDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CustomBot extends TelegramUser implements Initiable {

    private final String botToken;
    private BotUpdater botUpdater;
    private final ExecutorService processThreadPool;




    public CustomBot(String token,TelegramUser user,boolean individualPool) {
        super(user.getId(),user.isBot(),user.getFirstName());
        this.botToken = token;
        if (individualPool){
            processThreadPool= Executors.newFixedThreadPool(Launcher.getNumberThreadsIndividualPool());
        }else{
            processThreadPool= Launcher.getProcessThreadPool();
        }
    }

    public ExecutorService getProcessThreadPool() {
        return processThreadPool;
    }

    public String getBotToken() {
        return botToken;
    }



    public void init(){
        DataBaseDemo.LOGGER.log("INITIALIZATING BOT "+getFirstName()+"(ID: "+getId()+")");
        botUpdater =new BotUpdater(this);
        DataBaseDemo.LOGGER.log("SUCCESS INITIALIZATING BOT "+getFirstName()+"(ID: "+getId()+")");
    }

    @Override
    public void destroy() {


    }

    public void start(){
        init();
        DataBaseDemo.LOGGER.log("TRYING TO START BOT "+getFirstName()+"(ID: "+getId()+")");
        try{

            botUpdater.start();
        }catch (Exception e){

        }

    }

    @Override
    public void stop() {

    }

    @Override
    public void terminate() {

    }
}
