package com.nikobelich.sexproyect.ui.matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.model.Match;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchViewHolder> {

    private final List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        holder.bind(matches.get(position));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    static class MatchViewHolder extends RecyclerView.ViewHolder {

        private final ImageView photoView;
        private final TextView nameView;
        private final TextView matchedAtView;
        private final ImageButton sendMessageButton;

        MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.matchPhoto);
            nameView = itemView.findViewById(R.id.matchName);
            matchedAtView = itemView.findViewById(R.id.matchTime);
            sendMessageButton = itemView.findViewById(R.id.sendMessageButton);
        }

        void bind(Match match) {
            photoView.setImageResource(match.getProfile().getPhotoRes());
            nameView.setText(match.getProfile().getName());
            matchedAtView.setText(match.getMatchedAt());
            sendMessageButton.setOnClickListener(v -> Toast.makeText(
                    itemView.getContext(),
                    itemView.getContext().getString(R.string.match_start_conversation, match.getProfile().getName()),
                    Toast.LENGTH_SHORT
            ).show());
        }
    }
}
