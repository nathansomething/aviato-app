package com.aviato.userlogin;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.scope.ScopeBuilder;
import com.restfb.scope.UserDataPermissions;
import com.restfb.types.User;
import com.restfb.types.ads.UserPermission;

public class FacebookUtils {
    
    //keyfields we don't care who sees
    public final static String fbId = "1305051219545441";
    private final static String fbSec = "caecfb1894f0f6c80c5220cb86a55a7c";
    
    public static final String url = "jdbc:mysql://etwixt.com:3306/etwixtdb";
    public static final String user = "erlich";
    public static final String pass = "bachman";    
   
    /**
     * Returns a new Facebook client with the token set based on code
     * @param code
     * @param postLoginUrl
     * @return
     */
    public static FacebookClient getClient(String code, String postLoginUrl) {
        AccessToken token = new DefaultFacebookClient(Version.LATEST).obtainUserAccessToken(
                fbId, fbSec, postLoginUrl, code);
        
        
        return new DefaultFacebookClient(token.getAccessToken(), Version.LATEST);
    }
    
    /***
     * gets the login url while setting approrpriate scoped urls
     * 
     * @param postLoginUrl
     * @return
     */
    public static String getScopedLoginUrl(String postLoginUrl) {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(UserDataPermissions.USER_ABOUT_ME);
        scopeBuilder.addPermission(UserDataPermissions.USER_BIRTHDAY);
        
        FacebookClient client = new DefaultFacebookClient(Version.LATEST);
        
        String loginUrl = client.getLoginDialogUrl(FacebookUtils.fbId, postLoginUrl, scopeBuilder,
                Parameter.with("response_type", "code"));
        
        return loginUrl;
    }
    
    /***
     * Gets relevant details in a FacebookJson
     * @return
     */
    public static FacebookPerson getRelevantDetails(FacebookClient client) {
        JsonObject picture = 
                client.fetchObject("me/picture", 
                    JsonObject.class, Parameter.with("redirect","false"), Parameter.with("type", "large"));
        
        
        JsonObject user = client.fetchObject("me", JsonObject.class, Parameter.with("fields", "first_name,last_name,gender,birthday"));
        user.put("pic_url", picture.getJsonObject("data").get("url"));
        FacebookPerson result = new FacebookPerson(user.toString());
        
        return result;
    }
}
