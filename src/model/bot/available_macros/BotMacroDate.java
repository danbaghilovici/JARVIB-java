package model.bot.available_macros;

import core.CustomBot;
import model.telegram.available_types.TelegramUpdate;

public class BotMacroDate extends BotBaseMacro {
    private static final int macroId=1;
    private static final boolean macroTakesArgs=false;
    private static final String macroName=BotBaseMacro.MACRO_SIGNAL+"date";
    private String[] macroArgs;

    public BotMacroDate() {
        super(macroId, macroTakesArgs, macroName);
    }

    @Override
    public String applyMacro(CustomBot customBot, TelegramUpdate telegramUpdate) {
        

    }
}
