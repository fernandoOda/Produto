package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.evento.modelo.Evento;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

public class CadastroEvento extends AppCompatActivity {

    private final int resultCodeNovoEvento = 10;
    private final int resultCodeEventotoEditado = 11;
    private final int resultCodeEventoExcluido = 12;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Eventos");
        carregarEvento();
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventotoSelecionado") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventotoSelecionado");
            EditText editTextNome = findViewById(R.id.editTextNome);
            EditText editTextData = findViewById(R.id.editTextData);
            EditText editTextLocal = findViewById(R.id.editTextLocal);
            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            editTextLocal.setText(evento.getLocal());
            edicao = true;
            id = evento.getId();
        }
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextData = findViewById(R.id.editTextData);
        EditText editTextLocal = findViewById(R.id.editTextLocal);

        String nome = editTextNome.getText().toString();
        String data =  editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        Intent intent = new Intent();
        if (edicao) {
            intent.putExtra("eventoEditado", evento);
            setResult(resultCodeEventotoEditado, intent);
        } else {
            intent.putExtra("novoEvento", evento);
            setResult(resultCodeNovoEvento, intent);
        }
        finish();
    }

    public void onClickRemove(View v) {
        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextData = findViewById(R.id.editTextData);
        EditText editTextLocal = findViewById(R.id.editTextLocal);

        String nome = editTextNome.getText().toString();
        String data =  editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        Intent intent = new Intent();
        intent.putExtra("eventoExcluido", evento);
        setResult(resultCodeEventoExcluido, intent);
        finish();
    }

}