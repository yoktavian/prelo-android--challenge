package yoktavian.dev.prelo.networking;

/**
 * Created by yoktavian on 12/11/17.
 */

public class ApiUtils {
    private ApiUtils() {}
    public static final String BASE_URL = "https://dev.prelo.id/";
    public static ApiClient getAPIClient() {
        return ApiRequest.getClient(BASE_URL).create(ApiClient.class);
    }
}
