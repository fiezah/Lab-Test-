/**
 * Program description : to construct a Java program to create employee salary data
 * Programmer : fezy
 * Date : 14 March 2024
 */

import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class EmployeeSalary
{
    public static void main(String[]args) throws IOException
    {
        DecimalFormat dF = new DecimalFormat("0.00");
        try
        {
            //input file
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));
            //2 output files
            PrintWriter fileoutput1 = new PrintWriter(new FileWriter("employeeData.txt"));

            //Declare the variables
            String inputData = null;
            String employeename = "";
            double employeeSalary = 0.00;
            int employeeWorkingYear = 0;

            //variable for top performing employee
            String top_employeename = "";
            double top_employeeSalary = 0.00;
            int top_employeeWorkingYear = 0;

            //variable for newest employee
            String latest_employeename = "";
            double latest_employeeSalary = 0.00;
            int latest_employeeWorkingYear = 0;

            //Write the title header
            fileoutput1.println("*-*-*-*-*-*- list of employees -*-*-*-*-*-*");

            while((inputData = inputFile.readLine()) != null)
            {
                StringTokenizer strT = new StringTokenizer(inputData,"|");
                employeename= strT.nextToken();
                employeeSalary = Double.parseDouble(strT.nextToken());
                employeeWorkingYear = Integer.parseInt(strT.nextToken());

                double annualSalary = employeeSalary + (employeeSalary * 0.05);
                //to test for the negative number
                if(employeeSalary < 0 || employeeWorkingYear <0)
                    throw new IllegalArgumentException();

                //find top performing employee
                if(annualSalary > top_employeeSalary){
                    top_employeename = employeename;
                    top_employeeSalary = annualSalary;
                    top_employeeWorkingYear = employeeWorkingYear;
                }
                //find the employee with the least years of service
                if(latest_employeeWorkingYear == 0 || employeeWorkingYear < latest_employeeWorkingYear){
                    latest_employeename = employeename;
                    latest_employeeSalary = annualSalary;
                    latest_employeeWorkingYear = employeeWorkingYear;

                }

                //store list of employees
                String employeeData = employeename+"\t\t RM "+employeeSalary+"\t\t "+employeeWorkingYear+" years";
                fileoutput1.println(employeeData);

            }
            //top performing employee
            fileoutput1.println("\n\n *-*-*-*- ⭑⚝ details of top-performing employee !! -*-*-*-*");
            String top_employeeData = top_employeename+"\t\t RM "+top_employeeSalary+"\t\t "+top_employeeWorkingYear+" years";
            fileoutput1.println(top_employeeData);
            //display top performing employee
            JOptionPane.showMessageDialog(null,"*-*-*-*- ⭑⚝ details of top-performing employee !! -*-*-*-*\n"+top_employeeData);

            //latest employee
            fileoutput1.println("\n\n *-*-*-*- ❀ details of employee with the least years of service ❀ -*-*-*-*");
            String latest_employeeData = latest_employeename+"\t\t RM "+latest_employeeSalary+"\t\t "+latest_employeeWorkingYear+" years";
            fileoutput1.println(latest_employeeData);
            JOptionPane.showMessageDialog(null,"*-*-*-* ❀ details of employee with the least years of service ❀ -*-*-*-* \n"+latest_employeeData);

            //close all files
            inputFile.close();
            fileoutput1.close();

        }
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input!";
            JOptionPane.showMessageDialog(null, output);
        }
    }
}
