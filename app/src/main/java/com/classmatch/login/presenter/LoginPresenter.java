package com.classmatch.login.presenter;

import com.classmatch.login.LoginContracts;
import com.classmatch.login.entity.Credenciais;

public class LoginPresenter implements LoginContracts.Presenter {

    private LoginContracts.View view;
    private LoginContracts.Router router;
    private LoginContracts.Interactor interactor;

    public LoginPresenter(
            LoginContracts.View view,
            LoginContracts.Router router,
            LoginContracts.Interactor interactor
    ) {
        this.view = view;
        this.router = router;
        this.interactor = interactor;
    }

    public void login(Credenciais credenciais) {
        this.interactor.login(credenciais, new LoginContracts.Interactor.Listener<String>() {
            @Override
            public void onSuccess(String data) {
                handleSuccessLogin(data);
            }

            @Override
            public void onError(String error) {
                // TODO: Adicionar erro na view
            }
        });
    }

    public void onLogin() {
        System.out.println("Login");
    }

    private void handleSuccessLogin(String userUid) {
        interactor.existeProfessor(userUid, new LoginContracts.Interactor.Listener<Boolean>() {
            @Override
            public void onSuccess(Boolean existe) {
                if (existe) {
                    router.navigateToProfessorActivity();
                }
            }

            @Override
            public void onError(String error) {

            }
        });

        interactor.existeAluno(userUid, new LoginContracts.Interactor.Listener<Boolean>() {
            @Override
            public void onSuccess(Boolean existe) {
                if (existe) {
                    router.navigateToAlunoActivity();
                }
            }

            @Override
            public void onError(String error) {

            }
        });

        interactor.existeCoordenador(userUid, new LoginContracts.Interactor.Listener<Boolean>() {
            @Override
            public void onSuccess(Boolean existe) {
                if (existe) {
                    router.navigateToCoordenadorActivity();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
