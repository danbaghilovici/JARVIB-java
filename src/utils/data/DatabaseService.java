package utils.data;

import core.Launcher;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseService {
    private static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";
    private static final String TAG="DB SERVICE";
    private final String url,user,password;
    private Connection conn;
    private ArrayList<String> macrosList,commandsList;
    private static final String[] SUPPORTED_LANGUAGES ={"en","ro","es"};
    private static final int DEFAULT_LANGUAGE_ID=0;

    public DatabaseService(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            Launcher.LOGGER.consoleLog(TAG,"CONNECTED TO POSTGRES SUCCESFULLY");
        } catch (SQLException e) {
            Launcher.LOGGER.consoleLog(TAG,true,"COULD NOT CONNECT");
        }

    }

    private ResultSet sendQuery(String query){
        if (conn==null){
            Launcher.LOGGER.consoleLog(TAG,true,"NO CONNECTION AVAILABLE");
            return null;
        }
        // create the java statement
        Statement st = null;

        try {
            st = conn.createStatement();
            // execute the query, and get a java resultset
            //query="SELECT * FROM SUPPORTED_LANGUAGES";
            return st.executeQuery(query);

        } catch (Exception e) {
            Launcher.LOGGER.consoleLog(TAG,true,"QUERY INFO: "+e.getMessage());
        }


        return null;
    }

    public Integer getChatLanguagePref(Long chatId){
        String query="SELECT lang_id FROM language_prefs WHERE chat_id="+chatId+";";
        ResultSet result=sendQuery(query);
        if (result==null){
            return null;
        }
        try{
            result.next();
            int langId=result.getInt("lang_id");
            return langId;

        }catch (Exception e){
            //Launcher.LOGGER.consoleLog(TAG,true,"COULD NOT PARSE THE RESULT");
            String secondQuery="INSERT INTO language_prefs (chat_id,lang_id) VALUES("+chatId+","+DEFAULT_LANGUAGE_ID+"); ";
            sendQuery(secondQuery);
        }
        return DEFAULT_LANGUAGE_ID;
    }

    public Integer getCommandId(Integer langId,String command){
        String query="SELECT cmd_id FROM commands_"+ SUPPORTED_LANGUAGES[langId]+" WHERE cmd_str='"+command.toLowerCase()+"';";
        ResultSet result=sendQuery(query);
        if (result==null){
            return null;
        }
        try{
            result.next();
            int cmdId=result.getInt("cmd_id");
            return cmdId;

        }catch (Exception e){
            Launcher.LOGGER.consoleLog(TAG,true,e.getMessage());
        }
        return null;
    }

    public void setChatLanguagePref(Long chatId,String lang){
        int langId=getLangIndex(lang);
        if (langId<0){
            return ;
        }
        String query="INSERT INTO language_prefs (chat_id,lang_id) VALUES ("+chatId+","+langId+")" +
                "ON CONFLICT (chat_id) DO UPDATE SET lang_id="+langId+";";
        ResultSet resultSet=sendQuery(query);
        try{
            resultSet.next();
        }catch (Exception e){
            //Launcher.LOGGER.consoleLog(TAG,true,"COULD NOT INSERT");
        }

    }

    private int getLangIndex(String lang){
        for (int i=0;i<SUPPORTED_LANGUAGES.length;i++){
            if (SUPPORTED_LANGUAGES[i].equals(lang.toLowerCase())){
                return i;
            }
        }
        return -1;
    }

}
