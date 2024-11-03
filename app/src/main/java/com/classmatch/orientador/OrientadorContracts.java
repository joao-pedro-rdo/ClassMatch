package com.classmatch.orientador;

import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ClasseCard;
import com.classmatch.orientador.entity.ProfessorCard;

import java.util.ArrayList;

public interface OrientadorContracts {

    interface View {
        void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList);
    }

    interface Presenter {
        void requestClasseCardList();
        void salvarClasse(ClasseCard classeCard);
        void onClasseCardListDownloaded(ArrayList<ClasseCard> classeCardList);
        void onClasseCardClicked(ClasseCard classe);
    }

    interface Interactor {
        void downloadClasseCardList();
        void salvarClasse(ClasseCard classe);
    }

    interface Router {
        void navigateToResultado(ClasseCard classe);
    }
}
