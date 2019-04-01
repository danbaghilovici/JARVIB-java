/* CLASS REPRESENTING THE 'UPDATE' OBJECT FROM TELEGRAM OFFICIAL DOCS:
 *  https://core.telegram.org/bots/api#update
 *
 *  THE NAMING CONVENTION FOR ATTRIBUTES IS KEPT AS attribute_name (AS USED IN OFFICIAL DOCS)
 *  FOR CORRECT SERIALIZATION LATER ON WITH GSON EXTERNAL LIBRARY.
 *  THE CLASS METHODS FOLLOW THE JAVA NAMING CONVENTIONS.
 *
 *  STATUS: INCOMPLETE
 *  MORE ATTRIBUTES NEED TO BE ADDED
 */

package model.telegram.available_types;

public class TelegramUpdate {
    private final int update_id;
    private final TelegramMessage message, edited_message,channel_post,edited_channel_post;

    public TelegramUpdate(int update_id, TelegramMessage message, TelegramMessage edited_message, TelegramMessage channel_post, TelegramMessage edited_channel_post) {
        this.update_id = update_id;
        this.message = message;
        this.edited_message = edited_message;
        this.channel_post = channel_post;
        this.edited_channel_post = edited_channel_post;
    }

    public int getUpdateId() {
        return update_id;
    }

    public TelegramMessage getMessage() {
        return message;
    }

    public TelegramMessage getEditedMessage() {
        return edited_message;
    }

    public TelegramMessage getChannelPost() {
        return channel_post;
    }

    public TelegramMessage getEditedChannelPost() {
        return edited_channel_post;
    }
}
