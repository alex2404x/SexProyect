package com.tinderclone.feature.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.tinderclone.core.domain.model.User;
import com.tinderclone.feature.home.databinding.ItemCardProfileBinding;

import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<User> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCardProfileBinding binding = ItemCardProfileBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = items.get(position);
        holder.binding.textNameAge.setText(String.format("%s, %d", user.getPublicProfile().getDisplayName(), user.getPublicProfile().getAge()));

        if (user.getPublicProfile().getPhotos() != null && !user.getPublicProfile().getPhotos().isEmpty()) {
            Glide.with(holder.binding.imageProfile)
                    .load(user.getPublicProfile().getPhotos().get(0))
                    .into(holder.binding.imageProfile);
        }

        holder.binding.chipGroupInterests.removeAllViews();
        if (user.getPublicProfile().getInterests() != null) {
            for (String interest : user.getPublicProfile().getInterests()) {
                Chip chip = new Chip(holder.itemView.getContext());
                chip.setText(interest);
                holder.binding.chipGroupInterests.addView(chip);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<User> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<User> getItems() {
        return items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardProfileBinding binding;

        ViewHolder(@NonNull ItemCardProfileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
