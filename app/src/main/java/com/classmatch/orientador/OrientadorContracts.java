package com.classmatch.orientador;

import com.classmatch.orientador.entity.Classe;
import com.classmatch.orientador.entity.ClasseCard;

import java.util.ArrayList;

public interface OrientadorContracts {

    interface View {
        void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList);
    }

    interface Presenter {
        void requestClasseCardList();
        void onClasseCardListDownloaded(ArrayList<ClasseCard> classeCardList);
        void onClasseCardClicked(Classe classe);
    }

    interface Interactor {
        void downloadClasseCardList();
    }

    interface Router {
        void navigateToResultado(Classe classe);
    }
}
