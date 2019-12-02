package com.valdo.goquizz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.valdo.goquizz.R;
import com.valdo.goquizz.activities.RankActivity;
import com.valdo.goquizz.activities.RankHomeActivity;
import com.valdo.goquizz.models.SeeRankModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SeeRankAdapter extends RecyclerView.Adapter<SeeRankAdapter.MyViewHolder> {
   List<SeeRankModel>seeRankModels;

   Context context;

   public static String pinRecycler ;



    public SeeRankAdapter(List<SeeRankModel> seeRankModels) {
        this.seeRankModels = seeRankModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View seeRankView = layoutInflater.inflate(R.layout.item_see_rank_activity,parent,false);
        View seeRankView = layoutInflater.inflate(R.layout.activity_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(seeRankView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SeeRankModel seeRanked = seeRankModels.get(position);
        holder.pinButton.setText(seeRanked.getPinUserActivity());


    }

    @Override
    public int getItemCount() {
        return (seeRankModels != null) ? seeRankModels.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public Button pinButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            pinButton = itemView.findViewById(R.id.buttonItemSeeRankActivityzzzzz);

            pinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pinRecycler = pinButton.getText().toString();
                    System.out.println("ini adalah pin di recycler " + pinRecycler);
                    Intent inten = new Intent(context, RankHomeActivity.class);
                    context.startActivities(new Intent[]{inten});
                }
            });
        }
    }

    public SeeRankAdapter() {
    }
}
