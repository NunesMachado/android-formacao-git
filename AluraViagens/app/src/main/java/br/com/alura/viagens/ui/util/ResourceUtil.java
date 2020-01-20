package br.com.alura.viagens.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    public static final String DRAWABLE = "drawable";

    public static Drawable devolveDrawable(Context contexto, String drawableEmTexto) {
        Resources resources = contexto.getResources();
        int idDoDrawable = resources.getIdentifier(drawableEmTexto, DRAWABLE, contexto.getPackageName());
        return resources.getDrawable(idDoDrawable);
    }
}
