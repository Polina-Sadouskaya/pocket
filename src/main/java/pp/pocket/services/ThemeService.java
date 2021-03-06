package pp.pocket.services;

import pp.pocket.dbhelper.DBHelper;
import pp.pocket.entities.Theme;

import java.sql.SQLException;
import java.util.HashMap;

public class ThemeService {
    public static HashMap<String, Theme> getThemes(int userId) throws SQLException, ClassNotFoundException {
        HashMap<String, Theme> result = null;
        try {
            DBHelper.connect();
            result = DBHelper.readThemes(userId);
        } finally {
            DBHelper.disconnect();
        }
        return result;
    }

    public static Theme add(Theme theme) throws SQLException, ClassNotFoundException {
        Theme result = null;
        try {

            DBHelper.connect();
            DBHelper.addTheme(theme);
        } finally {
            DBHelper.disconnect();
        }
        return result;
    }


}
