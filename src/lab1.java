import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class lab1 {

    //brute force method
    public static int CountTripleSumBrute(ArrayList<Integer> list, int sum) {
        int triplets = 0;
        int len = list.toArray().length;
        len--;

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                for(int k = 0; k < len; k++) {
                    //System.out.println(list.get(i) + ", " + list.get(j) + ", " + list.get(k));
                    if(list.get(i) + list.get(j) + list.get(k) == sum) {
                        triplets++;
                    }
                }
            }
        }


        return triplets;
    }
    public static void main(String[] args) {

        ArrayList<Integer> mylist = new ArrayList<>();

        try {
            File myObj = new File("src/10int_1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                mylist.add(Integer.parseInt(data.trim()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //last element in the list will be the sum we want to get to
        int mysum = mylist.get(mylist.toArray().length-1);


        long startTimer = System.currentTimeMillis();
        int resBrute = CountTripleSumBrute(mylist, mysum);
        long endTimer = System.currentTimeMillis();
        System.out.println(resBrute);
        System.out.println(endTimer-startTimer + "ms");
    }
}