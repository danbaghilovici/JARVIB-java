package model.bot.available_commands;

import com.google.gson.Gson;
import core.CustomBot;
import http.TelegramSyncHTTPSRequester;
import model.telegram.available_methods.TelegramMethodSendMessage;
import model.telegram.available_types.TelegramResponse;
import model.telegram.available_types.TelegramUpdate;
import utils.data.DatabaseService;


public class BotCommandSayHi extends BotBaseCommand {
    private static final int commandId=1;
    private static final boolean commandTakesArgs=false;
    public static final String commandName="/sayhi";
    private String[] commandArgs;

    private static final String MESAGE_TO_BE_SEND="Hi";

    public BotCommandSayHi() {
        super(commandId, commandTakesArgs, commandName);
    }

    public BotCommandSayHi(String[] commandArgs, DatabaseService databaseService) {
        super(commandId,commandTakesArgs,commandName,commandArgs);
        this.commandArgs = commandArgs;
    }
    @Override
    public boolean execute(CustomBot customBot, TelegramUpdate telegramUpdate) {

        //This command does nothing to the message

        TelegramMethodSendMessage telegramMethodSendMessage=new TelegramMethodSendMessage();
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.CHAT_ID,telegramUpdate.getMessage().getChat().getId());
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.TEXT,MESAGE_TO_BE_SEND);
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.REPLY_TO_MESSAGE_ID,telegramUpdate.getMessage().getMessageId());
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.DISABLE_NOTIFICATION,true);

        TelegramSyncHTTPSRequester telegramSyncHTTPSRequester=new TelegramSyncHTTPSRequester(customBot.getBotToken(),telegramMethodSendMessage);
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

    @Override
    public void setCommandArgs(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }
}
