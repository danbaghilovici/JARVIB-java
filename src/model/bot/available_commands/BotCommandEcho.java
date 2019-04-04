package model.bot.available_commands;

import com.google.gson.Gson;
import core.CustomBot;
import http.TelegramSyncHTTPSRequester;
import model.telegram.available_methods.TelegramMethodSendMessage;
import model.telegram.available_types.TelegramResponse;
import model.telegram.available_types.TelegramUpdate;
import utils.data.DatabaseService;


public class BotCommandEcho extends BotBaseCommand {
    private static final int commandId=0;
    private static final boolean commandTakesArgs=true;
    public static final String commandName="/echo";
    private String[] commandArgs;

    public BotCommandEcho() {
        super(commandId,commandTakesArgs,commandName);
    }

    public BotCommandEcho(String[] commandArgs, DatabaseService databaseService) {
        super(commandId,commandTakesArgs,commandName,commandArgs);
        this.commandArgs = commandArgs;
    }
    @Override
    public boolean execute(CustomBot botInfo, TelegramUpdate telegramUpdate) {
        String textToSend="";
        if (commandArgs==null || commandArgs.length<1){
            return false;
        }else{
            for (int i=1;i<commandArgs.length;i++){
                textToSend+=commandArgs[i]+" ";
            }
        }

        //This command does nothing to the message

        TelegramMethodSendMessage telegramMethodSendMessage=new TelegramMethodSendMessage();
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.CHAT_ID,telegramUpdate.getMessage().getChat().getId());
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.TEXT,textToSend);
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.REPLY_TO_MESSAGE_ID,telegramUpdate.getMessage().getMessageId());
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.DISABLE_NOTIFICATION,true);

        TelegramSyncHTTPSRequester telegramSyncHTTPSRequester=new TelegramSyncHTTPSRequester(botInfo.getBotToken(),telegramMethodSendMessage);
        try{
            String response=telegramSyncHTTPSRequester.sendRequest();
            Gson gson=new Gson();
            TelegramResponse result=gson.fromJson(response,TelegramResponse.class);
            if (result.isOk()){
                return true;
            }else{
                System.err.println("REQUEST NOT OK");
                return false;
            }

        }catch (Exception e){
            System.err.println("REQUEST NOT SEND | "+e.getMessage());
            return false;
        }

    }
    public int getCommandId(){
        return commandId;
    }

    @Override
    public void setCommandArgs(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }
}
