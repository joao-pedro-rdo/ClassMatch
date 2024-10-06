package com.classmatch.results.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.results.entity.ResultsCard;

import java.util.List;

public class AdapterResults extends RecyclerView.Adapter<AdapterResults.ViewHolder> {

    private List<ResultsCard> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView className;
        private final TextView courseName;
        private final TextView studentsTotal;
        private final TextView studentsInterest;
        private final ProgressBar studentsScore;
        private final TextView teachersTotal;
        private final TextView teachersInterest;
        private final ProgressBar teachersScore;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardViewClient);
            className = view.findViewById(R.id.className);
            courseName = view.findViewById(R.id.courseName);
            studentsTotal = view.findViewById(R.id.studentsTotal);
            studentsInterest = view.findViewById(R.id.studentsInterest);
            studentsScore = view.findViewById(R.id.studentsScore);
            teachersTotal = view.findViewById(R.id.teachersTotal);
            teachersInterest = view.findViewById(R.id.teachersInterest);
            teachersScore = view.findViewById(R.id.teachersScore);
        }

        public void bind(ResultsCard result) {
            className.setText(itemView.getContext().getString(R.string.class_name_code, result.getName(), result.getCode()));
            courseName.setText(itemView.getContext().getString(R.string.course_semester, result.getCourse(), result.getSemester()));

            studentsTotal.setText(String.valueOf(result.getStudents()));
            studentsInterest.setText(result.getInterestStudents());
            studentsScore.setProgress(result.getStudentsScore());

            teachersTotal.setText(String.valueOf(result.getTeachers()));
            teachersInterest.setText(result.getInterestTeachers());
            teachersScore.setProgress(result.getTeachersScore());
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public AdapterResults(List<ResultsCard> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_results, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}