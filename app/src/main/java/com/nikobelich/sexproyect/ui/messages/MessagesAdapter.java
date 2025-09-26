package com.nikobelich.sexproyect.ui.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.model.MessageThread;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private final List<MessageThread> threads;

    public MessagesAdapter(List<MessageThread> threads) {
        this.threads = threads;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_thread, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(threads.get(position));
    }

    @Override
    public int getItemCount() {
        return threads.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private final ImageView photoView;
        private final TextView nameView;
        private final TextView messagePreviewView;
        private final TextView timestampView;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.threadPhoto);
            nameView = itemView.findViewById(R.id.threadName);
            messagePreviewView = itemView.findViewById(R.id.threadPreview);
            timestampView = itemView.findViewById(R.id.threadTimestamp);
        }

        void bind(MessageThread thread) {
            photoView.setImageResource(thread.getMatch().getProfile().getPhotoRes());
            nameView.setText(thread.getMatch().getProfile().getName());
            messagePreviewView.setText(thread.getLastMessage());
            timestampView.setText(thread.getTimestamp());
            int colorRes = thread.isUnread() ? R.color.message_unread : R.color.message_read;
            messagePreviewView.setTextColor(ContextCompat.getColor(itemView.getContext(), colorRes));
            itemView.setOnClickListener(v -> Toast.makeText(
                    itemView.getContext(),
                    itemView.getContext().getString(R.string.message_open_chat, thread.getMatch().getProfile().getName()),
                    Toast.LENGTH_SHORT
            ).show());
        }
    }
}
