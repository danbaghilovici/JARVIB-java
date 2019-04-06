package model.telegram.available_types;

public class TelegramChatMemberInfo {
    private TelegramUser user;
    private String status;
    private boolean can_be_edited,can_change_info,can_delete_messages,can_invite_users,can_restrict_members,can_pin_messages,can_promote_members;

    public TelegramChatMemberInfo(TelegramUser user, String status, boolean can_be_edited, boolean can_change_info, boolean can_delete_messages, boolean can_invite_users, boolean can_restrict_members, boolean can_pin_messages, boolean can_promote_members) {
        this.user = user;
        this.status = status;
        this.can_be_edited = can_be_edited;
        this.can_change_info = can_change_info;
        this.can_delete_messages = can_delete_messages;
        this.can_invite_users = can_invite_users;
        this.can_restrict_members = can_restrict_members;
        this.can_pin_messages = can_pin_messages;
        this.can_promote_members = can_promote_members;
    }

    public TelegramUser getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCan_be_edited() {
        return can_be_edited;
    }

    public boolean isCan_change_info() {
        return can_change_info;
    }

    public boolean isCan_delete_messages() {
        return can_delete_messages;
    }

    public boolean isCan_invite_users() {
        return can_invite_users;
    }

    public boolean isCan_restrict_members() {
        return can_restrict_members;
    }

    public boolean isCan_pin_messages() {
        return can_pin_messages;
    }

    public boolean isCan_promote_members() {
        return can_promote_members;
    }
}
