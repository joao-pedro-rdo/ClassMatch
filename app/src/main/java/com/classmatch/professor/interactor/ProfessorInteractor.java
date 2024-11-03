package com.classmatch.professor.interactor;

import androidx.annotation.NonNull;

import com.classmatch.professor.ProfessorContracts;
import com.classmatch.professor.entity.Classe;
import com.classmatch.professor.entity.ClasseCard;
import com.classmatch.professor.entity.Professor;
import com.classmatch.professor.entity.ProfessorClasse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfessorInteractor implements ProfessorContracts.Interactor {

    private final DatabaseReference mDatabase;
    private final FirebaseAuth mAuth;
    ProfessorContracts.Presenter presenter;

    public ProfessorInteractor(ProfessorContracts.Presenter presenter) {
        this.presenter = presenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void downloadProfessor(String id, Listener<Professor> listener) {
        mDatabase.child("/professor/"+id+"/nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nome = (String) snapshot.getValue();
                if (nome != null) {
                    Professor professor = new Professor(nome, id);
                    listener.onSuccess(professor);
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
    public void downloadProfessorClassesLista(String id) {
        mDatabase.child("/professor/" + id + "/disciplina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ProfessorClasse> professorClasses = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProfessorClasse value = snapshot.getValue(ProfessorClasse.class);
                    if (value != null) {
                        value.setId(snapshot.getKey());
                        professorClasses.add(value);
                    }
                }

                presenter.onProfessorClasseListaDownloaded(professorClasses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    public void selectClasse(Professor professor, ClasseCard classeCard) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("/professor/"+professor.getId()+"/disciplina/"+classeCard.getClasse().getId()+"/nota", classeCard.getInteresse());
        updates.put("/curso/0/disciplina/"+classeCard.getClasse().getId()+"/professor/"+professor.getId()+"/nome", professor.getNome());
        updates.put("/curso/0/disciplina/"+classeCard.getClasse().getId()+"/professor/"+professor.getId()+"/nota", classeCard.getInteresse());
        updates.put("/curso/0/meta/disciplina/"+classeCard.getClasse().getId()+"/professores", ServerValue.increment(1));
        mDatabase.updateChildren(updates);
    }

    @Override
    public void updateNotaClasse(Professor professor, ClasseCard classeCard) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("/professor/"+professor.getId()+"/disciplina/"+classeCard.getClasse().getId()+"/nota", classeCard.getInteresse());
        updates.put("/curso/0/disciplina/"+classeCard.getClasse().getId()+"/professor/"+professor.getId()+"/nome", professor.getNome());
        updates.put("/curso/0/disciplina/"+classeCard.getClasse().getId()+"/professor/"+professor.getId()+"/nota", classeCard.getInteresse());
        mDatabase.updateChildren(updates);
    }

    @Override
    public void deselectClasse(Professor professor, Classe classe) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("/professor/"+professor.getId()+"/disciplina/"+classe.getId(), null);
        updates.put("/curso/0/disciplina/"+classe.getId()+"/professor/"+professor.getId(), null);
        updates.put("/curso/0/meta/disciplina/"+classe.getId()+"/professores", ServerValue.increment(-1));
        mDatabase.updateChildren(updates);
    }
}
