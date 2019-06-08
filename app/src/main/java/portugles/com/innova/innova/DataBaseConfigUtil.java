package portugles.com.innova.innova;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Sergio on 21/09/2015.
 */
public class DataBaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }
}
