package ivagonz.antroma.guinet.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class UserContract {

    public static final String DB_NAME = "guinet.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "user";
    public static final String DEFAULT_SORT = Column.NAME + " DESC";
    public static final String AUTHORITY = "ivagonz.antroma.guinet.database.UserProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    public static final int STATUS_ITEM = 1;
    public static final int STATUS_DIR = 2;

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String ALIAS = "alias";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String DNI = "dni";
        public static final String POSITION = "position";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String DEGREE = "degree";

    }
}
