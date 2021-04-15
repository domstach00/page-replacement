package projekt.algorithms;

import projekt.DefProcess;

import java.util.ArrayList;

public class RAND {
        DefProcess[] frames;
        ArrayList<DefProcess> defProcessList;
        int missingPage = 0;


    public RAND(DefProcess[] frames, ArrayList<DefProcess> defProcessList) {
        this.frames = frames;
        this.defProcessList = defProcessList;

        startRAND();
    }


    private void startRAND(){

        for (int i = 0; i < defProcessList.size(); i++) {

            doRAND(defProcessList.get(i), i);
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



    private void doRAND(DefProcess process, int index){

        // uzupelnianie pustych ramek
        for (int i = 0; i < frames.length; i++) {

            if (frames[i] == null && !checkFrames(process)){
                frames[i] = process;
                missingPage++;
                return;
            }
        }

        // sprawdzanie czy powtarza sie odwolanie
        for (int i = 0; i < frames.length; i++) {

            if (frames[i].getReferenceNumber() == process.getReferenceNumber()){

                frames[i].setReferenceQuantity( frames[i].getReferenceQuantity() + 1 );
                frames[i].setTimeRecentlyUsed(0);
                return;
            }
        }

        // zastepowanie ramki
        for (int i = 0; i < frames.length; i++) {

            int replaceNr = replecePage();
            frames[replaceNr] = defProcessList.get(index);
            missingPage++;
            return;
        }
    }


    // metoda ktora wybiera strone ktora zostanie zastapiona
        private int replecePage() {
        int nr = 2 * frames.length;

        while (nr >= frames.length) {
            nr = (int) (Math.random()*10*frames.length);
        }

        return nr;
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
        return "--> Brakujacych stron w RAND: "+missingPage;
    }
}
