package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private EditText campoNome;
    private EditText campoCelular;
    private EditText campoTelefone;
    private EditText campoEmail;
    private AlunoDAO alunoDAO;
    private TelefoneDAO telefoneDAO;
    private Aluno aluno;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getAlunoDAO();
        telefoneDAO = database.getTelefoneDAO();
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
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        preencheCamposDeTelefone();

    }

    private void preencheCamposDeTelefone() {
        telefonesDoAluno = telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                campoTelefone.setText(telefone.getNumero());
            } else {
                campoCelular.setText(telefone.getNumero());
            }
        }
    }


    private void finalizaFormulario() {
        preencheAluno();

        Telefone telefoneFixo = criaTelefone(campoTelefone, TipoTelefone.FIXO);
        Telefone telefoneCelular = criaTelefone(campoCelular, TipoTelefone.CELULAR);

        if (aluno.temIdValido()) {
            editaAluno(telefoneFixo, telefoneCelular);
        } else {
            salvaAluno(telefoneFixo, telefoneCelular);
        }
        finish();
    }

    private Telefone criaTelefone(EditText campoTelefone, TipoTelefone fixo) {
        String numeroFixo = campoTelefone.getText().toString();
        return new Telefone(numeroFixo, fixo);
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        Integer idAluno = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(idAluno, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(),telefoneFixo, telefoneCelular);
        atualizaIdDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
    }

    private void atualizaIdDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }

    private void vinculaAlunoComTelefone(Integer idAluno, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setIdAluno(idAluno);
        }
    }

    private void inicializacaoDosCampos() {
        campoCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        //String celular = campoCelular.getText().toString();
        String nome = campoNome.getText().toString();
        //String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        //  aluno.setTelefoneFixo(telefone);
        // aluno.setTelefoneCelular(celular);

    }

}
