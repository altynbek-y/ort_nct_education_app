package  nami.project.indie.ort_nct.network;

/**
 * Retrofit Client class
 * Binds Retrofit object to the base url with which it will communicate
 * via DAO (Data Access Object), using API calls to the server
 * <p>
 * created by @iman_august, 30.01.20
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NctRetrofitClientInstance {
    private static final String BASE_URL = "http://217.29.18.149:9189/";
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
                     okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
                     retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
