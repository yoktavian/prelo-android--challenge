package yoktavian.dev.prelo.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yoktavian on 12/11/17.
 */

public class DataModel {
//    @SerializedName("profile")
//    @Expose
//    private Profil profile;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fullname")
    @Expose
    private String fullname;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

//    public Profil getProfile() {
//        return profile;
//    }
//
//    public class Profil {
//        @SerializedName("pict")
//        @Expose
//        private String pict;
//
//        public String getPict() {
//            return pict;
//        }
//    }
}
