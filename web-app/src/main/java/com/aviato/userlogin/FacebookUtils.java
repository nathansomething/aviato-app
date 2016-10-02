package com.aviato.userlogin;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.scope.ScopeBuilder;
import com.restfb.scope.UserDataPermissions;

public class FacebookUtils {
    
    //keyfields we don't care who sees
    public final static String fbId = "1305051219545441";
    private final static String fbSec = "caecfb1894f0f6c80c5220cb86a55a7c";
    
    private final static UserDataPermissions[] listOfPermissions = new UserDataPermissions[] 
            {UserDataPermissions.USER_BIRTHDAY};
    
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
        for (UserDataPermissions perm: listOfPermissions) {
            scopeBuilder.addPermission(perm);
        }
        
        FacebookClient client = new DefaultFacebookClient(Version.LATEST);
        
        String loginUrl = client.getLoginDialogUrl(FacebookUtils.fbId, postLoginUrl, scopeBuilder,
                Parameter.with("response_type", "code"));
        
        return loginUrl;
    }
}
