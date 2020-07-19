package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT * FROM Telefone " +
            "WHERE idAluno = :id " +
            "LIMIT 1")
    Telefone buscaPrimeiroTelefoneAluno(Integer id);

    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM Telefone " +
            "WHERE idAluno = :id ")
    List<Telefone> buscaTodosTelefonesDoAluno(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
