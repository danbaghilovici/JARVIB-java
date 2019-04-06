package model.telegram.available_types;

public class TelegramBotInfoResponse {
    private boolean ok;
    private TelegramUser result;

    public TelegramBotInfoResponse(boolean ok, TelegramUser result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public TelegramUser getResult() {
        return result;
    }

    public void setResult(TelegramUser result) {
        this.result = result;
    }
}
