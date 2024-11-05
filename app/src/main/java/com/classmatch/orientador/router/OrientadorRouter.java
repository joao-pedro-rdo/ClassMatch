package com.classmatch.orientador.router;

import android.content.Context;
import android.content.Intent;

import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.ClasseCard;
import com.classmatch.orientador.view.ResultadoActivity;

public class OrientadorRouter implements OrientadorContracts.Router {
    private Context context;

    public OrientadorRouter(Context context) {
        this.context = context;
    }

    @Override
    public void navigateToResultado(ClasseCard classe) {
        Intent intent = new Intent(context, ResultadoActivity.class);
        intent.putExtra("idClasse", classe.getId());
        intent.putExtra("nomeClasse", classe.getNome());
        context.startActivity(intent);
    }
}
