package pp.pocket.dbhelper;

import org.apache.log4j.Logger;
import pp.pocket.entities.Theme;
import pp.pocket.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static Connection conn = null;
    public static Statement statmt = null;
    public static ResultSet resSet = null;

    public static Logger log = Logger.getRootLogger();

    private static String themesTableCreationQuery = "CREATE TABLE if not exists 'themes' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text UNIQUE NOT NULL, 'type' text NOT NULL, 'userId' INTEGER NOT NULL);";
    private static String usersTableCreationQuery = "CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'email' text UNIQUE NOT NULL, 'password' text NOT NULL, 'name' text);";

    public static void connect() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:pocket.s3db");
        conn.setAutoCommit(false);
        statmt = conn.createStatement();
        log.info("Connected to DB");
    }

    public static void CloseDB() throws SQLException
    {
        conn.close();
        statmt.close();
        if (resSet!=null){
            resSet.close();
        }
        log.info("Connections closed");
    }

    public static int loggedUser(User user) throws Exception {
        resSet = statmt.executeQuery("SELECT id, password FROM users WHERE email='"+user.getEmail()+"';");
        if (user.getPassword().equals(resSet.getString("password"))){
            return resSet.getInt("id");
        } else {
            throw new Exception("Authorization failed. Check credentials");
        }
    }

    public static void createTable(String query) throws SQLException
    {
        statmt = conn.createStatement();
        statmt.execute(query);
        log.info("Table created of exist");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }

    //----THEMES
    public static List<Theme> readThemes(String ui) throws SQLException {
        createTable(themesTableCreationQuery);
        resSet = statmt.executeQuery("SELECT * FROM themes where userId='"+ui+"' or userId='default';");
        ArrayList<Theme> result = new ArrayList<>();

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  type = resSet.getString("type");
            int  userId = resSet.getInt("userId");
            result.add(new Theme(id, name, type, userId));
        }
        log.info("Table themes: "+result.size()+" rows.");
        return result;
    }

    public static Theme addTheme(Theme theme) throws SQLException {
        createTable(themesTableCreationQuery);
        statmt.execute("INSERT INTO themes ('name', 'type', 'userId') VALUES ('"+theme.getName()+"', '"+theme.getType()+"', '"+theme.getUserId()+"'); ");
        conn.commit();
        log.info("Added theme record: "+ theme.toString());
        return theme;
    }

    //----USERS----
    public static int addUser(User user) throws Exception {
        createTable(usersTableCreationQuery);
        if (isUnique(user.getEmail())){
            statmt.execute("INSERT INTO users ('email', 'password', 'name') VALUES ('"+user.getEmail()+"', '"+user.getPassword()+"', '"+user.getName()+"'); ");
            conn.commit();
            resSet = statmt.executeQuery("SELECT * FROM users WHERE email='"+user.getEmail()+"';");
            log.info("Added user record, id: "+ resSet.getInt("id"));
            return resSet.getInt("id");
        } else {
            throw new Exception("Email is used.");
        }
    }

    public static boolean isUnique(String email) throws SQLException {
        resSet = statmt.executeQuery("SELECT COUNT(*) FROM users WHERE email='"+email+"';");
        log.debug("Email: "+email+", is found in DB: "+ (resSet.getInt(1)==0));
        return (resSet.getInt(1)==0);
    }


}

