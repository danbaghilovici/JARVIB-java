package utils;


import core.CustomBot;
import model.bot.available_commands.BotBaseCommand;
import model.bot.available_commands.BotCommandEcho;
import model.bot.available_commands.BotCommandSayHi;
import model.telegram.available_types.TelegramUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseDemo {

    public static final Logger LOGGER=new Logger();
    public static final int DEFAULT_LANGUAGE_INDEX=0;

    public static final ArrayList<ArrayList<String>> AVAILABLE_COMMANDS_PER_LANGUAGE=new ArrayList<>();
    public static final Map<Long,Integer> LANGUAGE_PREFERANCES=new HashMap<>();



    public static void init(){

        ArrayList<String> availableCommandsEn=new ArrayList<>();
        availableCommandsEn.add("/sayhi");
        availableCommandsEn.add("/echo");

        ArrayList<String> availableCommandsRo=new ArrayList<>();
        availableCommandsRo.add("/zibuna");
        availableCommandsRo.add("/ecou");

        AVAILABLE_COMMANDS_PER_LANGUAGE.add(availableCommandsEn);
        AVAILABLE_COMMANDS_PER_LANGUAGE.add(availableCommandsRo);

        LANGUAGE_PREFERANCES.put(new Long("-1001309309605"),new Integer(1));



    }
    public static int findChatLanguagePreferences(Long chatId){
       return LANGUAGE_PREFERANCES.get(chatId);
    }

    public static int findCommandId(int lang,String command){
        return AVAILABLE_COMMANDS_PER_LANGUAGE.get(lang).indexOf(command);
    }

    public static void executeCommand(int commandId, TelegramUpdate telegramUpdate, CustomBot botInfo){
        BotBaseCommand aux=null;
        switch (commandId){
            case 0:
                 aux=new BotCommandSayHi(null);
                break;
            case 1:
                String[] args=telegramUpdate.getMessage().getText().split(" ");
                if (args.length>=2){
                    aux=new BotCommandEcho(args);
                }
                break;

        }
        aux.execute(botInfo,telegramUpdate);

    }






}
