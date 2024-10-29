package com.classmatch.orientador.router;

import android.content.Context;
import android.content.Intent;

import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.Classe;
import com.classmatch.orientador.view.ResultadoActivity;

public class OrientadorRouter implements OrientadorContracts.Router {
    private Context context;

    public OrientadorRouter(Context context) {
        this.context = context;
    }

    @Override
    public void navigateToResultado(Classe classe) {
        Intent intent = new Intent(context, ResultadoActivity.class);
        intent.putExtra("classe", classe.getNome());
        context.startActivity(intent);
    }
}
