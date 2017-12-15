package yoktavian.dev.prelo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yoktavian on 12/15/17.
 */

public class DataCheckout {
    @SerializedName("_data")
    @Expose
    private Checkout data;

    public Checkout getData() {
        return data;
    }

    public class Checkout {
        @SerializedName("transaction_id")
        @Expose
        private String transaction_id;

        @SerializedName("total_price")
        @Expose
        private String total_price;

        @SerializedName("expire_time")
        @Expose
        private String expire_time;

        public String getExpire_time() {
            return expire_time;
        }

        public String getTotal_price() {
            return total_price;
        }

        public String getTransaction_id() {
            return transaction_id;
        }
    }
}
