package db;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2016/1/11.
 */
public class Db {
    private static DbManager db;
    private static DbManager.DaoConfig config;

    public static DbManager getInstance(){
            config = new DbManager.DaoConfig();
            config.setDbName("sport");
            File file = new File("sport");
            config.setDbDir(file);
           db = x.getDb(config);
        return db;
    }
}
