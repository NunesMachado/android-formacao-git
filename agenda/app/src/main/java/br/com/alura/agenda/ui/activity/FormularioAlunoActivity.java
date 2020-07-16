package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO =  "Novo Aluno";
    private EditText campoNome;
    private EditText campoCelular;
    private EditText campoTelefone;
    private EditText campoEmail;
    private AlunoDAO alunoDAO;
    private TelefoneDAO telefoneDAO;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getAlunoDAO();
        telefoneDAO= database.getTelefoneDAO();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)) {
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            preencheCampos();
        }else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
       // campoCelular.setText(aluno.getTelefoneCelular());
        campoNome.setText(aluno.getNome());
      //  campoTelefone.setText(aluno.getTelefoneFixo());
        campoEmail.setText(aluno.getEmail());
    }


    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            alunoDAO.edita(aluno);
        }else{
            Integer idAluno = alunoDAO.salva(aluno).intValue();
            String numeroFixo = campoTelefone.getText().toString();
            Telefone telefoneFixo = new Telefone(numeroFixo, TipoTelefone.FIXO, idAluno);
            String numeroCelular = campoCelular.getText().toString();
            Telefone telefoneCeluar = new Telefone(numeroCelular, TipoTelefone.CELULAR, idAluno);
            telefoneDAO.salva(telefoneFixo, telefoneCeluar);


        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        //String celular = campoCelular.getText().toString();
        String nome =  campoNome.getText().toString();
        //String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
      //  aluno.setTelefoneFixo(telefone);
       // aluno.setTelefoneCelular(celular);

    }

}
