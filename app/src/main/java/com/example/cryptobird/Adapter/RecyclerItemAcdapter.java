package com.example.cryptobird.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptobird.databinding.RvItemBinding;
import com.example.cryptobird.models.Modelitem;

import java.util.ArrayList;

public class RecyclerItemAcdapter extends RecyclerView.Adapter<RecyclerItemAcdapter.ViewHolder> {

    private ArrayList<Modelitem> modelitems ;

    public RecyclerItemAcdapter(Context context, ArrayList<Modelitem> modelitems) {
        this.modelitems = modelitems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RvItemBinding binding = RvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // here we will bind the data
        setAnimation(holder.itemView);
        holder.binding.name.setText(modelitems.get(position).getName());
        holder.binding.symbol.setText(modelitems.get(position).getSymbbol());
        holder.binding.price.setText("$" + modelitems.get(position).getPrice() );

    }

    @Override
    public int getItemCount() {
        return modelitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // important imports
        RvItemBinding binding;

        public ViewHolder(RvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setAnimation(View view){

        AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }
}
