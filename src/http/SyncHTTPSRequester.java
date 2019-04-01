package http;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SyncHTTPSRequester {

    private static final String DEFAULT_REQUEST_METHOD="GET";

    private String requestMethod;
    private String requestUrl;
    private boolean urlUpdated;

    public SyncHTTPSRequester() {
        urlUpdated=false;
        requestMethod=DEFAULT_REQUEST_METHOD;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        this.urlUpdated=true;
    }

    public String sendRequest() throws Exception {
        if (!urlUpdated){
            throw new IllegalArgumentException();
        }
        URL requestObj = null;
        requestObj = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) requestObj.openConnection();
        connection.setRequestMethod(requestMethod);
        int responseCode = connection.getResponseCode();
        //System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return null;
        }
    }



}
