package com.nikobelich.sexproyect.ui.matches;

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

public class MatchesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.matchesRecyclerView);
        TextView emptyView = view.findViewById(R.id.emptyMatchesView);

        DatingRepository repository = DatingRepository.getInstance(requireContext());
        MatchesAdapter adapter = new MatchesAdapter(repository.getMatches());

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_medium)));
        recyclerView.setAdapter(adapter);

        boolean hasMatches = adapter.getItemCount() > 0;
        recyclerView.setVisibility(hasMatches ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(hasMatches ? View.GONE : View.VISIBLE);
    }
}
