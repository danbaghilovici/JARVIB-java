package core.tasks;

import core.CustomBot;
import model.telegram.available_types.TelegramUpdate;
import utils.DataBaseDemo;



public class ProcessUpdateTask implements Runnable {


    private TelegramUpdate telegramUpdate;
    private CustomBot botInfo;

    public ProcessUpdateTask(CustomBot botInfo,TelegramUpdate telegramUpdate) {
        this.telegramUpdate = telegramUpdate;
        this.botInfo=botInfo;
    }

    @Override
    public void run() {
        DataBaseDemo.LOGGER.consoleLog("UPDATE TASK:","THREAD "+Thread.currentThread().getId()+" TOOK UPDATE "+telegramUpdate.getUpdateId());
        if (telegramUpdate.getMessage().getText().startsWith("/")){
            int langId=DataBaseDemo.findChatLanguagePreferences(telegramUpdate.getMessage().getChat().getId());
            int cmdId= DataBaseDemo.findCommandId(langId,telegramUpdate.getMessage().getText().split(" ")[0]);
            //DataBaseDemo.executeCommand(cmdId,telegramUpdate,botInfo);


        }else{
            //not a command
            //if (telegramUpdate.getMessage().getText())
        }

    }
}
