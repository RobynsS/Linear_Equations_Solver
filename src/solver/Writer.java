package solver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static void readSolutionToOutput(String filepath, Complex[] solution, SolutionType solutionType) {
        File file = new File(filepath);

        try{
            FileWriter fileWriter = new FileWriter(file);

            //If there are many or no solutions: Print text
            if(solutionType == SolutionType.MANY || solutionType == SolutionType.NONE){

                fileWriter.write(solutionType.getText());

            } else if(solutionType == SolutionType.ONE) {

                //If there is one solution: Loop over solution to print all numbers on different lines
                for (int i = 0; i < solution.length; i++) {
                    fileWriter.write(solution[i].toString());

                    if (i != solution.length - 1) {
                        fileWriter.write("\r\n");
                    }
                }
            }

            fileWriter.close();
            System.out.println("Saved to file out.txt");
        }

        catch(IOException e){
            System.out.println("Output file was not found");
        }
    }
}
