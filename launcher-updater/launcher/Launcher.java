package launcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

    private static String newver = "";
    private static final String URL;
    private static final String LIB;
    private static final String VERS;
    private static final String LIBRARY;
    private static final String SOFTWARE;
    private static final String VERSION_PREFIX;
    
    static {
        newver = "";
        // https://debman.xyz/
        // http://localhost/
        URL = "https://debman.xyz/";
        LIB = "lib/";
        VERS = "vers";
        LIBRARY = "library/";
        SOFTWARE = "software/";
        VERSION_PREFIX = "ver-";
    }

    public static void main(String[] args) throws IOException {
        tryUpdate();
        launchApp();
    }

    private static void tryUpdate() {
        try {
            String curver = getCurrentVersion();
            if (isUpdatable(curver)) {
                updateApp();
                updateLib();
            }
        } catch (IOException ex) {
            System.out.println("Algo malió sal: " + ex.getLocalizedMessage());
        }
    }
    
    private static void updateLib() throws MalformedURLException, IOException {
        clearLib();
        
        URL url = new URL(URL + LIB);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        url.openStream()
                )
        );
        
        List<String> libs;
        libs = new ArrayList<>();

        String index;
        while ((index = reader.readLine()) != null) {
            libs.add(index);
        }
        
        for(String libName: libs) {
            downloadLib(libName);
        }
    }
    
    private static void clearLib() {
        File file = new File("lib");
        File[] fileEntries = file.listFiles();
        for(File entry: fileEntries) {
            entry.delete();
        }
    }

    private static void launchApp() throws IOException {
        String key = getKey();
        Runtime.getRuntime().exec("java -jar package2.bin " + key);
        System.exit(0);
    }
    
    private static String getKey() throws MalformedURLException, IOException {
        List<Parameter> params;
        params = new ArrayList<>();
        Parameter keyParam = setParam("version", newver);

        params.add(keyParam);

        String response;
        response = Requester.apply(Request.getVersionKey, params);
        
        return response.trim();
    }

    private static boolean isUpdatable(String curver) throws MalformedURLException, IOException {
        URL url = new URL(URL+VERS);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        url.openStream()
                )
        );

        List<String> versions;
        versions = new ArrayList<>();

        String index;
        while ((index = reader.readLine()) != null) {
            versions.add(index);
        }

        newver = versions.get(versions.size() - 1);
        versions.remove(versions.size() - 1);

        for (String ver : versions) {
            if (curver.equals(ver)) {
                return true;
            }
        }

        return !curver.equals(newver);
    }

    private static String getCurrentVersion() throws IOException {
        String curver;
        try {
           BufferedReader reader = new BufferedReader(new FileReader(new File("curver")));
           curver = reader.readLine().trim();
           return curver;
        }
        catch(FileNotFoundException ex) {
           curver = "1.0";
           File file = new File("curver");
           file.createNewFile();
           FileWriter fw = new FileWriter(file);
           BufferedWriter bw = new BufferedWriter(fw);
           bw.write(curver);
           bw.close();
           
           BufferedReader reader = new BufferedReader(new FileReader(file));
           curver = reader.readLine().trim();
           return curver;
        }
    }
    
    private static void downloadLib(String lib) throws MalformedURLException, IOException {
        String dir = LIB;
        
        String url;
        url = URL+LIBRARY + lib;
        
        InputStream in = new URL(url).openStream();
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);

        FileOutputStream fileOutputStream = new FileOutputStream(dir+lib);
        FileChannel fileChannel = fileOutputStream.getChannel();

        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }

    private static void updateApp() throws MalformedURLException, IOException {
        String url;
        String versionSufix = newver;
        
        url = URL+SOFTWARE+VERSION_PREFIX+versionSufix;

        String name;
        name = "package2.bin";

        InputStream in = new URL(url).openStream();
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);

        FileOutputStream fileOutputStream = new FileOutputStream(name);
        FileChannel fileChannel = fileOutputStream.getChannel();

        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        writeCurrentVersion();
    }

    private static void writeCurrentVersion() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("curver")
        );
        writer.write(newver);

        writer.close();
    }
    
    public static Parameter setParam(String name, String content) {
        Parameter param = new Parameter(name, content);
        return param;
    }
}
