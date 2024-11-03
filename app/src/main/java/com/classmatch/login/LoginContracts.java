package com.classmatch.login;

import com.classmatch.login.entity.Credenciais;

public interface LoginContracts {

    interface View {}

    interface Presenter {
        void login(Credenciais credenciais);
    }

    interface Interactor {
        void existeProfessor(String id, Listener<Boolean> listener);
        void existeAluno(String id, Listener<Boolean> listener);
        void existeCoordenador(String id, Listener<Boolean> listener);
        void login(Credenciais credenciais, Listener<String> listener);

        interface Listener<T> {
            void onSuccess(T data);
            void onError(String error);
        }
    }

    interface Router {
        void navigateToAlunoActivity();
        void navigateToProfessorActivity();
        void navigateToCoordenadorActivity();
    }
}
