package model.telegram.available_types;

import java.util.ArrayList;

public class TelegramChatMembersInfo {
    private boolean ok;
    private ArrayList<TelegramUser> result;

    public TelegramChatMembersInfo(boolean ok, ArrayList<TelegramUser> result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public ArrayList<TelegramUser> getResult() {
        return result;
    }

    public void setResult(ArrayList<TelegramUser> result) {
        this.result = result;
    }
}
