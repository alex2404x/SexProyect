package com.nikobelich.sexproyect.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nikobelich.sexproyect.R;
import com.nikobelich.sexproyect.data.DatingRepository;
import com.nikobelich.sexproyect.data.model.UserProfile;
import com.nikobelich.sexproyect.ui.common.SpacingItemDecoration;

import java.util.List;

public class DiscoverFragment extends Fragment implements DiscoverAdapter.ProfileActionListener {

    private DatingRepository repository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = DatingRepository.getInstance(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.discoverRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_large)));

        List<UserProfile> profiles = repository.getDiscoveryProfiles();
        DiscoverAdapter adapter = new DiscoverAdapter(profiles, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProfileLiked(@NonNull UserProfile profile) {
        String message = getString(R.string.profile_liked_message, profile.getName());
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileSkipped(@NonNull UserProfile profile) {
        String message = getString(R.string.profile_skipped_message, profile.getName());
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
