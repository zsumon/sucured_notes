package oop_project.loginwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumon on 7/24/2017.
 */

public class DatabaserHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_NAME_USERS = "usersInfo";
    private static final String USER_ID = "id";
    private static final String USERNAME = "user_name";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String SECURITY_QUESTION = "security_question";
    private static final String SECURITY_QUESTION_ANSWER = "security_question_ans";

    private static final String PASSWORD = "password";
    public static String TAG = "TAG";
    private String specificUserTaskTable = "_USER_NAME";
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME_USERS + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERNAME + " TEXT," + PASSWORD + " TEXT,"
            + NAME + " TEXT," + EMAIL + " TEXT," + SECURITY_QUESTION + " TEXT," + SECURITY_QUESTION_ANSWER + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_USERS;

    public DatabaserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "onCreate: from dbhelper: ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    public void addUser(User user) {
        SQLiteDatabase sqLDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USERNAME, user.getUserName());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(NAME, user.getName());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(SECURITY_QUESTION, user.getSecurityQuestion());
        contentValues.put(SECURITY_QUESTION_ANSWER, user.getSecurityAnswer());

        sqLDb.insert(TABLE_NAME_USERS, null, contentValues);
        sqLDb.close();

        createUserTaskTable(user.getUserName());


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME_USERS, null);

        if (c.moveToFirst()) {
            do {
                String[] arr = c.getColumnNames();
                Log.d(TAG, "[]: ---" + arr);
            } while (c.moveToNext());
        }


    }

    // update user info TODO

    public int removeUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numberOfRowsAffected = db.delete(TABLE_NAME_USERS, USERNAME + " = ?", new String[]{user.getUserName()});
        return numberOfRowsAffected;
    }

    public boolean isAlreadyRegistered(String userName) {
        String[] column = {USER_ID};

        SQLiteDatabase db = this.getWritableDatabase();


        String selection = USERNAME + " = ?";
        String[] args = {userName};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_NAME_USERS, //Table to query
                column,                    //columns to return
                selection,                  //columns for the WHERE clause
                args,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;

    }


    public boolean canLogin(String userName, String password) {
        // array of columns to fetch
        String[] columns = {
                USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USERNAME + " = ?" + " AND " + PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {userName, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        // getU(userName);


        return false;
    }


    public User getUserData(String userName) {
        String gotUserName = null;
        String passWord = null;
        String name = null;
        String email = null;
        String securityQuestion = null;
        String securityAnswer = null;

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + USERNAME + " = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{userName});

        if (cursor.moveToFirst()) {
            do {


                gotUserName = cursor.getString(cursor.getColumnIndex(USERNAME));

                passWord = cursor.getString(cursor.getColumnIndex(PASSWORD));

                name = cursor.getString(cursor.getColumnIndex(NAME));

                email = cursor.getString(cursor.getColumnIndex(EMAIL));

                securityQuestion = cursor.getString(cursor.getColumnIndex(SECURITY_QUESTION));
                securityAnswer = cursor.getString(cursor.getColumnIndex(SECURITY_QUESTION_ANSWER));

                Log.d("TAG", "got user name: " + gotUserName + " email : " + email);

                // addTaskToTable(new Task("Hardcoded task name", "dd"), userName);
                // Log.d("TAG", "addTaskToTable(new Task(\"Hardcoded task name\", \"dd\"), user.getUserName());");
                //getTask(userName);


            } while (cursor.moveToNext());
        }

        return new User(gotUserName, passWord, name, email, securityQuestion, securityAnswer);
    }


    //redundant
    @Deprecated
    protected User getUser(String userName) {
        String[] columns = {
                USER_ID, USERNAME, PASSWORD, NAME, EMAIL, SECURITY_QUESTION
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USERNAME + " = ?";

        // selection arguments
        String[] selectionArgs = {userName};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        // String sel = "(SELECT id, user_name, password, name, email, security_question FROM usersInfo WHERE  *  = ?)";
        //  cursor = (SELECT id, user_name, password, name, email, security_question FROM usersInfo WHERE  *  = ?)


        int cursorCount = cursor.getColumnCount();

        Log.d("TAG", "Counted: " + cursorCount);


        User user = null;

//        String name = cursor.getString(3);
        //    Log.d("TAG",name);
        cursor.close();
        db.close();
        return user;
    }


    private void createUserTaskTable(String userName) {
        String tableName = userName;
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("CREATE TABLE " + tableName + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, taskName, taskDetails" + ")");
        } catch (SQLException e) {
            Log.d("TAG", "Exception found: " + e.toString());
        }

        Log.d("TAG", "Maybe created Table: " + tableName);

        db.close();

    }

    public void addTaskToTable(Task task, String tableName) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("taskName", task.getTaskName());
        contentValues.put("taskDetails", task.getTaskDetails());

        db.insert(tableName, null, contentValues);
        db.close();


    }


    public void getTask(String tableName) {
        int c = 0;

        String taskName;
        String taskDetails;

        SQLiteDatabase db = this.getWritableDatabase();

        // String sql = "SELECT * FROM " + tableName;
        String sql = "SELECT * FROM " + tableName;

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {

            do {


                taskName = cursor.getString(1);
                taskDetails = cursor.getString(2);
                c++;
                Log.d("TAG", "inside getTask(): " + "TaskName: " + taskName + "counter: " + c);

            } while (cursor.moveToNext());


        }


//        if task name == selected task name in list  view


    }


    public Cursor getCursorForTask(String taskTableName) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + taskTableName, null);
        return cursor;
    }


    public int deleteTask(String userTable, String taskName) {

        SQLiteDatabase db = this.getWritableDatabase();

        //   String sql = "SELECT * FROM " + userTable;


        //    Cursor cursor = db.rawQuery(sql, null);

        int deletedInt = db.delete(userTable, "taskName = ? ", new String[]{taskName});
        Log.d(TAG, "deleteTask: " + deletedInt);

        db.close();

        return deletedInt;
    }


    public List<String> getTaskList(String taskTable) {
        List<String> taskList = new ArrayList<>();

        String taskName;
        String taskDetails;

        SQLiteDatabase db = this.getWritableDatabase();

        // String sql = "SELECT * FROM " + tableName;
        String sql = "SELECT * FROM " + taskTable;

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {

            do {

                taskName = cursor.getString(1);
                taskDetails = cursor.getString(2);

                Log.d("TAG", "inside getTask(): " + "TaskName: " + taskName);

                taskList.add(taskName);

            } while (cursor.moveToNext());


        }


        return taskList;

    }

    //TODO
    public void updatePassword(String username, String newPassword) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USERS + USERNAME + " =?", new String[]{username});


    }


}
