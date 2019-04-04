package core.tasks;

import com.google.gson.Gson;
import core.CustomBot;
import http.TelegramSyncHTTPSRequester;
import model.bot.available_commands.BotBaseCommand;
import model.bot.available_macros.BotBaseMacro;
import model.bot.available_macros.BotMacroEveryone;
import model.telegram.available_methods.TelegramMethodGetChatAdmins;
import model.telegram.available_types.TelegramChatMembersInfo;
import model.telegram.available_types.TelegramUpdate;

import java.lang.reflect.Constructor;


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
        }

    }

}
