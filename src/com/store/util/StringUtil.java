package com.store.util;

import java.util.regex.Pattern;

/**
 * <h1>Capítulo 14 – Strings, caracteres e expressões regulares</h1>
 * Classe utilitária para validação de SKU (código de produto) usando expressão
 * regular.
 *
 * <h2>Capítulos abordados:</h2>
 * <ul>
 * <li><b>14 – Expressões regulares:</b> compilação de um padrão
 * ({@code Pattern}) e
 * aplicação do método {@code matches()} para verificar se uma string segue o
 * formato exato "PROD-XXXX" (onde X é dígito).</li>
 * <li><b>8 – Classes e objetos:</b> membro {@code static final} – padrão
 * compilado uma
 * única vez (otimização).</li>
 * <li><b>6 – Métodos:</b> método estático público que encapsula a lógica de
 * validação.</li>
 * </ul>
 */
public class StringUtil {
    // Capítulo 14: compilação de regex; âncoras ^ e $, \d{4} para 4 dígitos
    // Capítulo 8: static final – constante de classe
    private static final Pattern SKU_PATTERN = Pattern.compile("^PROD-\\d{4}$");

    /**
     * Verifica se uma string segue o formato válido de SKU (PROD-0001, PROD-9999,
     * etc.).
     * 
     * @param sku string a ser validada
     * @return true se for um SKU válido
     *
     *         <b>Capítulo 14:</b> uso de {@code Matcher.matches()}.
     */
    public static boolean isValidSKU(String sku) {
        return SKU_PATTERN.matcher(sku).matches(); // Capítulo 14: Matcher.matches()
    }
}