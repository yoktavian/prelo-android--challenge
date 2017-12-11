package yoktavian.dev.prelo.networking;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
}
