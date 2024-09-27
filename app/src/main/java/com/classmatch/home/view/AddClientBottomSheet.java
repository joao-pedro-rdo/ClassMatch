package com.classmatch.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.classmatch.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddClientBottomSheet extends BottomSheetDialogFragment {

    public static final String FRAGMENT_KEY = "com.yosoy.beta.core_chat.view.response.ConvenioBottomSheet";
    Button btnContinue;
    MainActivity main;
    ProgressBar progressBar;
    BottomSheetDialogFragment fragment;
    TextView textViewTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.bottom_sheet_register_client, container);
        main = (MainActivity) getActivity();
        fragment = this;
        textViewTitle = layout.findViewById(R.id.textViewTitle);

        btnContinue = layout.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.dismiss();
            }
        });

        return layout;
    }
}
