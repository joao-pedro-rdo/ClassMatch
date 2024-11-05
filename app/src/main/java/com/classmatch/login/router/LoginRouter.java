package com.classmatch.login.router;

import android.content.Context;
import android.content.Intent;

import com.classmatch.aluno.view.AlunoActivity;
import com.classmatch.login.LoginContracts;
import com.classmatch.orientador.view.OrientadorActivity;
import com.classmatch.professor.view.ProfessorActivity;

public class LoginRouter implements LoginContracts.Router {
    private Context context;

    public LoginRouter(Context context) {
        this.context = context;
    }

    @Override
    public void navigateToAlunoActivity() {
        Intent intent = new Intent(context, AlunoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void navigateToProfessorActivity() {
        Intent intent = new Intent(context, ProfessorActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void navigateToCoordenadorActivity() {
        Intent intent = new Intent(context, OrientadorActivity.class);
        context.startActivity(intent);
    }
}
