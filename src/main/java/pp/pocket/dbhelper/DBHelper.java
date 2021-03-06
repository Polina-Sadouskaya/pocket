package pp.pocket.dbhelper;

import org.apache.log4j.Logger;
import pp.pocket.entities.Theme;

import java.sql.*;
import java.util.HashMap;

public class DBHelper {

    public static Connection conn = null;
    public static Statement statmt = null;
    public static ResultSet resSet = null;

    public static Logger log = Logger.getRootLogger();
    public static void connect() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:D:\\work\\github\\pocket\\pocket.s3db");
        conn.setAutoCommit(true);
        statmt = conn.createStatement();
        log.info("Connected to DB");
    }
    public static void disconnect() throws SQLException
    {
        conn.close();
        statmt.close();
        if (resSet!=null){
            resSet.close();
        }
        log.info("Connections closed");
    }

    //----THEMES
    public static HashMap<String, Theme> readThemes(int ui) throws SQLException {
        log.info("execute query");
        resSet = statmt.executeQuery("SELECT * FROM themes;");
        HashMap<String, Theme> result = new HashMap<>();
        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            int  type = resSet.getInt("type");
            int  userId = resSet.getInt("userId");
            result.put(String.valueOf(id), new Theme(id, name, type, userId));
        }
        log.info("Table themes: "+result.size()+" rows.");
        return result;
    }

    public static Theme addTheme(Theme theme) throws SQLException {
        statmt.execute("INSERT INTO themes ('name', 'type', 'userId') VALUES ('"+theme.getName()+"', '"+theme.getType()+"', '"+theme.getUserId()+"'); ");
        log.info("Added theme record: "+ theme.toString());
        return theme;
    }
}

