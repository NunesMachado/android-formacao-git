package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};
}
