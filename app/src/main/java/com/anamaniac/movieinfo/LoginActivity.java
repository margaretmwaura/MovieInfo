package com.anamaniac.movieinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements CallBackgroundAuthorization.View{
    public Retrofit retrofit;
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String BASE_URL = "https://api.themoviedb.org/3/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        ThyInterface toUse = retrofit.create(ThyInterface.class);
        Call<UserDataModel> call = toUse.getAuthenticationToken(BuildConfig.ApiKey);
        CallBackgroundAuthorization callBackgroundAuthorization = new CallBackgroundAuthorization(this);
        callBackgroundAuthorization.GetUser(call,this);

//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/octet-stream");
//        RequestBody body = RequestBody.create(mediaType, "{}");
//        Request request = new Request.Builder()
//                .url("https://api.themoviedb.org/3/authentication/token/new?api_key=%3C%3Capi_key%3E%3E")
//                .get()
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    @Override
    public void display(Boolean login, String Expiry, final String RequestToken) {
        if (login == true){
            TextView Display1 = findViewById(R.id.DisplayInfo);
            TextView Display2 = findViewById(R.id.DisplayInfo2);
            Display1.setText(Expiry);
            Display2.setText(RequestToken);
            Button button = findViewById(R.id.Authenticate);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Url = "https://www.themoviedb.org/authenticate/" + RequestToken;
                    Uri uri;
                    Intent intent = new Intent(LoginActivity.this,TvMovies.class);
                    startActivity(intent);

                }
            });
        }
        Log.d("Boolean value","This is the Boolean Value"+ login);
        Log.d("Strings",Expiry + RequestToken);
    }

    @Override
    public void RecyclerViewDisplay(List<TvDataModel> Results, Integer TotalResults) {

    }


}
