package control;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.IO.Reader;
import view.pop_up_view.LogInView;

/**
 * Class description: This class is used to start the application.
 *
 * @author Jonathan Torres
 * @since 28/01/2021
 * @version 1.0
 */
public class Main {

    private static File instanceValidation;
    private static FileChannel channel;
    private static FileLock lock;

    public static void main(String[] args)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            String updatedKey = args[0];
            if (updatedKey.equals("null")) {
                throw new Exception();
            }
            if (!Reader.verifyUpdated(updatedKey)) {
                throw new Exception();
            }
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LogInView.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (Exception e) {
            MainController.alert("Error", "Ocurrió un problema y la aplicación no puede ejecutarse", "Aceptar");
            System.exit(0);
        }

        try {
            String path = System.getenv("USERPROFILE") + "\\.debtorManager\\";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            instanceValidation = new File(path + "instanceValidation.dat");
            if (instanceValidation.exists()) {
                instanceValidation.delete();
            }
            channel = new RandomAccessFile(instanceValidation,
                    "rw").getChannel();
            lock = channel.tryLock();

            if (lock
                    == null) {
                channel.close();
                throw new RuntimeException(
                        "Debtor Manager ya se encuentra en ejecución");
            }

            Thread shutdown = new Thread(new Runnable() {
                @Override
                public void run() {
                    unlock();
                }
            });

            Runtime.getRuntime().addShutdownHook(shutdown);

        } catch (RuntimeException ex) {
            MainController.alert("Error", ex.getMessage(), "Aceptar");
            System.exit(0);
        }

        MainController.start();
    }

    private static void unlock() {
        try {
            if (lock
                    != null) {
                lock.release();
                channel.close();
                instanceValidation.delete();
            }
        } catch (IOException ex) {
        }
    }
}
