package com.manuel.consumoapimortin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.manuel.consumoapimortin.api.MortinapiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private  ListaMortinAdapter listaMortinAdapter;
    private ImageView imageView;
    private static final String TAG="MORTINDEX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.card_recycler_view);
        listaMortinAdapter=new ListaMortinAdapter(this);
        recyclerView.setAdapter(listaMortinAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imageView = findViewById(R.id.imagenGlide);
        setImageView();
        obtenerDatos();


    }
    private void obtenerDatos2() {
        MortinapiService service = retrofit.create(MortinapiService.class);
        Call<MortinRespuesta> mortinRespuestaCall = service.obtenerListaMortin();

        mortinRespuestaCall.enqueue(new Callback<MortinRespuesta>() {
            @Override
            public void onResponse(Call<MortinRespuesta> call, Response<MortinRespuesta> response) {

                if (response.isSuccessful()) {
                    MortinRespuesta mortinRespuesta = response.body();
                    List<Mortin> listaMortin = mortinRespuesta.getResults();
                    for (int i = 0; i < listaMortin.size(); i++) {
                        Mortin p = listaMortin.get(i);
                        Log.e(TAG, " mortin: " + p.getName());
                    }
                    listaMortinAdapter.add((ArrayList<Mortin>) listaMortin);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<MortinRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });
    }

    private void setImageView() {
        
        String url = "https://rickandmortyapi.com/api/character/avatar/698.jpeg";
        Glide.with(this)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(imageView);

    }

    private void obtenerDatos() {
        MortinapiService service=retrofit.create(MortinapiService.class);
        Call<MortinRespuesta> mortinRespuestaCall=service.obtenerListaMortin();
        mortinRespuestaCall.enqueue(new Callback<MortinRespuesta>() {
            @Override
            public void onResponse(Call<MortinRespuesta> call, Response<MortinRespuesta> response) {
                if (response.isSuccessful()) {
                    MortinRespuesta mortinRespuesta = response.body();
                    List<Mortin> listamortin = mortinRespuesta.getResults();
                    for (int i = 0; i < listamortin.size(); i++) {
                        Mortin p = listamortin.get(i);
                        Log.e(TAG, "character: " + p.getName());
                    }

                    listaMortinAdapter.add((ArrayList<Mortin>) listamortin);
                }
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<MortinRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

    }
}