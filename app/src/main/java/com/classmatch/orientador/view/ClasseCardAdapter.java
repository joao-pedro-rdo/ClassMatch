package com.classmatch.orientador.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.orientador.OrientadorContracts;
import com.classmatch.orientador.entity.ClasseCard;
import com.google.android.material.card.MaterialCardView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

public class ClasseCardAdapter extends RecyclerView.Adapter<ClasseCardAdapter.ViewHolder> {

    private ArrayList<ClasseCard> lista;
    private ArrayList<ClasseCard> listaFiltrada;
    private String filterTerm = "";
    private OrientadorActivity view;
    private OrientadorContracts.Presenter presenter;
    private String ordem;

    public ClasseCardAdapter(ArrayList<ClasseCard> lista, OrientadorActivity view, OrientadorContracts.Presenter presenter) {
        this.setItems(lista);
        this.view = view;
        this.ordem = view.getOrderOpcoes()[0];
        this.presenter = presenter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView cardView;
        private final TextView codigoNome;
        private final TextView cursoSemestre;
        private final ImageView requisito;
        private final TextView totalAlunos;
        private final TextView totalProfessores;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview_classe_orientador);
            codigoNome = view.findViewById(R.id.textview_codigo_nome);
            cursoSemestre = view.findViewById(R.id.textview_curso_semestre);
            requisito = view.findViewById(R.id.imageview_requisito);
            totalAlunos = view.findViewById(R.id.textview_total_aluno);
            totalProfessores = view.findViewById(R.id.textview_total_professores);
        }

        public void bind(ClasseCard classeCard, ClasseCardAdapter adaptor) {
            codigoNome.setText(itemView.getContext().getString(
                    R.string.classe_codigo_nome,
                    classeCard.getCodigo(),
                    classeCard.getNome()
            ));
            cursoSemestre.setText(itemView.getContext().getString(
                    R.string.classe_curso_semestre,
                    classeCard.getCurso(),
                    classeCard.getSemestre()
            ));
            requisito.setVisibility(classeCard.isRequisito() ? View.VISIBLE : View.GONE);
            totalAlunos.setText(Integer.toString(classeCard.getAlunos()));
            totalProfessores.setText(Integer.toString(classeCard.getProfessores()));

            cardView.setOnClickListener(v -> {
                presenter.onClasseCardClicked(classeCard);
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_classe_orientador, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(listaFiltrada.get(position), this);
    }

    @Override
    public int getItemCount() {
        return listaFiltrada.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(ArrayList<ClasseCard> classeCards) {
        this.lista = classeCards;
        gridSearch(this.filterTerm);
    }

    public void setListaFiltrada(ArrayList<ClasseCard> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
        if (view != null) {
            if (this.ordem.equals(view.getOrderOpcoes()[0])) {
                ordenarPorAlunos();
            } else {
                ordenarPorProfessores();
            }
        }
    }

    public ArrayList<ClasseCard> getItems() {return lista;}

    public ArrayList<ClasseCard> gridSearch(String searchTerm) {
        this.filterTerm = searchTerm;
        ArrayList<ClasseCard> result = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (unAccent(lista.get(i).getNome().toLowerCase()).contains(unAccent(this.filterTerm.toLowerCase())) ||
                unAccent(lista.get(i).getCurso().toLowerCase()).contains(unAccent(this.filterTerm.toLowerCase())) ||
                unAccent(lista.get(i).getCodigo().toLowerCase()).contains(unAccent(this.filterTerm.toLowerCase()))
            ) {
                result.add(lista.get(i));
            }
        }
        setListaFiltrada(result);
        return result;
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void ordenarPorAlunos() {
        listaFiltrada.sort(Comparator.comparingInt(ClasseCard::getAlunos).reversed());
        notifyDataSetChanged();
    }

    public void ordenarPorProfessores() {
        listaFiltrada.sort(Comparator.comparingInt(ClasseCard::getProfessores).reversed());
        notifyDataSetChanged();
    }
}