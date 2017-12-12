package yoktavian.dev.prelo.networking;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yoktavian.dev.prelo.ActvitiyLogin;
import yoktavian.dev.prelo.HomeActivity;
import yoktavian.dev.prelo.models.DataLoveList;
import yoktavian.dev.prelo.models.DataUser;

/**
 * Created by yoktavian on 12/11/17.
 */

public class ApiRequest {

    private static Retrofit retrofit = null;
    public static Context mcontext;
    private ApiClient apiClient;

    public ApiRequest(Context mcontext){
        this.mcontext = mcontext;
    }

    public static Retrofit getClient(String baseUrl) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void doLogin(String username, String password){
        apiClient = ApiUtils.getAPIClient();
        Call<DataUser> call = apiClient.doLogin(username, password);
        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                if (response.isSuccessful()) {
                    ((ActvitiyLogin)mcontext)._onLoginSuccess(response.body().getData());
                } else {
                    ((ActvitiyLogin)mcontext)._onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                ((ActvitiyLogin)mcontext)._onSomeThingWrong();
            }
        });
    }


    public void getLoveList(String token){
        apiClient = ApiUtils.getAPIClient();

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Token "+token);

        Call<DataLoveList> call = apiClient.getLoveData(map);
        call.enqueue(new Callback<DataLoveList>() {
            @Override
            public void onResponse(Call<DataLoveList> call, Response<DataLoveList> response) {
                if (response.isSuccessful()) {
                    ((HomeActivity)mcontext)._onRequestSuccess(response.body().getList());
                } else {
                    ((HomeActivity)mcontext)._onRequestFailed();
                }
            }

            @Override
            public void onFailure(Call<DataLoveList> call, Throwable t) {
                ((HomeActivity)mcontext)._onError();
            }
        });
    }
}
