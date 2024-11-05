package com.classmatch.orientador.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.orientador.ResultadoContracts;
import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ProfessorCard;
import com.classmatch.orientador.presenter.ResultadoPresenter;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAlunosFragment extends Fragment implements ResultadoContracts.ViewAluno {

    private RecyclerView recyclerView;
    private ResultadoAlunoCardAdapter adapter;
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
            adapter = new ResultadoAlunoCardAdapter();
            recyclerView.setAdapter(adapter);

            presenter.retrieveAlunoCard(idClasse);
        }

        return view;
    }

    @Override
    public void onAlunoCardRetrieved(ArrayList<AlunoCard> alunos) {
        adapter.setItems(alunos);
    }
}