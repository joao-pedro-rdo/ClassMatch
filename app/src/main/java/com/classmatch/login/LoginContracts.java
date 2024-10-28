package com.classmatch.login;

public interface LoginContracts {

    interface View {}

    interface Presenter {
        void onAlunoLogin();
        void onProfessorLogin();
        void onOrientadorLogin();
    }

    interface Router {
        void navigateToAlunoActivity();
        void navigateToProfessorActivity();
        void navigateToOrientadorActivity();
    }
}
