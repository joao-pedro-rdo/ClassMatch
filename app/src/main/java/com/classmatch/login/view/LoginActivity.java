package com.classmatch.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.classmatch.R;
import com.classmatch.home.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

private EditText edt_matricula;
private EditText edt_senha;
private Button btn_entrar;
private FirebaseAuth mAuth;



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
        mAuth = FirebaseAuth.getInstance();
        edt_matricula = findViewById(R.id.editTextText);
        edt_senha = findViewById(R.id.editTextText2);
        btn_entrar = findViewById(R.id.button);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginmatricula = edt_matricula.getText().toString();
                String loginSenha = edt_senha.getText().toString();

                if(!TextUtils.isEmpty(loginmatricula)|| !TextUtils.isEmpty(loginSenha)){
                    mAuth.signInWithEmailAndPassword(loginmatricula,loginSenha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){
                                     abrirTelaPrincipal();
                                 }else{
                                     String error = task.getException().getMessage();
                                     Toast.makeText(LoginActivity.this,""+error,Toast.LENGTH_SHORT).show();
                                 }
                                }
                            });
                }
            }
        });

    }
    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}