package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.model.TipoTelefone.FIXO;

public class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
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
    };

    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `Aluno_Novo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)");

            database.execSQL("INSERT INTO Aluno_Novo (id, nome, email, momentoDeCadastro)" +
                    "SELECT ID, NOME, EMAIL, momentoDeCadastro FROM Aluno");

            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`idAluno` INTEGER)");

            database.execSQL("INSERT INTO Telefone (numero, idAluno)" +
                    "SELECT telefone, id FROM Aluno");

            database.execSQL("UPDATE Telefone SET tipo= ?", new TipoTelefone[]{FIXO});

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_Novo RENAME TO Aluno");

        }
    } ;

    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}
