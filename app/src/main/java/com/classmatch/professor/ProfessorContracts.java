package com.classmatch.professor;

import com.classmatch.professor.entity.Classe;
import com.classmatch.professor.entity.ClasseCard;
import com.classmatch.professor.entity.Professor;
import com.classmatch.professor.entity.ProfessorClasse;

import java.util.ArrayList;

public interface ProfessorContracts {

    interface View {
        void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList);
    }

    interface Presenter {
        void requestClasseCardList();
        void onClasseListaDownloaded(ArrayList<Classe> classesLista);
        void onProfessorClasseListaDownloaded(ArrayList<ProfessorClasse> classes);
        void selectClasse(ClasseCard classe);
        void updateNotaClasse(ClasseCard classeCard);
        void deselectClasse(Classe classe);
    }

    interface Interactor {
        void downloadProfessor(String id, ProfessorContracts.Interactor.Listener<Professor> listener);
        void getCurrentUserId(ProfessorContracts.Interactor.Listener<String> listener);
        void downloadClasseLista();
        void downloadProfessorClassesLista(String id);

        void selectClasse(Professor professor, ClasseCard classeCard);
        void updateNotaClasse(Professor professor, ClasseCard classeCard);
        void deselectClasse(Professor professor, Classe classe);

        interface Listener<T> {
            void onSuccess(T dados);
        }
    }

    interface Router {}
}
