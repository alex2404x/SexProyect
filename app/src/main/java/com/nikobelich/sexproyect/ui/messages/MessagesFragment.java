package com.nikobelich.sexproyect.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.DatingRepository;
import com.nikobelich.sexproyect.ui.common.SpacingItemDecoration;

public class MessagesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.messagesRecyclerView);
        TextView emptyView = view.findViewById(R.id.emptyMessagesView);

        DatingRepository repository = DatingRepository.getInstance(requireContext());
        MessagesAdapter adapter = new MessagesAdapter(repository.getConversations());

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_small)));
        recyclerView.setAdapter(adapter);

        boolean hasMessages = adapter.getItemCount() > 0;
        recyclerView.setVisibility(hasMessages ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(hasMessages ? View.GONE : View.VISIBLE);
    }
}
