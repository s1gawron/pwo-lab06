package pwo.app;

import pwo.seq.SeqType;
import pwo.utils.SequenceGenerator;
import pwo.utils.SequenceTools;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Klasa aplikacyjna służąca do zapisywania wyników do pliku<br>
 *
 * @author Sebastian
 * @version 1.0.0
 */
class SeqToFileApp {

    protected SeqType seqType = null;

    protected Integer from = null, to = null;

    protected String fileName = null;

    /**
     * Pobiera argumenty przekazane podczas uruchamiania aplikacji.<br>
     *
     * @param args Tablica argumentów
     */
    protected boolean getArgs(String[] args) {
        try {
            seqType = SeqType.fromString(args[0]);
            from = Integer.parseInt(args[1]);
            to = Integer.parseInt(args[2]);
            fileName = args[3];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return false;
        }

        try {
            Paths.get(fileName);
        } catch (InvalidPathException ex) {
            return false;
        }

        return seqType != null && from >= 0 && to >= 0;
    }

    /**
     * Wywołuje zapisanie do pliku.<br>
     * Wyrazy ciągu tworzą wiersz.
     *
     * @see #writeToFile(pwo.utils.SequenceTools, SequenceGenerator, int, int, String)
     */
    protected boolean writeSeq() {
        return SequenceTools.writeToFile(seqType.getGenerator(),
            from, to, fileName);
    }

    public void run(String[] args) {
        System.out.println("Sequence to file CLI app");

        if (!getArgs(args)) {
            System.out.println("!Illegal arguments\n" + "Legal usage: seqName from to fileName");
            return;
        }

        if (!writeSeq()) {
            System.out.println("!Write to the file: " + fileName + " FAILED");
            return;
        }

        System.out.println("Results write to " + fileName);
    }
}
