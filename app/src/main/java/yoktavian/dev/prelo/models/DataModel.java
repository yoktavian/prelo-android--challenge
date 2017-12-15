package yoktavian.dev.prelo.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yoktavian on 12/11/17.
 */

public class DataModel {
    @SerializedName("profile")
    @Expose
    private Profil profile;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("default_address")
    @Expose
    private Address address;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getToken() {
        return token;
    }

    public Profil getProfile() {
        return profile;
    }

    public Address getAddress() {
        return address;
    }

    public class Profil {
        @SerializedName("pict")
        @Expose
        private String pict;

        @SerializedName("phone")
        @Expose
        private String phone;

        public String getPict() {
            return pict;
        }

        public String getPhone() {
            return phone;
        }
    }

    public class Address {
        @SerializedName("subdistrict_name")
        @Expose
        private String subdistrict_name;

        @SerializedName("region_name")
        @Expose
        private String region_name;

        @SerializedName("province_name")
        @Expose
        private String province_name;

        @SerializedName("postal")
        @Expose
        private String postal;

        @SerializedName("province_id")
        @Expose
        private String province_id;

        @SerializedName("region_id")
        @Expose
        private String region_id;

        @SerializedName("subdistrict_id")
        @Expose
        private String subdistrict_id;

        public String getSubdistrict_name() {
            return subdistrict_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public String getRegion_name() {
            return region_name;
        }

        public String getPostal() {
            return postal;
        }

        public String getProvince_id() {
            return province_id;
        }

        public String getRegion_id() {
            return region_id;
        }

        public String getSubdistrict_id() {
            return subdistrict_id;
        }
    }
}
