package launcher;

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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Requester {

    private static JFrame genericFrame;
    
    private static boolean SET = false;
    private static URL url;
    private static Map<String, String> params;
    private static StringBuilder postData;
    private static byte[] postDataBytes;
    private static BufferedReader reader;
    private static String json;
    private static String index;
    private static HttpURLConnection connection;

    //key variable REQUEST containing keyword "request"
    private static String REQUEST = "request";

    public static String apply(Request request, List<Parameter> data) throws UnsupportedEncodingException, IOException {
        if (!SET) {
            try {
                initializeRequester();
                SET = true;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Requester.class.getName()).log(Level.SEVERE, null, ex);
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
        
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        try {
            connection.getOutputStream().write(postDataBytes);
        }
        catch(ConnectException e) {
            alert("Error", "No fue posible importar desde el servidor", "Aceptar");
            System.exit(0);
        }
    }

    private static void initializeRequester() throws MalformedURLException, UnsupportedEncodingException, IOException {
        //real host: https://www.debman.xyz/app/request.php
        url = new URL("https://www.debman.xyz/app/request.php");
    }
    
    
    
    public static void alert(String title, String message, String acceptMessage) {
        if(genericFrame == null) {
            genericFrame = new JFrame();
        }
        String[] options = new String[1];
        options[0] = acceptMessage;
        JOptionPane.showOptionDialog(genericFrame.getContentPane(), 
                        message,
                        title,
                        0, 
                        JOptionPane.WARNING_MESSAGE,
                        null, 
                        options, 
                        null
                );
    }
}
