package com.classmatch.professor.presenter;

import com.classmatch.professor.ProfessorContracts;
import com.classmatch.professor.entity.ClasseCard;
import com.classmatch.professor.interactor.ProfessorInteractor;

import java.util.ArrayList;

public class ProfessorPresenter implements ProfessorContracts.Presenter {

    ProfessorContracts.View view;
    ProfessorContracts.Interactor interactor;

    public ProfessorPresenter(ProfessorContracts.View view) {
        this.view = view;
        this.interactor = new ProfessorInteractor(this);
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
