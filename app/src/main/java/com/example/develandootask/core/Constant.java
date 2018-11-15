package com.example.develandootask.core;

public class Constant {

    /** API_KEY
     * Api key is separate in case if it can be different for multi servers
     * */
    private static final String Key_Api = "api/";

    /** Keys inside Urls **/
    public static final String KEY_Page = "page";
    public static final String KEY_Results = "results";
    public static final String KEY_Seed = "seed";

    /** API URLs */
    public static final String URL_API_HOST = "https://randomuser.me/";
    public static final String URL_DataByPageResultSeed = Key_Api;

    /** Request Headers */
    public static final String KEY_Accept = "Accept: application/json";
    public static final String KEY_Post_Content_Type = "Content-Type: application/json";
    public static final String KEY_Cache_Control = "Cache-Control: no-cache";

    /** Realm DB RealmName */
    static final String REALM_NAME = "RealmDevelandoo";

    /** App Log Prefix */
    public static final String APP_LOG = "Log_Develandoo";

}
