package com.example.a100;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private ImageView photo;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        photo = (ImageView) findViewById(R.id.imagePet);
        text = (TextView) findViewById(R.id.PetName);

        mProgressBar.setVisibility(View.INVISIBLE);


        PID pid = PID.retrofit.create(PID.class);
        final Call<Jivotnoe> call = pid.getData();


        call.enqueue(new Callback<Jivotnoe>() {
                         @Override
                         public void onResponse(Call<Jivotnoe> call, Response<Jivotnoe> response) {
                             if (response.isSuccessful()) {
                                 Jivotnoe pet = response.body();
                                 text.setText(pet.getName());

                                 Picasso.with(MainActivity.this).load("https://fikiwiki.com/uploads/posts/2022-02/1644990920_11-fikiwiki-com-p-prikolnie-kartinki-pro-zhivotnikh-12.jpg").into(photo);

                                 mProgressBar.setVisibility(View.INVISIBLE);
                             } else {
                                 // Обрабатываем ошибку
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();
                                     mProgressBar.setVisibility(View.INVISIBLE);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<Jivotnoe> call, Throwable throwable) {
                             Toast.makeText(MainActivity.this, "Что-то пошло не так " + throwable.getMessage(),
                                     Toast.LENGTH_SHORT).show();
                             Log.d("ERRORS_GIT", "" + throwable.getMessage());
                             mProgressBar.setVisibility(View.INVISIBLE);
                         }
                     }
        );
    }
}