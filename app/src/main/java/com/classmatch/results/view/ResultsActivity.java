package com.classmatch.results.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classmatch.R;
import com.classmatch.results.entity.ResultsCard;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_results_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ResultsCard> results = generateSampleData();

        AdapterResults adapter = new AdapterResults(results);
        recyclerView.setAdapter(adapter);

        filterSpinner = findViewById(R.id.spinner_filter);
        setupSpinner();
    }

    private void setupSpinner() {
        String[] filters = { "Match", "Alunos", "Professores",};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);
    }

    private List<ResultsCard> generateSampleData() {
        List<ResultsCard> results = new ArrayList<>();

        results.add(new ResultsCard("Dispositivos Móveis", "AL0253", "Ciências da Computação", 5, "25%", "85%", 30, "2° Semestre", 80, 60));
        results.add(new ResultsCard("Algoritmos", "AL0451", "Ciências da Computação", 3, "20%", "90%", 25, "1° Semestre", 85, 50));
        results.add(new ResultsCard("Inteligência Artificial", "IA0398", "Ciências da Computação", 4, "50%", "75%", 40, "4° Semestre", 90, 70));
        results.add(new ResultsCard("Banco de Dados", "BD0201", "Ciências da Computação", 6, "30%", "60%", 35, "3° Semestre", 75, 80));
        results.add(new ResultsCard("Engenharia de Software", "ES0302", "Ciências da Computação", 4, "40%", "70%", 50, "5° Semestre", 85, 65));
        results.add(new ResultsCard("Redes de Computadores", "RC0505", "Ciências da Computação", 7, "60%", "55%", 45, "6° Semestre", 88, 72));
        results.add(new ResultsCard("Programação Orientada a Objetos", "POO0101", "Ciências da Computação", 5, "45%", "80%", 60, "1° Semestre", 82, 67));
        results.add(new ResultsCard("Desenvolvimento Web", "DW0404", "Ciências da Computação", 3, "55%", "90%", 38, "3° Semestre", 92, 78));
        results.add(new ResultsCard("Sistemas Operacionais", "SO0606", "Ciências da Computação", 6, "35%", "65%", 52, "5° Semestre", 79, 73));
        results.add(new ResultsCard("Teoria da Computação", "TC0707", "Ciências da Computação", 4, "70%", "40%", 32, "7° Semestre", 65, 55));
        results.add(new ResultsCard("Computação Gráfica", "CG0808", "Ciências da Computação", 2, "80%", "95%", 29, "2° Semestre", 91, 88));
        results.add(new ResultsCard("Segurança da Informação", "SI0909", "Ciências da Computação", 4, "75%", "60%", 47, "8° Semestre", 84, 62));

        return results;
    }
}