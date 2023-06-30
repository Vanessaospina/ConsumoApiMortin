package com.manuel.consumoapimortin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListaMortinAdapter extends RecyclerView.Adapter<ListaMortinAdapter.ViewHolder> {

    private RecyclerView recyclerView;
    private ListaMortinAdapter listaMortinAdapter;
    private ArrayList<Mortin> dataset;
    private Context context;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            fotoImageView = itemView.findViewById(R.id.fotoImageView);
        }
    }


    public ListaMortinAdapter(Context context) {
        this.context = context;
        dataset=new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return  dataset.size();
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Mortin morty=dataset.get(position);
        holder.name.setText(morty.getName());


        String url="https://rickandmortyapi.com/api/character/451.jpeg";

        Glide.with(context)
                .load("https://rickandmortyapi.com/api/character/avatar/"+morty.getId()+".jpeg")
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(holder.fotoImageView);
    }

    public void add(ArrayList<Mortin> listaMortin) {
        dataset.addAll(listaMortin);
        notifyDataSetChanged();
    }

}
