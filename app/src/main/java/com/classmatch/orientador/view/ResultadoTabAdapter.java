package com.classmatch.orientador.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ResultadoTabAdapter extends FragmentStateAdapter {
    private String idClasse;

    public ResultadoTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ResultadoTabAdapter(@NonNull FragmentActivity fragmentActivity, String idClasse) {
        super(fragmentActivity);
        this.idClasse = idClasse;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("idClasse", idClasse);
        if (position == 0) {
            ResultadoProfessoresFragment fragment = new ResultadoProfessoresFragment();
            fragment.setArguments(bundle);
            return fragment;
        } else {
            ResultadoAlunosFragment fragment = new ResultadoAlunosFragment();
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Duas tabs
    }
}