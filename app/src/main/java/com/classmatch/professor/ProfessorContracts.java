package com.classmatch.professor;

import com.classmatch.professor.entity.ClasseCard;

import java.util.ArrayList;

public interface ProfessorContracts {

    interface View {
        void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList);
//        void setContadorClasseCardSelecionaods(int quantidade);
    }

    interface Presenter {
        void requestClasseCardList();
        void onClasseCardListDownloaded(ArrayList<ClasseCard> classeCardList);
    }

    interface Interactor {
        void downloadClasseCardList();
    }

    interface Router {}
}
