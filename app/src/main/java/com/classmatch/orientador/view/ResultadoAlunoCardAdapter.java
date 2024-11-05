package com.classmatch.orientador.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.orientador.entity.AlunoCard;
import com.classmatch.orientador.entity.ProfessorCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultadoAlunoCardAdapter extends RecyclerView.Adapter<ResultadoAlunoCardAdapter.ViewHolder> {
    private List<AlunoCard> items = new ArrayList<>();


    public ResultadoAlunoCardAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resutado_professor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlunoCard card = items.get(position);
        holder.textView.setText(card.getNome());
        holder.interesse.setVisibility(View.GONE);
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

    public void setItems(List<AlunoCard> items) {
        this.items = items;
        this.items.sort(Comparator.comparing(AlunoCard::getNome));
        notifyDataSetChanged();
    }
}
