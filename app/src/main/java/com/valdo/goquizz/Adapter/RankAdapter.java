package com.valdo.goquizz.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.valdo.goquizz.R;
import com.valdo.goquizz.models.RankModel;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.MyViewHolder> {

    List<RankModel>rankModels;

    public RankAdapter(List<RankModel> rankModels) {
        this.rankModels = rankModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //mengambil objek context dari parrent
        Context context = parent.getContext();
        //objek context digunakan untuk membuat object layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //object layoutInflater digunakan untuk membuat object view yang merupakan hasil inflate layout
        View superHeroView = layoutInflater.inflate(R.layout.item_rank,parent,false);
        //digunakan untuk membuat object viewHolder
        MyViewHolder viewHolder = new MyViewHolder(superHeroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //ambil satu item hero
        RankModel rank= rankModels.get(position);
        //set text heroName berdasarkan data dari model hero
        holder.listRank.setText(rank.getScoreuser());
    }


    @Override
    public int getItemCount() {
        return (rankModels != null) ? rankModels.size() : 0 ;
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView listRank;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            listRank = itemView.findViewById(R.id.listRank);
        }
    }
}
