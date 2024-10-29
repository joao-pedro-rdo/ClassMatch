package com.classmatch.orientador.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ResultadoTabAdapter extends FragmentStateAdapter {
    public ResultadoTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ResultadoTabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ResultadoTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ResultadoProfessoresFragment();
        } else {
            return new ResultadoAlunosFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2; // Duas tabs
    }
}