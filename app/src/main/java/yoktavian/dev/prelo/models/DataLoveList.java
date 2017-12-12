package yoktavian.dev.prelo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yoktavian on 12/13/17.
 */

public class DataLoveList {
    @SerializedName("_data")
    @Expose
    private ArrayList<LoveListModel> list;

    public ArrayList<LoveListModel> getList(){
        return list;
    }

    public void setList(ArrayList<LoveListModel> list){
        this.list = list;
    }
}
