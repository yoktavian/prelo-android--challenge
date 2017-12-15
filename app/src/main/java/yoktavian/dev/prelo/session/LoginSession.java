package yoktavian.dev.prelo.session;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yoktavian on 12/11/17.
 */

public class LoginSession {

    private static final String Session_fullname    = "Ses_fullname";
    private static final String Session_username    = "Ses_username";
    private static final String Session_email       = "Ses_email";
    private static final String Session_alamat      = "Ses_alamat";
    private static final String Session_url_avatar  = "Ses_url_avatar";
    private static final String Session_token       = "Ses_token";

    private static final String Session_postal      = "Ses_postal";
    private static final String Session_subdistrict = "Ses_subdistrict";
    private static final String Session_region      = "Ses_region";
    private static final String Session_province    = "Ses_province";
    private static final String Session_phone       = "Ses_phone";

    private Context mcontext;

    public LoginSession(Context mcontext){
        this.mcontext = mcontext;
    }

    public void setLoginSession(String Ses_fullname, String Ses_username, String Ses_email, String Ses_token, String Ses_alamat, String url_avatar,
                                String Ses_postal, String Ses_subdistrict, String Ses_region, String Ses_province, String Ses_phone) {
        SharedPreferences mPrefs = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(Session_fullname, Ses_fullname);
        prefsEditor.putString(Session_username, Ses_username);
        prefsEditor.putString(Session_email, Ses_email);
        prefsEditor.putString(Session_alamat, Ses_alamat);
        prefsEditor.putString(Session_url_avatar, url_avatar);
        prefsEditor.putString(Session_token, Ses_token);

        prefsEditor.putString(Session_postal, Ses_postal);
        prefsEditor.putString(Session_subdistrict, Ses_subdistrict);
        prefsEditor.putString(Session_region, Ses_region);
        prefsEditor.putString(Session_province, Ses_province);
        prefsEditor.putString(Session_phone, Ses_phone);
        prefsEditor.commit();
    }

    public String getSes_fullname(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_fullname,"");
        return result;
    }
    public String getSes_username(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_fullname,"");
        return result;
    }
    public String getSes_email(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_email,"");
        return result;
    }
    public String getSes_alamat(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_alamat,"");
        return result;
    }
    public String getSession_url_avatar(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_url_avatar,"");
        return result;
    }
    public String getSes_token(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_token,"");
        return result;
    }
    public String getSes_postal(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_postal,"");
        return result;
    }
    public String getSes_subdistrictId(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_subdistrict,"");
        return result;
    }
    public String getSes_regionId(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_region,"");
        return result;
    }
    public String getSes_provinceId(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_province,"");
        return result;
    }
    public String getSes_phone(){
        SharedPreferences myPrefs   = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        String result               = myPrefs.getString(Session_phone,"");
        return result;
    }

    public void doLogout(){
        SharedPreferences myPrefs       = mcontext.getSharedPreferences("default", mcontext.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.remove(Session_fullname);
        editor.remove(Session_username);
        editor.remove(Session_email);
        editor.remove(Session_alamat);
        editor.remove(Session_url_avatar);
        editor.remove(Session_token);
        editor.commit();
    }
}
