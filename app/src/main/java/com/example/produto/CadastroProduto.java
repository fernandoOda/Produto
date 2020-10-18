package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.produto.modelo.Produto;

public class CadastroProduto extends AppCompatActivity {

    private final int resultCodeNovoProduto = 10;
    private final int resultCodeProdutoEditado = 11;
    private final int resultCodeProdutoExcluido = 12;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadastro de Produtos");
        carregarProduto();

    }

    private void carregarProduto() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("produtoSelecionado") != null) {
            Produto produto = (Produto) intent.getExtras().get("produtoSelecionado");
            EditText editTextNome = findViewById(R.id.editTextNome);
            EditText editTextValor = findViewById(R.id.editTextValor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            edicao = true;
            id = produto.getId();
        }
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextValor = findViewById(R.id.editTextValor);

        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id, nome, valor);
        Intent intent = new Intent();
        if (edicao) {
            intent.putExtra("produtoEditado", produto);
            setResult(resultCodeProdutoEditado, intent);
        } else {
            intent.putExtra("novoProduto", produto);
            setResult(resultCodeNovoProduto, intent);
        }
        finish();
    }

    public void onClickRemove(View v) {
        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextValor = findViewById(R.id.editTextValor);

        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id, nome, valor);
        Intent intent = new Intent();
        intent.putExtra("produtoExcluido", produto);
        setResult(resultCodeProdutoExcluido, intent);
        finish();
    }

}