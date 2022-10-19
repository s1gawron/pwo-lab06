package pwo.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Klasa narzędziowa służąca do obsługi obiektów<br>
 * implementujących interfejs {@link SequenceGenerator}
 *
 * @author Sebastian
 * @version 1.0.0
 */
public class SequenceTools {

    private static String getTerms(SequenceGenerator sg, int from, int to, String sep) {
        int i = from, step = from > to ? -1 : 1;
        StringBuilder terms = new StringBuilder();

        while (true) {
            terms.append(sg.getTerm(i)).append(sep);

            if (i == to) {
                return terms.toString().trim();
            }

            i += step;
        }
    }

    /**
     * Zamienia wyrazy ciągu na tekst.<br>
     * Wyrazy ciągu tworzą kolumnę.
     *
     * @param sg   Dowolny obiekt implementujący {@link SequenceGenerator}
     * @param from Początkowy wyraz ciągu
     * @param to   Końcowy wyraz ciągu
     * @return Wyrazy ciągu w postaci tekstu tworzącego kolumnę
     * @see #getTermsAsLine(pwo.utils.SequenceGenerator, int, int)
     */
    public static String getTermsAsColumn(SequenceGenerator sg, int from, int to) {
        return getTerms(sg, from, to, "\n");
    }

    /**
     * Zamiania wyrazy ciągu na tekst.<br>
     * Wyrazy ciągu tworzą wiersz.
     *
     * @param sg   Dowolny obiekt implementujący {@link SequenceGenerator}
     * @param from Początkowy wyraz ciągu
     * @param to   Końcowy wyraz ciągu
     * @return Wyrazy ciągu w postaci tekstu tworzącego wiersz
     * @see #getTermsAsColumn(pwo.utils.SequenceGenerator, int, int)
     */
    public static String getTermsAsLine(SequenceGenerator sg, int from, int to) {
        return getTerms(sg, from, to, " ");
    }

    /**
     * Zapisuje wyrazy ciągu do pliku w postaci kolumny.
     *
     * @param sg       Dowolny obiekt implementujący {@link SequenceGenerator}
     * @param from     Początkowy wyraz ciągu
     * @param to       Końcowy wyraz ciągu
     * @param fileName Nazwa pliku
     * @return true jeżeli udało się zapisać plik,
     * false w przeciwnym wypadku
     */
    public static boolean writeToFile(SequenceGenerator sg, int from, int to, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(getTermsAsColumn(sg, from, to));
        } catch (IOException ex) {
            return false;
        }

        return true;
    }
}
