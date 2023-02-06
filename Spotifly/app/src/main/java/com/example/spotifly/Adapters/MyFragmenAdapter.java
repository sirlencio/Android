package com.example.spotifly.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.spotifly.Fragments.InicioFragment;
import com.example.spotifly.Fragments.PlaylistFragment;

public class MyFragmenAdapter extends FragmentStateAdapter {
    public MyFragmenAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new InicioFragment();
        }
        return new PlaylistFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
