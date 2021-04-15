package projekt.algorithms;

import projekt.DefProcess;

import java.util.ArrayList;

public class OPT {
    DefProcess[] frames;
    ArrayList<DefProcess> defProcessList;
    int[] nextReference;
    int missingPage = 0;


    public OPT(DefProcess[] frames, ArrayList<DefProcess> defProcessList) {
        this.frames = frames;
        this.defProcessList = defProcessList;

        nextReference = new int[frames.length];

        startOPT();
    }


    private void startOPT(){

        for (int i = 0; i < defProcessList.size(); i++) {

            doOPT(defProcessList.get(i), i);
            showFrames();

            // kazdy proces z ramki dostaje +1 do jednoski czasu liczonej od ostaniego odwolania
            for (int j = 0; j < frames.length; j++) {

                if (frames[j] !=null){

                    frames[j].setTimeRecentlyUsed( frames[j].getTimeRecentlyUsed() + 1);

                }
            }
        }
        System.out.println("\n---------------->\tBrakujacych stron: "+missingPage);
    }


    private void doOPT(DefProcess process, int index){

        for (int i = 0; i < frames.length; i++) {

            if (frames[i] == null && !checkFrames(process)){
                frames[i] = process;
                missingPage++;
                return;
            }
        }


        for (int i = 0; i < frames.length; i++) {

            if (frames[i].getReferenceNumber() == process.getReferenceNumber()){

                frames[i].setReferenceQuantity( frames[i].getReferenceQuantity() + 1 );
                frames[i].setTimeRecentlyUsed(0);
                return;
            }

        }


        // zastapienie strony
        int nr = replecePage(index);

        frames[nr] = defProcessList.get(index);

        missingPage++;
    }


    // metoda ktora wybiera strone ktora zostanie zastapiona
    private int replecePage(int index) {

        // ustawianie wartosci jak daleko znajduje sie kolejne odwolanie
        for (int i = 0; i < nextReference.length; i++)
            nextReference[i] = findNextReference(frames[i].getReferenceNumber(), index);



        // zwracanie wartosci najwieszkej z listy nextReference

        int max = findMax(nextReference);
        return max;

    }

    private int findNextReference(int referenceNumber, int index) {

        for (int i = index; i < defProcessList.size() ; i++) {

            if ( defProcessList.get(i).getReferenceNumber() == referenceNumber )
                return i;

        }

        return defProcessList.size();
    }


    private boolean checkFrames(DefProcess defProcess){

        for (int i = 0; i < frames.length; i++) {

            if (frames[i] == null)
                break;
            else if (frames[i].getReferenceNumber() == defProcess.getReferenceNumber())
                return true;
        }

        return false;
    }


    private static int findMax(int[] val) {
        int max = val[0];
        int index = 0;

        for (int i = 0; i < val.length; i++) {

            if (val[i] > max) {
                max = val[i];
                index = i;

            }

        }

        return index;
    }


    private void showFrames(){
        System.out.println("\nAktywne ramki:");

        for (int i = 0; i < frames.length; i++) {
            if (frames[i] != null)
                System.out.println(frames[i]);
            else
                System.out.println("Ramka jest pusta");
        }
    }


    public String getMissingPage() {
        return "--> Brakujacych stron w OPT: "+missingPage;
    }
}
