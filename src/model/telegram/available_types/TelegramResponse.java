/*  CLASS REPRESENTING THE RESPONSE OBJECT GIVEN FROM TELEGRAM ON MAKING A REQUEST.
 *
 *  THE NAMING CONVENTION FOR ATTRIBUTES IS KEPT AS attribute_name (AS USED IN OFFICIAL DOCS)
 *  FOR CORRECT SERIALIZATION LATER ON WITH GSON EXTERNAL LIBRARY.
 *  THE CLASS METHODS FOLLOW THE JAVA NAMING CONVENTIONS.
 *
 *  STATUS: COMPLETE
 *  ALL ATTRIBUTES HAVE BEEN ADDED.
 */

package model.telegram.available_types;

import java.util.ArrayList;

public class TelegramResponse {
    private final boolean ok;
    private final ArrayList<TelegramUpdate> result;

    public TelegramResponse(boolean ok, ArrayList<TelegramUpdate> result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }


    public ArrayList<TelegramUpdate> getResult() {
        return result;
    }

}
