package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    static double[][] readElementsFromInput(String filePath) throws ReaderException{
        File file = new File(filePath);
        int N;
        double[][] elements;

        try{
            //Try to create Scanner object with the given file
            Scanner scanner = new Scanner(file);

            //Get amount of variables N and initialize elements array
            N =  Integer.parseInt(scanner.nextLine());
            elements = new double[N][N+1];

            //Get each row of elements
            int rowCounter = 0;
            while(scanner.hasNextLine()){
                String rowString = scanner.nextLine();

                //Split the input in different parts
                String[] rowStringSplit = rowString.split(" ");

                //Parse different numbers to ints
                int columnCounter = 0;
                for (String s: rowStringSplit){
                    elements[rowCounter][columnCounter] = Double.parseDouble(s);
                    columnCounter++;
                }

                //Validate if amount of columns align with N
                if(columnCounter != N+1){
                    throw new ReaderException("Input file did not contain the correct data structure");
                }
                rowCounter++;
            }

            //Validate if dimensions of input structure align with N
            if(rowCounter != N){
                throw new ReaderException("Input file did not contain the correct data structure");
            }

            //Close scanner
            scanner.close();

            //Return input elements
            return elements;

        }
        catch(FileNotFoundException e){
            throw new ReaderException("Input file was not found");
        }
        catch(IndexOutOfBoundsException e){
            throw new ReaderException("Input file did not contain the correct data structure");
        }

    }
}

class ReaderException extends RuntimeException{

    private String errorMsg;

    ReaderException(String msg){
        this.errorMsg = msg;
    }

    public String toString(){
        return "ReaderException: Reader was not able to read the " +
                "input file correctly due to the following error:\n" +
                this.errorMsg;
    }
}