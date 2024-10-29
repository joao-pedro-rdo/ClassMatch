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
import com.classmatch.orientador.entity.ProfessorCard;

import java.util.ArrayList;
import java.util.List;

public class ResultadoProfessoresFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado_tab, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ProfessorCard> professorList = new ArrayList<>();
        professorList.add(new ProfessorCard("Maria Oliveira", 5));
        professorList.add(new ProfessorCard("Ana Silva", 4));
        professorList.add(new ProfessorCard("Beatriz Costa", 4));
        professorList.add(new ProfessorCard("João Pereira", 3));
        professorList.add(new ProfessorCard("Carlos Sousa", 2));


        ResultadoProfessorCardAdapter adapter = new ResultadoProfessorCardAdapter(professorList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}