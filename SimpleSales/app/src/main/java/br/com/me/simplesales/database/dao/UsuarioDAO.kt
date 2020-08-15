package br.com.me.simplesales.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.me.simplesales.model.Usuario

@Dao
interface UsuarioDAO {

    @Query("SELECT * FROM Usuario u  WHERE u.login like :login and u.senha like :senha")
    fun buscaUsuarioPorLoginESenha(login: String, senha: String): Usuario

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(usuario: Usuario)
}