package core;


import inter.Initiable;
import model.bot.available_commands.BotBaseCommand;
import model.bot.available_commands.BotCommandEcho;
import model.bot.available_commands.BotCommandSayHi;
import model.bot.available_commands.BotCommandSetLanguage;
import model.bot.available_macros.BotBaseMacro;
import model.telegram.available_types.TelegramUser;
import utils.data.DatabaseService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CustomBot extends TelegramUser implements Initiable {
    private final String TAG;

    private final String botToken;
    private BotUpdater botUpdater;
    private final ExecutorService processThreadPool;
    private final ArrayList<BotBaseCommand> AVAILABLE_COMMANDS;
    private final ArrayList<BotBaseMacro> AVAILABLE_MACROS;



    public CustomBot(String token,TelegramUser user,boolean individualPool) {
        super(user.getId(),user.isBot(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getLanguageCode());
        this.botToken = token;
        if (individualPool){
            processThreadPool= Executors.newFixedThreadPool(Launcher.getNumberThreadsIndividualPool());
        }else{
            processThreadPool= Launcher.getProcessThreadPool();
        }
        AVAILABLE_COMMANDS=new ArrayList<>();
        AVAILABLE_MACROS=new ArrayList<>();
        TAG="BOT "+this.getFirstName()+" ("+this.getId()+")";
    }

    public ExecutorService getProcessThreadPool() {
        return processThreadPool;
    }

    public String getBotToken() {
        return botToken;
    }



    public void init(){
        //databaseService.connect();
        botUpdater =new BotUpdater(this);
        Launcher.LOGGER.consoleLog(TAG,"INIT SUCCESS");
        //COMMANDS OBJECTES
        AVAILABLE_COMMANDS.add(new BotCommandEcho());
        AVAILABLE_COMMANDS.add(new BotCommandSayHi());
        AVAILABLE_COMMANDS.add(new BotCommandSetLanguage());


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


    public ArrayList<BotBaseCommand> getAVAILABLE_COMMANDS() {
        return AVAILABLE_COMMANDS;
    }
}
