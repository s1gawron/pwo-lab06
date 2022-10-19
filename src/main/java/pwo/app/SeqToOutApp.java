package pwo.app;

import pwo.utils.SequenceTools;

/**
 * Klasa aplikacyjna służąca do wyświetlania wyników w konsoli<br>
 *
 * @author Sebastian
 * @version 1.0.0
 */
public class SeqToOutApp extends SeqToFileApp {

    /**
     * Pobiera argumenty przekazane podczas uruchamiania aplikacji.<br>
     *
     * @param args Tablica argumentów
     */
    @Override protected boolean getArgs(final String[] args) {
        if (super.getArgs(args)) {
            return true;
        }

        return seqType != null && from >= 0 && to >= 0;
    }

    /**
     * Wywołuje wyświetlenie wyniku w konsoli.<br>
     *
     */
    @Override protected boolean writeSeq() {
        System.out.println(SequenceTools.getTermsAsColumn(seqType.getGenerator(), from, to));

        return true;
    }

    @Override public void run(final String[] args) {
        System.out.println("Sequence to terminal CLI app");

        if (!getArgs(args)) {
            System.out.println("!Illegal arguments\n" + "Legal usage: seqName from to");
            return;
        }

        writeSeq();
    }
}
