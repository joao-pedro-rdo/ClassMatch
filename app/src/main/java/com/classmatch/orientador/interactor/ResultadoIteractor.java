package com.classmatch.orientador.interactor;

import androidx.annotation.NonNull;

import com.classmatch.orientador.ResultadoContracts;
import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ProfessorCard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultadoIteractor implements ResultadoContracts.Interactor {
    ResultadoContracts.Presenter presenter;
    private final DatabaseReference mDatabase;

    public ResultadoIteractor(ResultadoContracts.Presenter presenter) {
        this.presenter = presenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void downloadAlunoCard(String id) {
        mDatabase.child("/curso/0/disciplina/"+id+"/aluno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<AlunoCard> alunoLista = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AlunoCard value = snapshot.getValue(AlunoCard.class);
                    if (value != null) {
                        alunoLista.add(value);
                    }
                }

                presenter.onAlunoCardDownloaded(alunoLista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void downloadProfessorCard(String id) {
        mDatabase.child("/curso/0/disciplina/"+id+"/professor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ProfessorCard> professorLista = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProfessorCard value = snapshot.getValue(ProfessorCard.class);
                    if (value != null) {
                        professorLista.add(value);
                    }
                }

                presenter.onProfessorCardDownloaded(professorLista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
