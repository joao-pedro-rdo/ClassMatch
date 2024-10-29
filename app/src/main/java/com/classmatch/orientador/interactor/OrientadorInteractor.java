package com.classmatch.orientador.interactor;

import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.Classe;
import com.classmatch.orientador.entity.ClasseCard;

import java.util.ArrayList;
import java.util.UUID;

public class OrientadorInteractor implements OrientadorContracts.Interactor {

    OrientadorContracts.Presenter presenter;
//    private final DatabaseReference database;

    public OrientadorInteractor(OrientadorContracts.Presenter presenter) {
        this.presenter = presenter;
//        database = FirebaseDatabase.getInstance().getReference().child("studentClasses");
    }

//    @Override
//    public void downloadClasseCardList() {
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<ClasseCard> studentClassCardsList = new ArrayList<>();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    StudentClassCard value = snapshot.getValue(StudentClassCard.class);
//                    if (value != null) {
//                        studentClassCardsList.add(value);
//                    }
//                }
//
//                presenter.onClientListDownloaded(studentClassCardsList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }

    @Override
    public void downloadClasseCardList() {
        ArrayList<ClasseCard> classeCards = new ArrayList<>();

        // Criando 10 classes
        Classe classe1 = new Classe(UUID.randomUUID().toString(), "Matemática", "MAT101", "Engenharia", 1, false);
        Classe classe2 = new Classe(UUID.randomUUID().toString(), "Física", "FIS101", "Engenharia", 1, false);
        Classe classe3 = new Classe(UUID.randomUUID().toString(), "Química", "QUI101", "Engenharia", 1, false);
        Classe classe4 = new Classe(UUID.randomUUID().toString(), "Programação", "PROG101", "Sistemas", 1, false);
        Classe classe5 = new Classe(UUID.randomUUID().toString(), "Estruturas de Dados", "ED101", "Sistemas", 2, true);
        Classe classe6 = new Classe(UUID.randomUUID().toString(), "Banco de Dados", "BD101", "Sistemas", 2, false);
        Classe classe7 = new Classe(UUID.randomUUID().toString(), "Rede de Computadores", "RED101", "Sistemas", 2, false);
        Classe classe8 = new Classe(UUID.randomUUID().toString(), "Algoritmos", "ALGO101", "Sistemas", 2, true);
        Classe classe9 = new Classe(UUID.randomUUID().toString(), "Engenharia de Software", "ES101", "Sistemas", 3, true);
        Classe classe10 = new Classe(UUID.randomUUID().toString(), "Inteligência Artificial", "IA101", "Sistemas", 3, false);

        // Criando os ClasseCards
        classeCards.add(new ClasseCard(classe1, 3, 15));
        classeCards.add(new ClasseCard(classe2, 2, 10));
        classeCards.add(new ClasseCard(classe3, 1, 21));
        classeCards.add(new ClasseCard(classe4, 4, 23));
        classeCards.add(new ClasseCard(classe5, 3, 18));
        classeCards.add(new ClasseCard(classe6, 2, 14));
        classeCards.add(new ClasseCard(classe7, 2, 13));
        classeCards.add(new ClasseCard(classe8, 3, 17));
        classeCards.add(new ClasseCard(classe9, 4, 22));
        classeCards.add(new ClasseCard(classe10, 5, 20));

        presenter.onClasseCardListDownloaded(classeCards);
    }
}
