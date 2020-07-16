package br.com.alura.agenda.database.conversor;

import androidx.room.TypeConverter;

import br.com.alura.agenda.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String paraString(TipoTelefone tipoTelefone){
        return  tipoTelefone.name().toString();
    }

    @TypeConverter
    public TipoTelefone paraEnum(String valor){
        if(valor!= null){
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
