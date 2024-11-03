package com.classmatch.aluno.interactor;

import androidx.annotation.NonNull;

import com.classmatch.aluno.AlunoContracts;
import com.classmatch.aluno.entity.Aluno;
import com.classmatch.aluno.entity.Classe;
import com.classmatch.aluno.entity.ClasseCard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ServerValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AlunoInteractor implements AlunoContracts.Interactor {

    private final DatabaseReference mDatabase;
    private final FirebaseAuth mAuth;
    AlunoContracts.Presenter presenter;

    public AlunoInteractor(AlunoContracts.Presenter presenter) {
        this.presenter = presenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void downloadAluno(String id, Listener<Aluno> listener) {
        mDatabase.child("/aluno/"+id+"/nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nome = (String) snapshot.getValue();
                if (nome != null) {
                    Aluno aluno = new Aluno(nome, id);
                    listener.onSuccess(aluno);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    public void getCurrentUserId(Listener<String> listener) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            listener.onSuccess(user.getUid());
        }
    }

    @Override
    public void downloadClasseLista() {
        mDatabase.child("/curso/0/meta/disciplina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Classe> classeLista = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Classe value = snapshot.getValue(Classe.class);
                    if (value != null) {
                        value.setId(snapshot.getKey());
                        classeLista.add(value);
                    }
                }

                presenter.onClasseListaDownloaded(classeLista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void downloadAlunoClassesLista(String id) {
        mDatabase.child("/aluno/" + id + "/disciplina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> classesIds = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Boolean value = snapshot.getValue(Boolean.class);
                    if (Boolean.TRUE.equals(value)) {
                        classesIds.add(snapshot.getKey());
                    }
                }

                presenter.onAlunoClasseListaDownloaded(classesIds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    public void selectClasse(Aluno aluno, Classe classe) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("/aluno/"+aluno.getId()+"/disciplina/"+classe.getId(), true);
        updates.put("/curso/0/disciplina/"+classe.getId()+"/aluno/"+aluno.getId()+"/nome", aluno.getNome());
        updates.put("/curso/0/meta/disciplina/"+classe.getId()+"/alunos", ServerValue.increment(1));
        mDatabase.updateChildren(updates);
    }

    @Override
    public void deselectClasse(Aluno aluno, Classe classe) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("/aluno/"+aluno.getId()+"/disciplina/"+classe.getId(), null);
        updates.put("/curso/0/disciplina/"+classe.getId()+"/aluno/"+aluno.getId(), null);
        updates.put("/curso/0/meta/disciplina/"+classe.getId()+"/alunos", ServerValue.increment(-1));
        mDatabase.updateChildren(updates);
    }
}
