package model.bot.available_commands;

import com.google.gson.Gson;
import core.CustomBot;
import http.TelegramSyncHTTPSRequester;
import model.telegram.available_methods.TelegramMethodSendMessage;
import model.telegram.available_types.TelegramResponse;
import model.telegram.available_types.TelegramUpdate;


public class BotCommandEcho extends BotBaseCommand {
    private static final int commandId=1;
    private static final boolean commandTakesArgs=true;
    public static final String commandName="/echo";
    private String[] commandArgs;



    public BotCommandEcho(String[] commandArgs) {
        super(commandId,commandTakesArgs,commandName,commandArgs);
        this.commandArgs = commandArgs;
    }
    @Override
    public boolean execute(CustomBot botInfo, TelegramUpdate telegramUpdate) {

        //This command does nothing to the message

        TelegramMethodSendMessage telegramMethodSendMessage=new TelegramMethodSendMessage();
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.CHAT_ID,telegramUpdate.getMessage().getChat().getId());
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.TEXT,commandArgs[1]);
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
}
