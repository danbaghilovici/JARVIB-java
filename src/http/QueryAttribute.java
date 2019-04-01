/*  CLASS REPRESENTING THE FIELD-VALUE ATTRIBUTE IN A URL QUERY.
 *  https://core.telegram.org/bots/api#making-requests
 *  https://en.wikipedia.org/wiki/Query_string
 */

package http;

public class QueryAttribute<T> {
    private final String name;
    private T value;
    private final boolean required;
    private boolean valueUpdated;

    public QueryAttribute(String fieldName, boolean fieldRequired) {
        this.name = fieldName;
        this.required = fieldRequired;
        this.valueUpdated =false;
    }

    public void setValue(T value) {
        this.value = value;
        this.valueUpdated=true;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        //Need to check this
        T value=null;
        if (required){
            if (valueUpdated){
                value=this.value;
            }else{
                throw new IllegalArgumentException();
            }
        }else{
            value=this.value;
        }

        return value;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isValueUpdated() {
        return valueUpdated;
    }
}
