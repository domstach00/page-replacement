package projekt;
import projekt.algorithms.*;

import java.util.ArrayList;

public class PageReplacement {
    DefProcess frames[];
    int numberOfProcesses;
    ArrayList<DefProcess> defProcessList = new ArrayList<>();

    AprokLRU aprokLRU;
    FIFO fifo;
    LRU lru;
    OPT opt;
    RAND rand;



    public PageReplacement(int pageFrames, int numberOfProcesses, int reference) {
        this.numberOfProcesses = numberOfProcesses;
        this.frames = new DefProcess[pageFrames];

        System.out.println("Liczba ramek: "+ pageFrames);
        generateProcesses(reference);

        showList();

        algFIFO();
        algOPT();
        algLRU();
        algAprokLRU();
        algRAND();

        compareAlgs();
    }

    private void compareAlgs() {
        System.out.println("\n");
        System.out.println("Porownanie algorytmow wzgledem ilosci stron ktore trzeba bylo zaladowac do ramki");
        System.out.println(fifo.getMissingPage());
        System.out.println(opt.getMissingPage());
        System.out.println(lru.getMissingPage());
        System.out.println(aprokLRU.getMissingPage());
        System.out.println(rand.getMissingPage());

    }

    private void algFIFO(){ // done
        System.out.println("\n\nAlgorytm FIFO\n");
        fifo = new FIFO(frames, defProcessList);
        System.out.println("\n============================================================================================================================");
    }

    private void algOPT(){ // done
        System.out.println("\n\nAlgorytm OPT\n");
        opt = new OPT(frames, defProcessList);
        System.out.println("\n============================================================================================================================");
    }

    private void algLRU(){ // done
        System.out.println("\n\nAlgorytm LRU\n");
        lru = new LRU(frames, defProcessList);
        System.out.println("\n============================================================================================================================");
    }

    private void algAprokLRU(){
        System.out.println("\n\nAlgorytm Aproksymowany LRU\n");
        aprokLRU = new AprokLRU(frames, defProcessList);
        System.out.println("\n============================================================================================================================");
    }

    private void algRAND(){ // done
        System.out.println("\n\nAlgorytm RAND\n");
        rand = new RAND(frames, defProcessList);
        System.out.println("\n============================================================================================================================");
    }

    private void generateProcesses(int reference) {

        for (int i = 0; i < numberOfProcesses; i++) {
            defProcessList.add(new DefProcess("Proces"+(1+i), i, reference));
        }
    }

    private void showList(){

        for (int i = 0; i < defProcessList.size(); i++) {
            System.out.println(defProcessList.get(i));
        }
    }

}
