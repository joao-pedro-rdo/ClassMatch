package com.classmatch.orientador.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classmatch.R;
import com.classmatch.orientador.ResultadoContracts;
import com.classmatch.orientador.entity.ProfessorCard;
import com.classmatch.orientador.presenter.ResultadoPresenter;

import java.util.ArrayList;

public class ResultadoProfessoresFragment extends Fragment implements ResultadoContracts.ViewProfessor {

    private RecyclerView recyclerView;
    private ResultadoProfessorCardAdapter adapter;
    private ResultadoContracts.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado_tab, container, false);

        presenter = new ResultadoPresenter(this);

        if (getArguments() != null) {
            String idClasse = getArguments().getString("idClasse");

            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new ResultadoProfessorCardAdapter();
            recyclerView.setAdapter(adapter);

            presenter.retrieveProfessorCard(idClasse);
        }

        return view;
    }

    @Override
    public void onProfessorCardRetrieved(ArrayList<ProfessorCard> professores) {
        adapter.setItems(professores);
    }
}