package com.example.listapersonagem.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.io.Serializable;

import static com.example.listapersonagem.ui.activities.ContantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Formulário Personagem";
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText camponome;
    private EditText campoaltura;
    private EditText camponasc;
    private EditText campotelefone;
    private EditText campoendereco;
    private EditText campocep;
    private EditText camporg;
    private EditText campogenero;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //varial itemId pega a id do item
        int itemId = item.getItemId();
        //verifica o id do item se for igual o idsalvar
        if(itemId == R.id.activity_formulario_personagem_menu_salvar){
            //chama metodo finalizarformulario
            finalizarFormulario();
        }
        //retorna o onOptionsItemSelected
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        setTitle(TITULO_APPBAR);
        //chama o metodo inicializarcampos
        inicializacaoCampos();
        //chama o metodo configurarbotaosalvar
        ConfiguraBotaoSalvar();
        //chama o metodo carregarpersonagem
        carregaPersonagem();
    }
    private void carregaPersonagem() {

        Intent dados =getIntent();
        //verifica se o personagem ja existir ele abre e poxa as informacoes e joga no campo para editar
        if(dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        }
        //se o personagem for novo ele muda o title da pagina e salva
        else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        //coloca os dados do personagem nos campos para editar
        camponome.setText(personagem.getNome());
        campoaltura.setText(personagem.getAltura());
        camponasc.setText(personagem.getNascimento());
        campotelefone.setText(personagem.getTelefone());
        campoendereco.setText(personagem.getEndereco());
        campocep.setText(personagem.getCEP());
        camporg.setText(personagem.getRG());
        campogenero.setText(personagem.getGenero());
    }

    private void ConfiguraBotaoSalvar() {
        //configuracao para configurar o botao salvar
        Button botaosalvar = findViewById(R.id.button_salvar);
        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            //chama o metodo finalizar o formulario quando botao salvar pressionado
            public void onClick(View view) {
                finalizarFormulario();
            }
        });
    }

    private void finalizarFormulario() {
        preencherPersonagem();

        //Vereficaçao para salvar ou editar personagem
        if(personagem.IdValido()){
            dao.Editar(personagem);
            finish();
        }else {
            dao.salva(personagem);
        }
        finish();
    }

    private void inicializacaoCampos() {
        //pega oq foi digitado em cada campo
        camponome = findViewById(R.id.editText_nome);
        campoaltura = findViewById(R.id.editText_altura);
        camponasc = findViewById(R.id.editText_nascimento);
        campotelefone = findViewById(R.id.editText_telefone);
        campoendereco = findViewById(R.id.editText_endereco);
        campocep = findViewById(R.id.editText_cep);
        camporg = findViewById(R.id.editText_rg);
        campogenero = findViewById(R.id.editText_genero);


        //formataçao para deixar o campo altura no formato desejado automaticamente
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoaltura, smfAltura);
        campoaltura.addTextChangedListener(mtwAltura);

        //formataçao para deixar o campo nascimento no formato desejado automaticamente
        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(camponasc, smfNascimento);
        camponasc.addTextChangedListener(mtwNascimento);

        SimpleMaskFormatter smftelefone = new SimpleMaskFormatter("NN NNNNN-NNNN");
        MaskTextWatcher mtwtefone = new MaskTextWatcher(campotelefone, smftelefone);
        campotelefone.addTextChangedListener(mtwtefone);

        SimpleMaskFormatter smfcep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwcep = new MaskTextWatcher(campocep, smfcep);
        campocep.addTextChangedListener(mtwcep);

        SimpleMaskFormatter smfrg = new SimpleMaskFormatter("NN.NNN.NNN-N");
        MaskTextWatcher mtwrg = new MaskTextWatcher(camporg, smfrg);
        camporg.addTextChangedListener(mtwrg);

    }



    private void preencherPersonagem(){
        //pega as informacoes de campos
        String nome = camponome.getText().toString();
        String altura = campoaltura.getText().toString();
        String nascimento = camponasc.getText().toString();
        String telefone = campotelefone.getText().toString();
        String endereco = campoendereco.getText().toString();
        String cep = campocep.getText().toString();
        String rg = camporg.getText().toString();
        String genero = campogenero.getText().toString();

        //seta as informaoes do personagem para salvar
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setTelefone(telefone);
        personagem.setEndereco(endereco);
        personagem.setCEP(cep);
        personagem.setRG(rg);
        personagem.setGenero(genero);


    }


}