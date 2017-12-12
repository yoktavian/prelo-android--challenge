package yoktavian.dev.prelo.models;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yoktavian on 12/13/17.
 */

public class LoveListModel {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("display_picts")
    @Expose
    private JsonArray display_picts;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public JsonArray getDisplay_picts() {
        return display_picts;
    }
}
