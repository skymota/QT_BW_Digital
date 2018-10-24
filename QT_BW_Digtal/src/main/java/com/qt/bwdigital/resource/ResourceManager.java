package com.qt.bwdigital.resource;

import android.content.res.Resources;
import android.graphics.Typeface;
import java.util.EnumMap;
import java.util.Map;

public class ResourceManager {
    private static Map<Font, Typeface> TYPE_FACES = new EnumMap(Font.class);

    public enum Font {
        CLOCK_FONT("fonts/CasanovaScotia.otf"),
        TEXT_FONT("fonts/CasanovaScotia.otf"),
        DATE_FONT("fonts/CasanovaScotia.otf");
        
        private final String path;

        private Font(String str) {
            this.path = str;
        }
    }

    public static Typeface getTypeFace(Resources resources, Font font) {
        Typeface typeface = (Typeface) TYPE_FACES.get(font);
        if (typeface != null) {
            return typeface;
        }
        typeface = Typeface.createFromAsset(resources.getAssets(), font.path);
        TYPE_FACES.put(font, typeface);
        return typeface;
    }
}
