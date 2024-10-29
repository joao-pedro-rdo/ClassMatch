package com.classmatch.orientador.presenter;

import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.Classe;
import com.classmatch.orientador.entity.ClasseCard;
import com.classmatch.orientador.interactor.OrientadorInteractor;
import com.classmatch.orientador.router.OrientadorRouter;

import java.util.ArrayList;

public class OrientadorPresenter implements OrientadorContracts.Presenter {

    OrientadorContracts.View view;
    OrientadorContracts.Interactor interactor;
    OrientadorContracts.Router router;

    public OrientadorPresenter(OrientadorContracts.View view, OrientadorContracts.Router router) {
        this.view = view;
        this.router = router;
        this.interactor = new OrientadorInteractor(this);
    }

    @Override
    public void requestClasseCardList() {
        this.interactor.downloadClasseCardList();
    }

    @Override
    public void onClasseCardListDownloaded(ArrayList<ClasseCard> studentClassesList) {
        this.view.onClasseCardListRetrived(studentClassesList);
    }

    public void onClasseCardClicked(Classe classe) {
        this.router.navigateToResultado(classe);
    }
}
