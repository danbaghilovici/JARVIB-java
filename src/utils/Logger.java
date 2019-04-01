package utils;

public class Logger {
    private static Logger loggerInstance=null;
    private boolean disabled;

    public Logger() {
        this.disabled = false;
    }

    public Logger(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }
    public static Logger getInstance(){
        if (loggerInstance==null){
            loggerInstance=new Logger();

        }return loggerInstance;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void consoleLog(String TAG,boolean error,String msg){
        if (isDisabled()){
            return;
        }
        if (error){
            System.err.println(TAG+" | "+msg);
        }else{
            System.out.println(TAG+" | "+msg);
        }
    }

    public void consoleLog(String TAG,String msg){
        if (isDisabled()){
            return;
        }
        System.out.println(TAG+" | "+msg);

    }

}
