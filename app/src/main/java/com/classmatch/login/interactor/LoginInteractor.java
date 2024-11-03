package com.classmatch.login.interactor;


import androidx.annotation.NonNull;

import com.classmatch.login.LoginContracts;
import com.classmatch.login.entity.Credenciais;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginInteractor implements LoginContracts.Interactor {
    private final FirebaseAuth mAuth;
    private final DatabaseReference mDatabase;

    public LoginInteractor() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void login(Credenciais credenciais, Listener<String> listener) {
        mAuth.signInWithEmailAndPassword(credenciais.getEmail(), credenciais.getSenha())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            listener.onSuccess(user.getUid());
                        } else {
                            listener.onError("Usuário não encontrado.");
                        }
                    } else {
                        listener.onError("Não foi possível fazer o login.");
                    }
                });
    }

    @Override
    public void existeProfessor(String id, Listener<Boolean> listener) {
        mDatabase.child("/professor/"+id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listener.onSuccess(true);
                } else {
                    listener.onSuccess(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onError("Error checking item: " + error.getMessage());
            }
        });
    }

    @Override
    public void existeAluno(String id, Listener<Boolean> listener) {
        mDatabase.child("/aluno/"+id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listener.onSuccess(true);
                } else {
                    listener.onSuccess(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onError("Error checking item: " + error.getMessage());
            }
        });
    }

    @Override
    public void existeCoordenador(String id, Listener<Boolean> listener) {
        mDatabase.child("/coordenador/"+id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listener.onSuccess(true);
                } else {
                    listener.onSuccess(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onError("Error checking item: " + error.getMessage());
            }
        });
    }
}
