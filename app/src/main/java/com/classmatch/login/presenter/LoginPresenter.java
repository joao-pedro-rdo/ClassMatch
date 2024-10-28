package com.classmatch.login.presenter;

import com.classmatch.login.LoginContracts;

public class LoginPresenter implements LoginContracts.Presenter {

    private LoginContracts.View view;
    private LoginContracts.Router router;

    public LoginPresenter(
            LoginContracts.View view,
            LoginContracts.Router router
    ) {
        this.view = view;
        this.router = router;
    }

    public void onAlunoLogin() {
        router.navigateToAlunoActivity();
    }

    public void onProfessorLogin() {
        router.navigateToProfessorActivity();
    }

    public void onOrientadorLogin() {
        router.navigateToOrientadorActivity();
    }
}
