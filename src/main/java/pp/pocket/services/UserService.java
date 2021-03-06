package pp.pocket.services;

import org.apache.log4j.Logger;
import pp.pocket.dbhelper.DBHelper;
import pp.pocket.entities.User;


public class UserService {
    public static Logger log = Logger.getRootLogger();

    /** Adds user to DB and returns id of created user record **/
    public static int add(User user) throws Exception {
        int resultId;
        try {
            DBHelper.connect();
            resultId = DBHelper.addUser(user);
        } finally {
            DBHelper.CloseDB();
        }
        return resultId;
    }

    public static int checkValidCreds(String creds) throws Exception {
        String email = creds.substring(0, creds.indexOf(":"));
        String password = creds.substring(creds.indexOf(":")+1);
        log.debug("creds: "+ email+", "+password+";");
        int resultId;
        try {
            DBHelper.connect();
            resultId = DBHelper.loggedUser(new User(email, password));
        } finally {
            DBHelper.CloseDB();
        }
        return resultId;
    }
}
