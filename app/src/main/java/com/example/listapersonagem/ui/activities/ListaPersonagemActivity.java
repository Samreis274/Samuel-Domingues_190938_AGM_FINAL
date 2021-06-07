package com.example.listapersonagem.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listapersonagem.ui.activities.ContantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    //cria o titulo
    public static final String TITULO_APPBAR = "Lista de Personagem";
    //cria uma instencao de dao
    private final PersonagemDAO dao = new PersonagemDAO();
    //cria o adapter
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //seta o titulo
        setTitle(TITULO_APPBAR);
        //chama o metodo do botao do novo personagem
        ConfiguraFabNovoPersonagem();
        //chama o metodo da configuracao da lista
        ConfiguraLista();

    }

    private void ConfiguraFabNovoPersonagem() {
        //configuracao do botao para novo personagem
        FloatingActionButton botaonovopersonagem = findViewById(R.id.fab_add);
        botaonovopersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            //chama o metodo abreformulario
            public void onClick(View v) {
                AbreFormulario();
            }
        });
    }

    private void AbreFormulario() {
        //chama a activity do formulario
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }
    @Override
    protected void onResume() {
        super.onResume();
        atualizaAdapter();

    }

    private void atualizaAdapter() {
        //atualiza as informaçoes do adapter
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    @Override
    //cria o menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu,menu);
    }

    @Override
    //chama o menu quando segurar no personagem
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return configuraMenu(item);

    }

    private boolean configuraMenu(@NonNull MenuItem item) {
        //varial itemId pega a id do item
        int itemId = item.getItemId();
        //verifica o id do item se for igual o idRemover
        if(itemId == R.id.activity_lista_personagem_menu_remover) {
            //chama um alerta
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
                    //coloca positivo para a palavra sim
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //apaga o personagem escolhido
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    //coloca negativo para a palavra nao
                    .setNegativeButton("Não", null)
                    .show();
            }
        //retorna o onContextItemSelected
        return super.onContextItemSelected(item);
    }
    private void remove(Personagem personagem){
        //chama o metodo remove de dao
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    private void ConfiguraLista() {
        //pega o id da lista
        ListView listaDePersonagem = findViewById(R.id.activity_main_lista_personagem);

        ListaDePersonagem(listaDePersonagem);
        configuraItemPorClique(listaDePersonagem);
        registerForContextMenu(listaDePersonagem);
    }


    private void configuraItemPorClique(ListView listaDePersonagem) {
        listaDePersonagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                //pega posicao do item escolhido
                Personagem personagemEscolhido = (Personagem) parent.getItemAtPosition(posicao);
                //chama o metodo abreformularioeditar para editar o personagem
                AbreFormularioEditar(personagemEscolhido);
            }
        });
    }

    private void AbreFormularioEditar(Personagem personagemEscolhido) {
        //chama o formilariopersonagem
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        //vai para o formulario
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        //incia a activity formulario
        startActivity(vaiParaFormulario);
    }

    private void ListaDePersonagem(ListView listaDePersonagem) {
        //cria um array adapter para guardar as informaçoes na lista
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagem.setAdapter(adapter);
    }




















}
