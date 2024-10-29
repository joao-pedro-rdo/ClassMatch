package com.classmatch.orientador.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.orientador.entity.ProfessorCard;

import java.util.List;

public class ResultadoProfessorCardAdapter extends RecyclerView.Adapter<ResultadoProfessorCardAdapter.ViewHolder> {
    private final List<ProfessorCard> items;

    public ResultadoProfessorCardAdapter(List<ProfessorCard> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resutado_professor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfessorCard card = items.get(position);
        holder.textView.setText(card.getNome());
        holder.interesse.setText(Integer.toString(card.getInteresse()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView interesse;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.nome_professor);
            interesse = itemView.findViewById(R.id.textview_interesse);
        }
    }
}
