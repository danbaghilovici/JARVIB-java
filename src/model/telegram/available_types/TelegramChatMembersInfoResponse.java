package model.telegram.available_types;

import java.util.ArrayList;

public class TelegramChatMembersInfoResponse {
    private boolean ok;
    private ArrayList<TelegramChatMemberInfo> result;

    public TelegramChatMembersInfoResponse(boolean ok, ArrayList<TelegramChatMemberInfo> result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public ArrayList<TelegramChatMemberInfo> getResult() {
        return result;
    }
}
