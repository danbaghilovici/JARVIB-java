/* CLASS REPRESENTING THE 'CHAT' OBJECT FROM TELEGRAM OFFICIAL DOCS:
 *  https://core.telegram.org/bots/api#chat
 *
 *  THE NAMING CONVENTION FOR ATTRIBUTES IS KEPT AS attribute_name (AS USED IN OFFICIAL DOCS)
 *  FOR CORRECT SERIALIZATION LATER ON WITH GSON EXTERNAL LIBRARY.
 *  THE CLASS METHODS FOLLOW THE JAVA NAMING CONVENTIONS.
 *
 *  STATUS: COMPLETE
 *  ALL ATTRIBUTES HAVE BEEN ADDED.
 */

package model.telegram.available_types;


public class TelegramChat {
    private final long id;
    private final String type,title,username,first_name,last_name,description,invite_link,sticker_set_name;
    private final boolean all_members_are_administrators,can_set_sticker_set;
    private final TelegramMessage pinned_message;
    private final TelegramChatPhoto photo;


    public TelegramChat(long id, String type, String title, String username, String first_name, String last_name, String description, String invite_link, String sticker_set_name, boolean all_members_are_administrators, boolean can_set_sticker_set, TelegramMessage pinned_message, TelegramChatPhoto photo) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.description = description;
        this.invite_link = invite_link;
        this.sticker_set_name = sticker_set_name;
        this.all_members_are_administrators = all_members_are_administrators;
        this.can_set_sticker_set = can_set_sticker_set;
        this.pinned_message = pinned_message;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getDescription() {
        return description;
    }

    public String getInviteLink() {
        return invite_link;
    }

    public String getStickerSetName() {
        return sticker_set_name;
    }

    public boolean areAllMembersAdministrators() {
        return all_members_are_administrators;
    }

    public boolean canSetStickerSet() {
        return can_set_sticker_set;
    }

    public TelegramMessage getPinnedMessage() {
        return pinned_message;
    }

    public TelegramChatPhoto getPhoto() {
        return photo;
    }
}
