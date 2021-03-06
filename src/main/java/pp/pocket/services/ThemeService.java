package pp.pocket.services;

import pp.pocket.dbhelper.DBHelper;
import pp.pocket.entities.Theme;

import java.sql.SQLException;
import java.util.List;

public class ThemeService {
    public static List<Theme> getThemes(String userId) throws SQLException, ClassNotFoundException {
        List<Theme> result = null;
        try {
            DBHelper.connect();
            result = DBHelper.readThemes(userId);
        } finally {
            DBHelper.CloseDB();
        }
        return result;
    }

    public static Theme add(Theme theme) throws SQLException, ClassNotFoundException {
        Theme result = null;
        try {

            DBHelper.connect();
            DBHelper.addTheme(theme);
        } finally {
            DBHelper.CloseDB();
        }
        return result;
    }


}
