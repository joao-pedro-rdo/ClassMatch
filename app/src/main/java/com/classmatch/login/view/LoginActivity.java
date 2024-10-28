package com.classmatch.login.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.classmatch.R;
import com.classmatch.login.LoginContracts;
import com.classmatch.login.entity.Credenciais;
import com.classmatch.login.presenter.LoginPresenter;
import com.classmatch.login.router.LoginRouter;

public class LoginActivity extends AppCompatActivity implements LoginContracts.View {

    private LoginContracts.Presenter presenter;

    private Credenciais credenciais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.presenter = new LoginPresenter(this, new LoginRouter(this));
        this.credenciais = new Credenciais();

        addEmailInput();
        addSenhaInput();
        addLoginButton();
    }

    void addEmailInput() {
        EditText emailInput = findViewById(R.id.editTextEmail);
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                credenciais.setEmail(editable.toString());
            }
        });
    }

    void addSenhaInput() {
        EditText emailInput = findViewById(R.id.editTextSenha);
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                credenciais.setSenha(editable.toString());
            }
        });
    }

    void addLoginButton() {
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(view -> {
            switch (credenciais.getEmail()) {
                case "aluno":
                    presenter.onAlunoLogin();
                    break;
                case "professor":
                    presenter.onProfessorLogin();
                    break;
                case "orientador":
                    presenter.onOrientadorLogin();
                    break;
                default:
                    Toast.makeText(this, "Credencias inválidas! Use aluno, prefessor ou orientador", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}