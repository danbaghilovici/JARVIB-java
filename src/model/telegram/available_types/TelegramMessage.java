/* CLASS REPRESENTING THE 'MESSAGE' OBJECT FROM TELEGRAM OFFICIAL DOCS:
 *  https://core.telegram.org/bots/api#message
 *
 *  THE NAMING CONVENTION FOR ATTRIBUTES IS KEPT AS attribute_name (AS USED IN OFFICIAL DOCS)
 *  FOR CORRECT SERIALIZATION LATER ON WITH GSON EXTERNAL LIBRARY.
 *  THE CLASS METHODS FOLLOW THE JAVA NAMING CONVENTIONS.
 *
 *  STATUS: INCOMPLETE
 *  MORE ATTRIBUTES NEED TO BE ADDED
 */

package model.telegram.available_types;

public class TelegramMessage {
    private final int message_id,date,forward_from_mesasage_id,forward_signature;
    private final TelegramUser from,forward_from;
    private final TelegramChat chat,forward_from_chat;
    private final String text;

    public TelegramMessage(int message_id, int date, int forward_from_mesasage_id, int forward_signature, TelegramUser from, TelegramUser forward_from, TelegramChat chat, TelegramChat forward_from_chat, String text) {
        this.message_id = message_id;
        this.date = date;
        this.forward_from_mesasage_id = forward_from_mesasage_id;
        this.forward_signature = forward_signature;
        this.from = from;
        this.forward_from = forward_from;
        this.chat = chat;
        this.forward_from_chat = forward_from_chat;
        this.text = text;
    }

    public int getMessageId() {
        return message_id;
    }

    public int getDate() {
        return date;
    }

    public int getForwardFromMesasageId() {
        return forward_from_mesasage_id;
    }

    public int getForwardSignature() {
        return forward_signature;
    }

    public TelegramUser getFrom() {
        return from;
    }

    public TelegramUser getForwardFrom() {
        return forward_from;
    }

    public TelegramChat getChat() {
        return chat;
    }

    public TelegramChat getForwardFromChat() {
        return forward_from_chat;
    }

    public String getText() {
        return text;
    }
}
