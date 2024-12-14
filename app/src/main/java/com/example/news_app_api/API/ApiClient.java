package com.example.news_app_api.API;

import okhttp3.OkHttpClient;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ApiClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit;

    // Méthode pour obtenir le client Retrofit
    public static Retrofit getApiClient() {
        if (retrofit == null) {

            // Créer un interceptor pour ajouter l'en-tête User-Agent
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("User-Agent", "AndroidApp") // Ajouter l'en-tête User-Agent
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            // Construire le Retrofit avec le client OkHttp configuré
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)  // Ajouter le client avec l'intercepteur
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
