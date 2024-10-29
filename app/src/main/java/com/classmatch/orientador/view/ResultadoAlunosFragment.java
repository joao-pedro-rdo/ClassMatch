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
import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ProfessorCard;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAlunosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado_tab, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<AlunoCard> alunoList = new ArrayList<>();
        alunoList.add(new AlunoCard("Lucas Almeida"));
        alunoList.add(new AlunoCard("Mariana Souza"));
        alunoList.add(new AlunoCard("Pedro Silva"));
        alunoList.add(new AlunoCard("Ana Clara"));
        alunoList.add(new AlunoCard("Felipe Santos"));
        alunoList.add(new AlunoCard("Julia Costa"));
        alunoList.add(new AlunoCard("Roberto Ferreira"));
        alunoList.add(new AlunoCard("Isabela Lima"));
        alunoList.add(new AlunoCard("Gabriel Rocha"));
        alunoList.add(new AlunoCard("Larissa Martins"));
        alunoList.add(new AlunoCard("Tiago Oliveira"));
        alunoList.add(new AlunoCard("Fernanda Dias"));
        alunoList.add(new AlunoCard("André Pires"));
        alunoList.add(new AlunoCard("Bianca Ramos"));
        alunoList.add(new AlunoCard("Samuel Cardoso"));
        alunoList.add(new AlunoCard("Camila Mendes"));
        alunoList.add(new AlunoCard("Rafael Almeida"));
        alunoList.add(new AlunoCard("Aline Santos"));
        alunoList.add(new AlunoCard("Thiago Gomes"));
        alunoList.add(new AlunoCard("Carla Nascimento"));

        ResultadoAlunoCardAdapter adapter = new ResultadoAlunoCardAdapter(alunoList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}