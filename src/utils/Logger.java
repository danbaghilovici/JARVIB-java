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

    public void log(String msg){
        System.out.println(disabled?"":msg);
    }
    public void logerr(String msg){
        System.err.println(disabled?"":msg);
    }

}
