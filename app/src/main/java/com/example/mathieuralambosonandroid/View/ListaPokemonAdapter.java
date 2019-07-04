package com.example.mathieuralambosonandroid.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mathieuralambosonandroid.Model.Detail;
import com.example.mathieuralambosonandroid.Model.Pokemon;
import com.example.mathieuralambosonandroid.R;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private ArrayList<Detail> pokemon;
    private Context context;

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
        pokemon = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pokemon p = dataset.get(position);//ATTENTION
        //final Detail d = pokemon.get(position);
        holder.nombreTextView.setText(p.getName());
        //holder.height.setText(p.getHeight());
        //holder.weight.setText(p.getWeight());
        //holder.type.setText(p.getType());

        Glide.with(context)
                .load("https://pokeres.bastionbot.org/images/pokemon/" + p.getNumber()+ ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        holder.fotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + p.getName());
                Toast.makeText(context,"Clicked on Pokemon : "+p.getName(),Toast.LENGTH_SHORT).show();

                //Declaration SecondActivity
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("image_url", p.getUrl());
                intent.putExtra("image_name",p.getName());

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView height;
        private TextView weight;
        private TextView type;
        private TextView weakness;

        public ViewHolder( View itemView) {
            super(itemView);

            fotoImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            height = itemView.findViewById(R.id.height);
            weight = itemView.findViewById(R.id.weight);
            type = itemView.findViewById(R.id.type);
            weakness = itemView.findViewById(R.id.weakness);

        }
    }
}