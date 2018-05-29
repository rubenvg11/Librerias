package com.acampdev.borisalexandrcamposrios.libapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.acampdev.borisalexandrcamposrios.libapp.Adapters.AnimeAdapter;
import com.acampdev.borisalexandrcamposrios.libapp.Models.Anime;
import com.acampdev.borisalexandrcamposrios.libapp.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final  String JSON_URL="https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest jsonArrayRequest ;
    private RequestQueue requestQueue;
    private List<Anime> animeList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animeList = new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerID);
        jsonrequest();

    }

    private  void  jsonrequest(){
        jsonArrayRequest= new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject= null;
                for(int i=0; i<response.length(); i++){
                    try{
                        jsonObject=response.getJSONObject(i);
                        Anime anime= new Anime();
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));

                        animeList.add(anime);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                setuprecyclerView(animeList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void setuprecyclerView(List<Anime> animeList){
        AnimeAdapter animeAdapter = new AnimeAdapter(this,animeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animeAdapter);
    }
}
