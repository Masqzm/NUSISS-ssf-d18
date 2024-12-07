package ssf.day18.config;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

public class Constants {
    public static final String REDIS_TEMPLATE_01 = "redis-0";
    
    public static final String SESS_ATTR_NAMESLIST = "namesList";
    public static final String SESS_ATTR_DOBLIST = "dobList";

    public static final String CARPARK_URL = "https://data.gov.sg/api/action/datastore_search?resource_id=d_9f6056bdb6b1dfba57f063593e4f34ae";

    public static List<String> getSessionNamesList(HttpSession sess) {
        List<String> namesList = new ArrayList<>();

        if(sess.getAttribute(SESS_ATTR_NAMESLIST) != null)
            namesList = (List<String>) sess.getAttribute(SESS_ATTR_NAMESLIST);

        return namesList;
    }

    public static List<String> getSessionDOBList(HttpSession sess) {
        List<String> dobList = new ArrayList<>();

        if(sess.getAttribute(SESS_ATTR_NAMESLIST) != null)
            dobList = (List<String>) sess.getAttribute(SESS_ATTR_DOBLIST);

        return dobList;
    }
}
