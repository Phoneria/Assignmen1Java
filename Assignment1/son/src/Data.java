import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {
    public static String[] readFile (String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();

            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))){
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }// Read files
    public static ArrayList<List<String>> collectAll(String path, int n){
        ArrayList<List<String>> peopleDictionary = new ArrayList<List<String>>();
        ArrayList<String> peopleList = new ArrayList<String>();
        String[] people = readFile(path);
        ArrayList<String>  variableList = new ArrayList<>();
        ArrayList<String>  IDList = new ArrayList<>();// It holds ID's
        ArrayList<String>  dataList = new ArrayList<>();
        ArrayList<List<String>> variableGroup = new ArrayList<>();// It holds variables of ID


        for(String s: people){
            peopleList.add(s);
        }//Add elements of people array to peoplist Arraylist
        for( String s :peopleList){
            String[] eachPeople = s.split("\t");
            for( String j : eachPeople){
                dataList.add(j);
            }

        }// Delete tabs from arraylist
        for(int i = 0; i < dataList.size();i++){
            if ( i%n == 0) {
                IDList.add(dataList.get(i));
            }
            else {
                variableList.add(dataList.get(i));
            }
        }// Take ID's and variables in couple Arraylist
        for(int i = 0;i<variableList.size()/(n-1);i++){

            variableGroup.add(variableList.subList((n-1)*i,(n-1)*(i+1)));

        }// Take variables in matrix
        for(int i = 0; i< IDList.size(); i++){
            peopleDictionary.add(Collections.singletonList(IDList.get(i)));
            peopleDictionary.add(variableGroup.get(i));
        }
        return peopleDictionary;
    }// Enter name of file and how many variables in each line.

    private ArrayList<List<String>>  dataList ;
    private ArrayList<List<String>> dataVariables;
    private ArrayList<String> dataID;

    public Data(String file , int numOfVariable) {
        this.dataList = collectAll(file,numOfVariable);;
        this.dataVariables =  new ArrayList<List<String>>();;
        this.dataID = new ArrayList<String>();


        for(int i = 0;i< dataList.size();i++) {
            if (i % 2 != 0) {
                dataVariables.add(dataList.get(i));


            } else {
                dataID.add(dataList.get(i).get(0));
            }
        }
    }
    public ArrayList<List<String>> getdataVariables() {
        return dataVariables;
    }
    public ArrayList<String> getdataID() {
        return dataID;
    }

}

