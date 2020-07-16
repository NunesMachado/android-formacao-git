package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT t.* FROM Telefone t " +
            "WHERE t.idAluno = :id " +
            "LIMIT 1")
    Telefone buscaPrimeiroTelefoneAluno(Integer id);

    @Insert
    void salva(Telefone... telefones);
}
