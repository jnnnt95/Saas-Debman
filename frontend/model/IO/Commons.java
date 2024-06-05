package model.IO;

import com.google.gson.JsonObject;
import control.MainController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Parameter;
import model.enums.Request;

public abstract class Commons {
    
    private static boolean SET;
    private static String textURL;
    private static URL objectURL;
    private static Map<String, String> params;
    private static StringBuilder postData;
    private static byte[] postDataBytes;
    private static BufferedReader reader;
    private static String json;
    private static String index;
    private static HttpURLConnection connection;

    //key variable REQUEST containing keyword "request"
    private static final String REQUEST;
    
    static {
        SET = false;
        // https://www.debman.xyz/app/request.php
        // http://localhost/app/request.php
        textURL = "https://www.debman.xyz/app/request.php";
        REQUEST = "request";
    }
    
    public static Parameter setParam(String name, String content) {
        Parameter param = new Parameter(name, content);
        return param;
    }
    
    public static void showErrorMessage(JsonObject error) {
        String name;
        String message;
        boolean sessionError;

        sessionError = error.get("sessionError").getAsBoolean();
        
        name = error.get("name").getAsString();
        message = error.get("message").getAsString();
        if(sessionError) {
            message += "... error fatal, el programa se cerrará...";
        }
        MainController.alert("Error", name + "\n\n" + message, "Aceptar");
        
        if(sessionError) {
            System.exit(0);
        }
    }
    
    public static void addSessionData(List<Parameter> params) {
        Parameter userIdParam = setParam("userId", String.valueOf(MainController.getUser().getId()));
        Parameter sessionKeyParam = setParam("sessionKey", MainController.getSessionKey());
        
        params.add(userIdParam);
        params.add(sessionKeyParam);
    }
    
    public static String apply(Request request, List<Parameter> data) throws UnsupportedEncodingException, IOException {
        if (!SET) {
            try {
                initializeRequester();
                SET = true;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Commons.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Commons.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        params = new LinkedHashMap<>();
        params.put(REQUEST, request.name());
        setParams(data);
        preparePosting();
        readJsonResponse();

        return json;
    }

    private static String readJsonResponse() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        json = "";
        while ((index = reader.readLine()) != null) {
            json += index;
        }
        reader.close();
        return json;
    }

    private static void setParams(List<Parameter> paramSource) {
        for (Parameter param : paramSource) {
            params.put(param.getName(), param.getContent());
        }
    }

    private static void preparePosting() throws UnsupportedEncodingException, IOException {
        postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append("=");
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        postDataBytes = postData.toString().getBytes("UTF-8");
        
        connection = (HttpURLConnection) objectURL.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        try {
            connection.getOutputStream().write(postDataBytes);
        }
        catch(ConnectException e) {
            MainController.alert("Error de conexión", "No fue posible conectar con el servidor. Por favor, inténtelo de nuevo más tarde", "Aceptar");
            System.exit(0);
        }
    }

    private static void initializeRequester() throws MalformedURLException, UnsupportedEncodingException, IOException {
        objectURL = new URL(textURL);
    }
}
