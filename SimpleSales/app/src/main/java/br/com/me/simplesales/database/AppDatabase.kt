package br.com.me.simplesales.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.me.simplesales.database.dao.PedidoDAO
import br.com.me.simplesales.database.dao.UsuarioDAO
import br.com.me.simplesales.model.Pedido
import br.com.me.simplesales.model.Usuario

private const val NOME_BANCO_DE_DADOS = "simplesales.db"

@Database(entities = [Usuario::class, Pedido::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val usuarioDAO: UsuarioDAO
    abstract val pedidoDAO: PedidoDAO

    companion object {
        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {

            if (::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            )
              // .fallbackToDestructiveMigration()
               // .allowMainThreadQueries()
                .build()

            return db
        }

    }

}