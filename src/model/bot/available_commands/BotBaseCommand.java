package model.bot.available_commands;

import core.CustomBot;
import model.telegram.available_types.TelegramUpdate;
import utils.data.DatabaseService;


public abstract class BotBaseCommand {
    public static final String COMMAND_SIGNAL="/";
    private final int commandId;
    private final boolean commandTakesArgs;
    public final String commandName;
    private String[] commandArgs;
    private DatabaseService databaseService;

    public BotBaseCommand(int commandId, boolean commandTakesArgs, String commandName) {
        this.commandId = commandId;
        this.commandTakesArgs = commandTakesArgs;
        this.commandName = commandName;
    }

    public BotBaseCommand(int commandId, boolean commandTakesArgs, String commandName, String[] commandArgs) {
        this.commandId = commandId;
        this.commandTakesArgs = commandTakesArgs;
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }

    public BotBaseCommand(int commandId, boolean commandTakesArgs, String commandName, String[] commandArgs, DatabaseService databaseService) {
        this.commandId = commandId;
        this.commandTakesArgs = commandTakesArgs;
        this.commandName = commandName;
        this.commandArgs = commandArgs;
        this.databaseService = databaseService;
    }

    public boolean execute(CustomBot botInfo, TelegramUpdate telegramUpdate){
        return false;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandArgs(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
