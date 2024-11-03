package com.classmatch.orientador.presenter;

import com.classmatch.orientador.ResultadoContracts;
import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ProfessorCard;
import com.classmatch.orientador.interactor.ResultadoIteractor;
import com.classmatch.orientador.view.ResultadoAlunosFragment;
import com.classmatch.orientador.view.ResultadoProfessoresFragment;

import java.util.ArrayList;

public class ResultadoPresenter implements ResultadoContracts.Presenter {

    ResultadoContracts.ViewProfessor viewProfessor;
    ResultadoContracts.ViewAluno viewAluno;
    ResultadoContracts.Interactor interactor;

    public ResultadoPresenter(ResultadoProfessoresFragment fragment) {
        this.viewProfessor = fragment;
        this.interactor = new ResultadoIteractor(this);
    }

    public ResultadoPresenter(ResultadoAlunosFragment fragment) {
        this.viewAluno = fragment;
        this.interactor = new ResultadoIteractor(this);
    }


    @Override
    public void retrieveAlunoCard(String id) {
        interactor.downloadAlunoCard(id);
    }

    @Override
    public void retrieveProfessorCard(String id) {
        interactor.downloadProfessorCard(id);
    }

    @Override
    public void onAlunoCardDownloaded(ArrayList<AlunoCard> alunos) {
        viewAluno.onAlunoCardRetrieved(alunos);
    }

    @Override
    public void onProfessorCardDownloaded(ArrayList<ProfessorCard> professores) {
        viewProfessor.onProfessorCardRetrieved(professores);
    }
}
