package com.classmatch.professor.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.professor.ProfessorContracts;
import com.classmatch.professor.entity.ClasseCard;
import com.google.android.material.card.MaterialCardView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClasseCardAdaptor extends RecyclerView.Adapter<ClasseCardAdaptor.ViewHolder> {

    private ArrayList<ClasseCard> lista;
    private ArrayList<ClasseCard> listaFiltrada;
    private String filterTerm = "";
    private ProfessorActivity view;
    private ProfessorContracts.Presenter presenter;

    public ClasseCardAdaptor(ArrayList<ClasseCard> lista, ProfessorActivity view, ProfessorContracts.Presenter presenter) {
        this.setItems(lista);
        this.view = view;
        this.presenter = presenter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView cardView;
        private final TextView codigoNome;
        private final TextView cursoSemestre;
        private final ImageView requisito;
        private final TextView interesse;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview_classe_aluno);
            codigoNome = view.findViewById(R.id.textview_codigo_nome);
            cursoSemestre = view.findViewById(R.id.textview_curso_semestre);
            requisito = view.findViewById(R.id.imageview_requisito);
            interesse = view.findViewById(R.id.textview_interesse);
        }

        public void bind(ClasseCard classeCard, ClasseCardAdaptor adaptor) {
            codigoNome.setText(itemView.getContext().getString(
                    R.string.classe_codigo_nome,
                    classeCard.getClasse().getCodigo(),
                    classeCard.getClasse().getNome()
            ));
            cursoSemestre.setText(itemView.getContext().getString(
                    R.string.classe_curso_semestre,
                    classeCard.getClasse().getCurso(),
                    classeCard.getClasse().getSemestre()
            ));
            requisito.setVisibility(classeCard.getClasse().isRequisito() ? View.VISIBLE : View.GONE);
            interesse.setText(Integer.toString(classeCard.getInteresse()));
            interesse.setVisibility(classeCard.isSelecionada() ? View.VISIBLE : View.INVISIBLE);
            cardView.setChecked(classeCard.isSelecionada());

            cardView.setOnClickListener(v -> {
                InteresseFragment ratingDialog = getInteresseFragment(classeCard, adaptor);
                ratingDialog.show(view.getSupportFragmentManager(), "CustomRatingDialog");
            });
        }

        @NonNull
        private InteresseFragment getInteresseFragment(ClasseCard classeCard, ClasseCardAdaptor adaptor) {
            InteresseFragment ratingDialog = new InteresseFragment(classeCard.isSelecionada() ? classeCard.getInteresse() : 0);
            ratingDialog.setOnRatingSelectedListener(rating -> {
                classeCard.setInteresse(rating);
                if (classeCard.isSelecionada()) {
                    presenter.updateNotaClasse(classeCard);
                } else {
                    presenter.selectClasse(classeCard);
                }
                classeCard.setSelecionada(true);
                view.setSelectedCount(adaptor.getSelecionadosCount(), adaptor.getTotalItemCount());
                notifyDataSetChanged();
            });
            return ratingDialog;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_classe_professor, viewGroup, false);
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

    public int getTotalItemCount() {
        return lista.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(ArrayList<ClasseCard> classeCards) {
        this.lista = classeCards;
        if (view != null) {
            view.setSelectedCount(this.getSelecionadosCount(), this.getTotalItemCount());
        }
        gridSearch(this.filterTerm);
    }

    public void setListaFiltrada(ArrayList<ClasseCard> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
        notifyDataSetChanged();
    }

    public ArrayList<ClasseCard> getItems() {return lista;}

    public int getSelecionadosCount() {
        return (int) lista.stream().filter(ClasseCard::isSelecionada).count();
    }

    public ArrayList<ClasseCard> gridSearch(String searchTerm) {
        this.filterTerm = searchTerm;
        ArrayList<ClasseCard> result = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (unAccent(lista.get(i).getClasse().getNome().toLowerCase()).contains(unAccent(this.filterTerm.toLowerCase())) ||
                unAccent(lista.get(i).getClasse().getCurso().toLowerCase()).contains(unAccent(this.filterTerm.toLowerCase())) ||
                unAccent(lista.get(i).getClasse().getCodigo().toLowerCase()).contains(unAccent(this.filterTerm.toLowerCase()))
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
}