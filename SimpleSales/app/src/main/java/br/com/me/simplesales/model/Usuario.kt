package br.com.me.simplesales.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,
    val login: String?,
    val senha: String?
) : Parcelable {
    constructor(parcel: Parcel) :this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(dest: Parcel?, flags: Int) {
       dest?.writeLong(id)
       dest?.writeString(login)
       dest?.writeString(senha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}