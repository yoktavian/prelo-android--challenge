package yoktavian.dev.prelo.networking;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import yoktavian.dev.prelo.models.DataCheckout;
import yoktavian.dev.prelo.models.DataDetail;
import yoktavian.dev.prelo.models.DataLoveList;
import yoktavian.dev.prelo.models.DataModel;
import yoktavian.dev.prelo.models.DataUser;

/**
 * Created by yoktavian on 12/11/17.
 */

public interface ApiClient {
    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<DataUser> doLogin(@Field("username_or_email") String username,
                           @Field("password") String password);


    @GET("/api/me/lovelist")
    Call<DataLoveList> getLoveData(@HeaderMap Map<String, String> header);

    @GET("api/product/{id}")
    Call<DataDetail> getDetail(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/cart/checkout")
    Call<DataCheckout> doCheckOut(@HeaderMap Map<String, String> header, @Field("cart_products") JSONArray product, @Field("shipping_address") JsonObject address);
}
