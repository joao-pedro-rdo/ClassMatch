package com.classmatch.login.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.classmatch.R;
import com.classmatch.login.LoginContracts;
import com.classmatch.login.entity.Credenciais;
import com.classmatch.login.interactor.LoginInteractor;
import com.classmatch.login.presenter.LoginPresenter;
import com.classmatch.login.router.LoginRouter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginContracts.View {

    private LoginContracts.Presenter presenter;
    private Credenciais credenciais;
//    private FirebaseAuth mAuth;

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
//        mAuth = FirebaseAuth.getInstance();

        this.presenter = new LoginPresenter(this, new LoginRouter(this), new LoginInteractor());
        this.credenciais = new Credenciais();

//        this.presenter.onOrientadorLogin();

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
            if(!TextUtils.isEmpty(credenciais.getEmail())|| !TextUtils.isEmpty(credenciais.getSenha())) {
                this.presenter.login(credenciais);
            }
        });

    }
}