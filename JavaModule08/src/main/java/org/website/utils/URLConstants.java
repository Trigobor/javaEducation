package org.website.utils;

import java.util.HashMap;
import java.util.Map;

public class URLConstants {
    public static final String INDEX_URL = "/index";
    public static final String LOGIN_URL = "/login";
    public static final String USER_URL = "/user";
    public static final String ADMIN_URL = "/admin";
    public static final String ADMIN_BLOCK_URL = "/adminBlock";

    public static final String[] ADMIN_URLS = {"/JavaModule08_war_exploded/admin"};
    public static final Map<String, String> redirectMap;

    static {
        redirectMap = new HashMap<>();
        redirectMap.put("/JavaModule08_war_exploded/pages/index.jsp", "/index");
        redirectMap.put("/JavaModule08_war_exploded/pages/adminBlock.jsp", "/adminBlock");
        redirectMap.put("/JavaModule08_war_exploded/pages/admin.jsp", "/admin");
        redirectMap.put("/JavaModule08_war_exploded/pages/login.jsp", "/login");
        redirectMap.put("/JavaModule08_war_exploded/pages/user.jsp", "/user");
    }
}
