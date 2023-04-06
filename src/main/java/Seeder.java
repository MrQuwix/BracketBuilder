import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Seeder {


    public static final String FILE_PATH = "./src/main/resources/entryList.txt";
    public static final String LONG_LINE = "_____________";
    public static final String PIPE_SPACE = "|            ";
    public static final String PIPE_LINE = "|____________";
    public static final String SPACE = "             ";

    public static Queue<Deck> cDecks = new LinkedList<>();

    public static void readList(String filePath) {

        File dir = new File("");
        System.out.println(dir.getAbsolutePath());

        File mFile = new File(filePath);
        List<Deck> shuffleList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(mFile))){
            String nextLine = br.readLine();
            while(nextLine != null) {
                shuffleList.add(new Deck(nextLine.split(";")[0].trim(), nextLine.split(";")[1].trim()));
                nextLine = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.shuffle(shuffleList);
        for(Deck deck : shuffleList){
            cDecks.add(deck);
        }
    }

    public static Integer calculateSlots(){
        for(int i = 0; i < 100; i ++){
            if(cDecks.size() < (Math.pow(2, i))){
                return Math.toIntExact(Math.round(Math.pow(2, i)));
            }
        }
        return null;
    }


    public static void main(String [] args){
        readList(FILE_PATH);
        Integer slots = calculateSlots();

        System.out.println(slots);

        Deck[] finalBracket = new Deck[slots];
        if(slots%4 != 0){
            System.out.println("Ummmmm... fix it");
        }
        for(int i = 0; i < slots; i+=2) {
            finalBracket[i] = cDecks.poll();
        }
        Integer remainder = cDecks.size() % 4;
        int i = 1;
        while (finalBracket[i] == null && !cDecks.isEmpty()) {
                Deck nextOpp = cDecks.poll();
                if (nextOpp.canPlay(finalBracket[i - 1])) {
                    finalBracket[i] = nextOpp;
                } else {
                    System.out.println("There was a collision.");
                    if (cDecks.isEmpty()) {
                        System.out.println("Final match not possible pairing next opponent.");
                        i+=2;
                    }
                    cDecks.add(nextOpp);
                }
                i = (i+slots/4 + 2)%slots;

        }


        for(int j = 0; j < finalBracket.length; j++) {
//            System.out.print(j);
            String padding = "";
            Integer mod128 = j%128;
            Integer mod64 = j%64;
            if(mod128 == 0) {
                padding += LONG_LINE;
            } else if(mod128 == 64) {
                padding += PIPE_LINE;
            } else if(mod128<64){
                padding += PIPE_SPACE;
            } else {
                padding += SPACE;
            }

            if(mod64 == 0) {
                padding += LONG_LINE;
            } else if(mod64 == 32) {
                padding += PIPE_LINE;
            } else if(mod64<32){
                padding += PIPE_SPACE;
            } else {
                padding += SPACE;
            }

            //mod 64

            switch(j%32) {
                case(0):
                    padding += LONG_LINE;
                    break;
                case(1):
                case(2):
                case(3):
                case(4):
                case(5):
                case(6):
                case(7):
                case(8):
                case(9):
                case(10):
                case(11):
                case(12):
                case(13):
                case(14):
                case(15):
                    padding += PIPE_SPACE;
                    break;
                case(16):
                    padding += PIPE_LINE;
                    break;
                case(17):
                case(18):
                case(19):
                case(20):
                case(21):
                case(22):
                case(23):
                case(24):
                case(25):
                case(26):
                case(27):
                case(28):
                case(29):
                case(30):
                case(31):
                    padding += SPACE;
                    break;

            }
            switch(j%16) {
                case(0):
                    padding += LONG_LINE;
                    break;
                case(1):
                case(2):
                case(3):
                case(4):
                case(5):
                case(6):
                case(7):
                    padding += PIPE_SPACE;
                    break;
                case(8):
                    padding += PIPE_LINE;
                    break;
                case(9):
                case(10):
                case(11):
                case(12):
                case(13):
                case(14):
                case(15):
                    padding += SPACE;
                    break;
            }
            switch(j%8) {
                case(0):
                    padding += LONG_LINE;
                    break;
                case(1):
                case(2):
                case(3):
                    padding += PIPE_SPACE;
                    break;
                case(4):
                    padding += PIPE_LINE;
                    break;
                case(5):
                case(6):
                case(7):
                    padding += SPACE;
                    break;

            }

            switch(j%4){
                case(0):
                    padding += LONG_LINE;
                    break;
                case(1):
                    padding += PIPE_SPACE;
                    break;
                case(2):
                    padding += PIPE_LINE;
                    break;
                case(3):
                    padding += SPACE;
                    break;
            }
            if(j%2 == 0){
                System.out.println(padding + " ___" + finalBracket[j].getPlaneswalker() + "-" + j);
            } else {
                if(finalBracket[j] == null) {
                    System.out.println(padding + "|___BYE");
                } else {
                    System.out.println(padding + "|___" + finalBracket[j].getPlaneswalker() + "-" + j);
                }
            }
            if((j+1)%8 == 0){
               // System.out.println("                __________________");
            }
            if((j+1)%16 == 0){
                //System.out.println("         _________________________");
            }
            if((j+1)%32 == 0){
               // System.out.println("    ______________________________");
            }

        }

        //System.out.println("__________________________________");



    }
}
