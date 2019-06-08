package portugles.com.innova.innova;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    //Database name
    private static final String DATABASE_NAME = "firebaseinnovanica";
    //Version of the database. Changing the version will call {@Link OrmLite.onUpgrade}
    private static final int DATABASE_VERSION = 1;

    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
    private Dao<usermodel, Long> usuarioDao;



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                 /*
                 * R.raw.ormlite_config is a reference to the ormlite_config.txt file in the
                 * /res/raw/ directory of this project
                 */
                R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates the database table
             */
            TableUtils.createTable(connectionSource, usermodel.class);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /*
        It is called when you construct a SQLiteOpenHelper with version newer than the version of the opened database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            TableUtils.dropTable(connectionSource, usermodel.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an instance of the data access object
     * @return
     * @throws SQLException
     */
    public Dao<usermodel, Long> getDaoUser() throws SQLException {
        if(usuarioDao == null) {
            try {
                usuarioDao = getDao(usermodel.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarioDao;
    }

    @Override
    public void close() {
        super.close();
        usuarioDao = null;

    }

}






