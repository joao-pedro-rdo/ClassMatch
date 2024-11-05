package com.classmatch.professor.presenter;

import com.classmatch.professor.ProfessorContracts;
import com.classmatch.professor.entity.Classe;
import com.classmatch.professor.entity.ClasseCard;
import com.classmatch.professor.entity.Professor;
import com.classmatch.professor.entity.ProfessorClasse;
import com.classmatch.professor.interactor.ProfessorInteractor;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProfessorPresenter implements ProfessorContracts.Presenter {

    ProfessorContracts.View view;
    ProfessorContracts.Interactor interactor;
    private Professor professor;
    private ArrayList<ClasseCard> classeCardList = new ArrayList<>();
    private ArrayList<Classe> classeList= new ArrayList<>();
    private ArrayList<ProfessorClasse> professorClasses = new ArrayList<>();

    public ProfessorPresenter(ProfessorContracts.View view) {
        this.view = view;
        this.interactor = new ProfessorInteractor(this);

        interactor.getCurrentUserId(id -> {
            interactor.downloadProfessor(id, resProfessor-> {
                professor = resProfessor;
            });
        });
    }

    @Override
    public void requestClasseCardList() {
        interactor.getCurrentUserId(id -> {
            interactor.downloadClasseLista();
            interactor.downloadProfessorClassesLista(id);
        });
    }

    @Override
    public void onClasseListaDownloaded(ArrayList<Classe> classesLista) {
        this.classeList = classesLista;
        updateClasseCardList();
    }

    @Override
    public void onProfessorClasseListaDownloaded(ArrayList<ProfessorClasse> classes) {
        this.professorClasses = classes;
        updateClasseCardList();
    }

    private void updateClasseCardList() {
        classeCardList = (ArrayList<ClasseCard>) classeList.stream().map(classe -> {
            ClasseCard classeCard = new ClasseCard();
            classeCard.setClasse(classe);
            classeCard.setInteresse(0);
            classeCard.setSelecionada(false);
            Optional<ProfessorClasse> professorClasse = professorClasses
                    .stream()
                    .filter(cls -> cls.getId().equals(classe.getId()))
                    .findFirst();
            if (professorClasse.isPresent()) {
                classeCard.setInteresse(professorClasse.get().getNota());
                classeCard.setSelecionada(true);
            }

            return classeCard;
        }).collect(Collectors.toList());

        view.onClasseCardListRetrived(classeCardList);
    }

    @Override
    public void selectClasse(ClasseCard classe) {
        interactor.selectClasse(professor, classe);
    }

    @Override
    public void updateNotaClasse(ClasseCard classe) {
        interactor.updateNotaClasse(professor, classe);
    }

    @Override
    public void deselectClasse(Classe classe) {
        interactor.deselectClasse(professor, classe);
    }
}
