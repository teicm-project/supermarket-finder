package teicm_team.supermarket_finder;

import android.provider.BaseColumns;

/**
 * Created by Stergios Tselios on 22/10/2016.
 */

///// Ορίζει τις σταθερές που χρησιμοποιούνται για την/////
///// πρόσβαση στα δεδομένα της βάσης/////

public class ListaDB {
    public static final String DB_NAME = "todolist.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}