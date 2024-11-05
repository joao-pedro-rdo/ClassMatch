package com.classmatch.aluno;

import com.classmatch.aluno.entity.Aluno;
import com.classmatch.aluno.entity.Classe;
import com.classmatch.aluno.entity.ClasseCard;

import java.util.ArrayList;

public interface AlunoContracts {

    interface View {
        void onClasseCardListRetrived(ArrayList<ClasseCard> classCardList);
    }

    interface Presenter {
        void requestClasseCardList();
        void onClasseListaDownloaded(ArrayList<Classe> classesLista);
        void onAlunoClasseListaDownloaded(ArrayList<String> classesIds);
        void selectClasse(Classe classe);
        void deselectClasse(Classe classe);
    }

    interface Interactor {
        void downloadAluno(String id, Listener<Aluno> listener);
        void getCurrentUserId(Listener<String> listener);
        void downloadClasseLista();
        void downloadAlunoClassesLista(String id);

        void selectClasse(Aluno aluno, Classe classe);
        void deselectClasse(Aluno aluno, Classe classe);

        interface Listener<T> {
            void onSuccess(T dados);
        }
    }

    interface Router {}
}
