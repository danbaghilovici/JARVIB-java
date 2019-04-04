package model.bot.available_macros;

import core.CustomBot;
import model.telegram.available_types.TelegramUpdate;

public class BotBaseMacro {

    public static final String MACRO_SIGNAL="!";
    private final int macroId;
    private final boolean macroTakesArgs;
    private final String macroName;
    private String[] macroArgs;

    public BotBaseMacro(int macroId, boolean macroTakesArgs, String macroName) {
        this.macroId = macroId;
        this.macroTakesArgs = macroTakesArgs;
        this.macroName = macroName;
    }

    public BotBaseMacro(int macroId, boolean macroTakesArgs, String macroName, String[] macroArgs) {
        this.macroId = macroId;
        this.macroTakesArgs = macroTakesArgs;
        this.macroName = macroName;
        this.macroArgs = macroArgs;
    }

    public String applyMacro(CustomBot customBot, TelegramUpdate telegramUpdate){
        return null;
    }
    public static void sendMacroRequest(String s){

    }
}
