package yoktavian.dev.prelo.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yoktavian on 12/15/17.
 */

public class DataDetail {
    @SerializedName("_data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public class Data {

        @SerializedName("display_picts")
        @Expose
        private List<String> display;

        @SerializedName("price")
        @Expose
        private String price;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("seller")
        @Expose
        private Seller seller;


        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public List<String> getDisplay() {
            return display;
        }

        public Seller getSeller() {
            return seller;
        }

        public class Seller {
            @SerializedName("email")
            @Expose
            private String email;

            @SerializedName("pict")
            @Expose
            private String pict;

            @SerializedName("fullname")
            @Expose
            private String fullname;

            public String getEmail() {
                return email;
            }

            public String getPict() {
                return pict;
            }

            public String getFullname() {
                return fullname;
            }
        }
    }
}
