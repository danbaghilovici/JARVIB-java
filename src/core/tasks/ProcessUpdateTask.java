package core.tasks;

import core.CustomBot;
import http.TelegramSyncHTTPSRequester;
import model.bot.available_macros.BotMacroEveryone;
import model.telegram.available_methods.TelegramMethodDeleteMessage;
import model.telegram.available_methods.TelegramMethodSendMessage;
import model.telegram.available_types.TelegramUpdate;


public class ProcessUpdateTask implements Runnable {
    private static final String TAG="PROCESS THREAD";




    private TelegramUpdate telegramUpdate;
    private CustomBot botInfo;

    public ProcessUpdateTask(CustomBot botInfo,TelegramUpdate telegramUpdate) {
        this.telegramUpdate = telegramUpdate;
        this.botInfo=botInfo;
    }

    @Override
    public void run() {
        if (telegramUpdate.getMessage().getText().contains("!")){
            BotMacroEveryone a=new BotMacroEveryone();
            String result=a.applyMacro(botInfo,telegramUpdate);
            sendMacro(result,botInfo,telegramUpdate);
        }

    }

    public static void sendMacro(String s,CustomBot botInfo,TelegramUpdate telegramUpdate){
        // DELETE
        TelegramMethodDeleteMessage telegramMethodDeleteMessage=new TelegramMethodDeleteMessage();
        telegramMethodDeleteMessage.updateFieldValue(TelegramMethodDeleteMessage.CHAT_ID,telegramUpdate.getMessage().getChat().getId());
        telegramMethodDeleteMessage.updateFieldValue(TelegramMethodDeleteMessage.MESSAGE_ID,telegramUpdate.getMessage().getMessageId());
        TelegramSyncHTTPSRequester requester=new TelegramSyncHTTPSRequester(botInfo,telegramMethodDeleteMessage);
        try {
            requester.sendRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // SEND
        TelegramMethodSendMessage telegramMethodSendMessage =new TelegramMethodSendMessage();
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.CHAT_ID,telegramUpdate.getMessage().getChat().getId());
        telegramMethodSendMessage.updateFieldValue(TelegramMethodSendMessage.TEXT,telegramUpdate.getMessage().getFrom().getUsername()+" : "+s);
        requester=new TelegramSyncHTTPSRequester(botInfo,telegramMethodSendMessage);
        try {
            requester.sendRequest();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
