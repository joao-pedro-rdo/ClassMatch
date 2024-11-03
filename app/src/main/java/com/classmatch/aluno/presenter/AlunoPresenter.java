package com.classmatch.aluno.presenter;

import com.classmatch.aluno.AlunoContracts;
import com.classmatch.aluno.entity.Aluno;
import com.classmatch.aluno.entity.Classe;
import com.classmatch.aluno.entity.ClasseCard;
import com.classmatch.aluno.interactor.AlunoInteractor;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AlunoPresenter implements AlunoContracts.Presenter {

    AlunoContracts.View view;
    AlunoContracts.Interactor interactor;

    private Aluno aluno;
    private ArrayList<ClasseCard> classeCardList = new ArrayList<>();
    private ArrayList<Classe> classeList= new ArrayList<>();
    private ArrayList<String> alunoClassesIds = new ArrayList<>();

    public AlunoPresenter(AlunoContracts.View view) {
        this.view = view;
        this.interactor = new AlunoInteractor(this);

        interactor.getCurrentUserId(id -> {
            interactor.downloadAluno(id, resAluno -> {
                aluno = resAluno;
            });
        });
    }

    @Override
    public void requestClasseCardList() {
        interactor.getCurrentUserId(id -> {
            interactor.downloadClasseLista();
            interactor.downloadAlunoClassesLista(id);
        });
    }

    @Override
    public void onClasseListaDownloaded(ArrayList<Classe> classesLista) {
        this.classeList = classesLista;
        updateClasseCardList();
    }

    @Override
    public void onAlunoClasseListaDownloaded(ArrayList<String> classesIds) {
        this.alunoClassesIds = classesIds;
        updateClasseCardList();
    }

    private void updateClasseCardList() {
        classeCardList = (ArrayList<ClasseCard>) classeList.stream().map(classe -> {
            ClasseCard classeCard = new ClasseCard(classe, false);
            if (alunoClassesIds.contains(classe.getId())) {
                classeCard.setSelecionada(true);
            }

            return classeCard;
        }).collect(Collectors.toList());

        view.onClasseCardListRetrived(classeCardList);
    }

    @Override
    public void selectClasse(Classe classe) {
        interactor.selectClasse(aluno, classe);
    }

    @Override
    public void deselectClasse(Classe classe) {
        interactor.deselectClasse(aluno, classe);
    }
}
