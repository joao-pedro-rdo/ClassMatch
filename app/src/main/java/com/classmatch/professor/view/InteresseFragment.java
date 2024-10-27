package com.classmatch.professor.view;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.DialogFragment;

import com.classmatch.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class InteresseFragment extends DialogFragment {

    public interface OnRatingSelectedListener {
        void onRatingSelected(int rating);
    }

    private OnRatingSelectedListener listener;
    private LinearLayout ratingContainer;
    private int selectedRating = 0;

    public InteresseFragment(int selectedRating) {
        this.selectedRating = selectedRating;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_interesse, null);

        // Configurar os botões de rating
        ratingContainer = view.findViewById(R.id.rating_container);
        for (int i = 0; i < ratingContainer.getChildCount(); i++) {
            final TextView ratingButton = (TextView) ratingContainer.getChildAt(i);
            final int rating = Integer.parseInt(ratingButton.getTag().toString());

            ratingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        updateRating(rating);
                        listener.onRatingSelected(rating);
                    }
                    dismiss();
                }
            });
        }

        updateRating(selectedRating);

        // Usar MaterialAlertDialogBuilder para o estilo Material 3
        return new MaterialAlertDialogBuilder(requireContext())
                .setView(view)
                .create();
    }

    public void setOnRatingSelectedListener(OnRatingSelectedListener listener) {
        this.listener = listener;
    }

    private void updateRating(int rating) {
        // Reset all TextViews to outlined style
        for (int i = 0; i < ratingContainer.getChildCount(); i++) {
            final TextView ratingTextView = (TextView) ratingContainer.getChildAt(i);
            TextViewCompat.setTextAppearance(ratingTextView, R.style.RatingOutlinedCircle);
            ratingTextView.setBackgroundResource(R.drawable.outlined_circle_material3);
        }

        // Apply filled style to selected rating
        if (rating >= 1 && rating <= 5) {
            TextView selectedTextView = ratingContainer.findViewWithTag(String.valueOf(rating));
            TextViewCompat.setTextAppearance(selectedTextView, R.style.RatingFilledCircle);
            selectedTextView.setBackgroundResource(R.drawable.filled_circle_material3);
            selectedRating = rating;
        }
    }
}