package projekt.algorithms;

import projekt.DefProcess;

import java.util.ArrayList;

public class AprokLRU {
    DefProcess[] frames;
    ArrayList<DefProcess> defProcessList;
    boolean[] chanceBit;
    int missingPage = 0;


    public AprokLRU(DefProcess[] frames, ArrayList<DefProcess> defProcessList) {
        this.frames = frames;
        this.defProcessList = defProcessList;

        chanceBit = new boolean[frames.length];

        startAprokLRU();
    }


    private void startAprokLRU(){

        for (int i = 0; i < chanceBit.length; i++)
            chanceBit[i] = true;


        for (int i = 0; i < defProcessList.size(); i++) {

            doAprokLRU(defProcessList.get(i), i);
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


    private void doAprokLRU(DefProcess process, int index){

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

                chanceBit[i] = true; // ustawienie bitu na 1

                return;
            }
        }

        // jesli zawiera wartosc False przy bicie drugiej szansy to wtedy tylko wyszukujemy strony z o najwczesniejszym przybyciu do ramki
        // natomiast jesli nie ma takiego bitu o wartosci False to wszystkie bity ustawiane sa na False
        if ( includeFalseStatement() ) {
            replaceAlg(index);
        }
        else {

            for (int i = 0; i < chanceBit.length; i++) {
                chanceBit[i] = false;
            }

            replaceAlg(index);

        }
        missingPage++;
    }


    private void replaceAlg(int index){

        for (int i = 0; i < frames.length; i++) {

            int replaceNr = replecePage();
            frames[replaceNr] = defProcessList.get(index);
            chanceBit[replaceNr] = true; // ustawienie bitu drugiej szansy przy nowej stronie na wartosc True
            return;
        }


    }


    // metoda ktora wybiera strone ktora zostanie zastapiona
    private int replecePage() {
        int nr = 0;
        int min = frames[0].getArrivalTime();

        for (int i = 0; i < frames.length; i++) {

            if (min > frames[i].getArrivalTime() && chanceBit[i] == false ) {
                min = frames[i].getArrivalTime();
                nr = i;
            }
        }

        return nr;
    }


    private boolean includeFalseStatement() {

        for (int i = 0; i < chanceBit.length ; i++) {

            if (chanceBit[i] == false)
                return true;

        }

        return false;
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
        return "--> Brakujacych stron w AprokLRU: "+missingPage;
    }
}
