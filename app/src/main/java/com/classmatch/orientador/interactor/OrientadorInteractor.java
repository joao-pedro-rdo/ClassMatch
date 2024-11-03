package com.classmatch.orientador.interactor;

import androidx.annotation.NonNull;

import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.ClasseCard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrientadorInteractor implements OrientadorContracts.Interactor {

    OrientadorContracts.Presenter presenter;
    private final DatabaseReference mDatabase;

    public OrientadorInteractor(OrientadorContracts.Presenter presenter) {
        this.presenter = presenter;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void downloadClasseCardList() {
        mDatabase.child("/curso/0/meta/disciplina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ClasseCard> classeLista = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClasseCard value = snapshot.getValue(ClasseCard.class);
                    if (value != null) {
                        value.setId(snapshot.getKey());
                        classeLista.add(value);
                    }
                }

                presenter.onClasseCardListDownloaded(classeLista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void salvarClasse(ClasseCard classe) {
        String classeId = mDatabase.child("/curso/0/meta/disciplina").push().getKey();

        if (classeId != null) {
            mDatabase.child("/curso/0/meta/disciplina/"+classeId).setValue(classe)
                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            //
//                        }
                    });
        }
    }
}
