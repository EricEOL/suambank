package br.com.ericeol.suambank.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class RealFormatNumber {
    public static String format(Double number) {
        Locale locale = new Locale("pt", "BR");
        Currency currency = Currency.getInstance(locale);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(number);
    }
}
