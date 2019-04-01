/* CLASS REPRESENTING THE 'USER' OBJECT FROM TELEGRAM OFFICIAL DOCS:
 *  https://core.telegram.org/bots/api#user
 *
 *  THE NAMING CONVENTION FOR ATTRIBUTES IS KEPT AS attribute_name (AS USED IN OFFICIAL DOCS)
 *  FOR CORRECT SERIALIZATION LATER ON WITH GSON EXTERNAL LIBRARY.
 *  THE CLASS METHODS FOLLOW THE JAVA NAMING CONVENTIONS.
 *
 *  STATUS: COMPLETE
 *  ALL ATTRIBUTES HAVE BEEN ADDED.
 */

package model.telegram.available_types;


public class TelegramUser {
    private final int id;
    private final boolean is_bot;
    private final String first_name,last_name,username,language_code;

    public TelegramUser(int id, boolean is_bot, String first_name, String last_name, String username, String language_code) {
        this.id = id;
        this.is_bot = is_bot;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.language_code = language_code;
    }

    public int getId() {
        return id;
    }

    public boolean isBot() {
        return is_bot;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguageCode() {
        return language_code;
    }
}
