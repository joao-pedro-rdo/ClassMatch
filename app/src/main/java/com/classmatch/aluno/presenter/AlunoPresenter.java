package com.classmatch.aluno.presenter;

import com.classmatch.aluno.AlunoContracts;
import com.classmatch.aluno.entity.ClasseCard;
import com.classmatch.aluno.interactor.AlunoInteractor;

import java.util.ArrayList;

public class AlunoPresenter implements AlunoContracts.Presenter {

    AlunoContracts.View view;
    AlunoContracts.Interactor interactor;

    public AlunoPresenter(AlunoContracts.View view) {
        this.view = view;
        this.interactor = new AlunoInteractor(this);
    }

    @Override
    public void requestClasseCardList() {
        this.interactor.downloadClasseCardList();
    }

    @Override
    public void onClasseCardListDownloaded(ArrayList<ClasseCard> studentClassesList) {
        this.view.onClasseCardListRetrived(studentClassesList);
    }
}
