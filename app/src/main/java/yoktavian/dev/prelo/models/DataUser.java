package yoktavian.dev.prelo.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoktavian on 12/11/17.
 */

public class DataUser {
    @SerializedName("_data")
    @Expose
    private JsonObject json;

    public JsonObject getData(){
        return json;
    }

    public void setData(JsonObject json){
        this.json = json;
    }
}
