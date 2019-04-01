package model.bot.available_commands;

import core.CustomBot;
import model.telegram.available_types.TelegramUpdate;


public class BotBaseCommand {
    private final int commandId;
    private final boolean commandTakesArgs;
    public final String commandName;
    private final String[] commandArgs;

    public BotBaseCommand(int commandId, boolean commandTakesArgs, String commandName, String[] commandArgs) {
        this.commandId = commandId;
        this.commandTakesArgs = commandTakesArgs;
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }

    public boolean execute(CustomBot botInfo, TelegramUpdate telegramUpdate){
        return false;
    }




}
