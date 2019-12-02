package com.valdo.goquizz.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.valdo.goquizz.R;
import com.valdo.goquizz.models.SeePinModel;
import com.valdo.goquizz.models.SeeRankModel;

import java.util.List;

public class SeePinAdapter extends RecyclerView.Adapter<SeePinAdapter.MyViewHolder> {
    List<SeePinModel>seePinModels;

    public SeePinAdapter(List<SeePinModel> seePinModels) {
        this.seePinModels = seePinModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View seeRankPinView = layoutInflater.inflate(R.layout.item_see_pin,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(seeRankPinView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SeePinModel seePinModel = seePinModels.get(position);
        holder.pinUser.setText(seePinModel.getPinUserActivity());

    }

    @Override
    public int getItemCount() {
        return (seePinModels != null) ? seePinModels.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pinUser;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pinUser = itemView.findViewById(R.id.textViewItemseepin);
        }
    }
}
