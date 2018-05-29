package com.acampdev.borisalexandrcamposrios.libapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acampdev.borisalexandrcamposrios.libapp.Activities.AnimeActivity;
import com.acampdev.borisalexandrcamposrios.libapp.Models.Anime;
import com.acampdev.borisalexandrcamposrios.libapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {

    RequestOptions requestOptions;
    private Context context;
    private List<Anime> animes;

    public AnimeAdapter(Context context, List<Anime> animes){
        this.context=context;
        this.animes=animes;
        requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        view= layoutInflater.inflate(R.layout.anime_item,parent,false);
        final ViewHolder viewHolder= new ViewHolder(view);
        viewHolder.viewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, AnimeActivity.class);
                intent.putExtra("anime_name",animes.get(viewHolder.getAdapterPosition()).getName());
                intent.putExtra("anime_description",animes.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("anime_studio",animes.get(viewHolder.getAdapterPosition()).getStudio());
                intent.putExtra("anime_category",animes.get(viewHolder.getAdapterPosition()).getCategorie());
                intent.putExtra("anime_nb_episode",animes.get(viewHolder.getAdapterPosition()).getNb_episode());
                intent.putExtra("anime_rating",animes.get(viewHolder.getAdapterPosition()).getRating());
                intent.putExtra("anime_img",animes.get(viewHolder.getAdapterPosition()).getImage_url());

                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvname.setText(animes.get(position).getName());
        holder.tv_rate.setText(animes.get(position).getRating());
        holder.tvstudio.setText(animes.get(position).getStudio());
        holder.tvcat.setText(animes.get(position).getCategorie());

        // cargando imagenes de internet  Glide
        Glide.with(context).load(animes.get(position).getImage_url()).apply(requestOptions).into(holder.animeThumbnail);
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvname,tv_rate,tvstudio,tvcat;
        ImageView animeThumbnail;
        LinearLayout viewContainer;

        public ViewHolder(View itemView){
            super(itemView);
            viewContainer=itemView.findViewById(R.id.container);
            tvname= itemView.findViewById(R.id.name);
            tvstudio=itemView.findViewById(R.id.studio);
            tv_rate=itemView.findViewById(R.id.rating);
            tvcat=itemView.findViewById(R.id.categorie);
            animeThumbnail=itemView.findViewById(R.id.thumbnail);
        }
    }
}
