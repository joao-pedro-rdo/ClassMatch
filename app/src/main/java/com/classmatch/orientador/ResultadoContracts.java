package com.classmatch.orientador;

import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ProfessorCard;

import java.util.ArrayList;

public interface ResultadoContracts {

    interface View {}

    interface ViewProfessor {
        void onProfessorCardRetrieved(ArrayList<ProfessorCard> professores);
    }

    interface ViewAluno {
        void onAlunoCardRetrieved(ArrayList<AlunoCard> alunos);
    }

    interface Presenter {
        void retrieveAlunoCard(String id);
        void retrieveProfessorCard(String id);
        void onAlunoCardDownloaded(ArrayList<AlunoCard> alunos);
        void onProfessorCardDownloaded(ArrayList<ProfessorCard> alunos);
    }

    interface Interactor {
        void downloadAlunoCard(String id);
        void downloadProfessorCard(String id);
    }

    interface Router {}
}
