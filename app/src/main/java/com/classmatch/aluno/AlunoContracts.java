package com.classmatch.aluno;

import com.classmatch.aluno.entity.ClasseCard;

import java.util.ArrayList;

public interface AlunoContracts {

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
