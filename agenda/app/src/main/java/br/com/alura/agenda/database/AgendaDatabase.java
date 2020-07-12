package br.com.alura.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                    }
                }, new Migration(2, 3) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        //cria uma nova tabela com as informações
                        database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                                "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                " `nome` TEXT, " +
                                "`telefone` TEXT, " +
                                "`email` TEXT)");

                        //Copiar os dados da tabela antiga para a nova
                        database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                                "SELECT ID, NOME, TELEFONE, EMAIL FROM aluno");
                        //Remove a tabela antiga
                        database.execSQL("DROP TABLE ALUNO");
                        //Renomear a tabela nova com nome da antiga
                        database.execSQL("ALTER TABLE Aluno_novo RENAME TO aluno");

                    }
                })
                .build();
    }
}
