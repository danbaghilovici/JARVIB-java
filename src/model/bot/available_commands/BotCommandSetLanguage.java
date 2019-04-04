package model.bot.available_commands;

import com.google.gson.Gson;
import core.CustomBot;
import http.TelegramSyncHTTPSRequester;
import model.telegram.available_methods.TelegramMethodSendMessage;
import model.telegram.available_types.TelegramResponse;
import model.telegram.available_types.TelegramUpdate;
import utils.data.DatabaseService;

public class BotCommandSetLanguage extends BotBaseCommand{
    private static final int commandId=2;
    private static final boolean commandTakesArgs=true;
    public static final String commandName="/setLanguage";
    private String[] commandArgs;
    private DatabaseService databaseService;

    public BotCommandSetLanguage() {
        super(commandId, commandTakesArgs, commandName);
    }

    public BotCommandSetLanguage(String[] commandArgs, DatabaseService databaseService) {
        super(commandId,commandTakesArgs,commandName,commandArgs,databaseService);
        this.commandArgs = commandArgs;
        this.databaseService=databaseService;
    }
    @Override
    public boolean execute(CustomBot botInfo, TelegramUpdate telegramUpdate) {
        for (int i=0;i<commandArgs.length;i++){
            System.out.println(commandArgs[i]);
        }
        databaseService.setChatLanguagePref(telegramUpdate.getMessage().getChat().getId(),commandArgs[1]);
        return true;

    }
    public int getCommandId(){
        return commandId;
    }

    @Override
    public void setCommandArgs(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
