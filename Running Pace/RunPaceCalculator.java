
/**
 * Tyler Villalobos
 * 
 * Enter in either miles or kilometers, get back the conversion 
 * of mi to km or vice versa. Then, enter whether you want the pace or the speed (not implemented yet)
 * then enter hours, minutes, seconds of the run time to 
 * get back the pace per mile, and per km or speed in mph or kph.
 * 
 *Known Bugs: some small rounding errors in the pace calculations.
 */
import java.util.*;

public class RunPaceCalculator
{
    public static void main(String args[]){
        System.out.println("Running Pace Calculator");
        System.out.println("-----------------------");
        System.out.print("Enter 1 if using miles, 2 if using kilometers: ");
        Scanner keyboard = new Scanner(System.in);
        int input = keyboard.nextInt();
        double miles = 0;
        double km = 0;
        boolean usingMi = false;
        boolean usingKm = false;
        boolean wantSpeed = false;
        
        if (input == 1){
            usingMi = true;
            System.out.print("Enter how many miles: ");
            miles = keyboard.nextDouble();
            km = milesToKm(miles);
            System.out.printf("%2.2f miles = %2.2f km", miles, km);
            
        }
        else if (input == 2){
            usingKm = true;
            System.out.print("Enter how many kilometers: ");
            km = keyboard.nextDouble();
            miles = kmToMiles(km);
            System.out.printf("%2.2fmiles = %2.2fkm", km, miles);
        }
        else if(input == 3){
            usingMi = true;
            miles = 3.08;
            km = milesToKm(miles);
            System.out.printf("%2.2f miles = %2.2f km", miles, km);
        }
        System.out.println();
       // System.out.print("Enter 1 for pace, 2 for Speed");
        //input = keyboard.nextInt();
        
        //if(input == 2)
        //wantSpeed = true;
        
        System.out.print("Enter how many hours: ");
        int hours = keyboard.nextInt();
        System.out.print("Enter how many minutes: ");
        int minutes = keyboard.nextInt();
        System.out.print("Enter how many seconds: ");
        int seconds = keyboard.nextInt();
        int [] times = {hours, minutes, seconds};
        printHrMinSec(times);
        System.out.println();
        if(wantSpeed == false){
            if(usingMi == true){
                paceCalculator((timesToSeconds(hours, minutes, seconds)), miles);   
                System.out.println(" per mile");
                paceCalculator((timesToSeconds(hours, minutes, seconds)),(milesToKm(miles)));
                System.out.println(" per km");
            }
            else if(usingKm == true){
                paceCalculator((timesToSeconds(hours, minutes, seconds)), km);
                System.out.println(" per km");
                paceCalculator((timesToSeconds(hours, minutes, seconds)),(kmToMiles(km)));
                System.out.println(" per mile");
            
            }
        }
        /**else{
            if(usingMi == true){
                
            }
            else if(usingKm == true){
                
            }
        }
        */
    }
    
    public static double milesToKm(double miles){
        final double KM_PER_MILE = 1.609344;
        
        double kilometers = miles * KM_PER_MILE;
        
        return kilometers;
        
    }
    
    public static double kmToMiles(double kilometers){
        final double MILES_PER_KM = 0.6213711922;
        
        double miles = kilometers * MILES_PER_KM;
        
        return miles;
    }
    
    public static void printArrayValues(int [] arrValues){
        final int NUMBERS_PER_LINE = 5;
        int count = 1;
        for(int i: arrValues){
        System.out.printf("%5d", i);
            if (count > 0 && count % NUMBERS_PER_LINE == 0)
            System.out.println();
        count++;
        }
        System.out.println("");  
    }
    
    public static int timesToSeconds(int hours, int minutes, int seconds){
        int [] times = {(hours * 3600), (minutes * 60), seconds};
        int timeInSeconds = times[0] + times[1] + times[2];
        return timeInSeconds;
    }
     
    public static void printHrMinSec(int [] times){
        System.out.printf("Time entered was: %02d:%02d:%02d\n" , times[0], times[1], times[2]);
    }
   
    public static int [] getTimeFromSeconds(int [] times){
        int i = 0;
        int [] timesArr = new int [3];
        for (int seconds: times){
        int [] minutes = new int [times.length];
        //convert total seconds into hours
        int numberOfHours = seconds / 3600;
        //convert the total seconds into minutes
        int numberOfMinutes = seconds / 60;
        //compute seconds to display with the minutes
        int remainingSeconds = seconds % 60;
        timesArr[0] = numberOfHours;
        timesArr[1] = numberOfMinutes;
        timesArr[2] = remainingSeconds;
        i++;
        }
        return timesArr;
    }
    
    public static void paceCalculator(int timeInSeconds, double distance){
        double paceInSeconds = timeInSeconds / distance;
        int [] time = {(int) paceInSeconds};
        printPaceTime(time);

        
        
    }
    
    public static void printPaceTime(int [] times){
        int i = 0;
        String msg = "The pace is";
        for (int seconds: times){
        
        int [] minutes = new int [3];
        //convert total seconds into hours
        int numberOfHours = seconds / 3600;
        //convert the total seconds into minutes
        int numberOfMinutes = seconds / 60;
        //compute seconds to display with the minutes
        int remainingSeconds = seconds % 60;
        
        //store in new array
        minutes[0] = numberOfHours;
        minutes[1] = numberOfMinutes;
        minutes[2] = remainingSeconds;
    
        //print out the results in HR:MM:SS format
        if((minutes[0] / 10) == 0)
            msg = msg + " 0" + numberOfHours;
        else 
            msg = msg + " " + numberOfHours;
        if((minutes[1] / 10) == 0)
            msg = msg + ":0" + minutes[1];
        else
            msg = msg + ":" + minutes[1];
        if((minutes[2] / 10) == 0)
            msg = msg + ":0" + remainingSeconds;
        else
            msg = msg + ":" + remainingSeconds;
        
        i++; 
        }   
        System.out.print(msg);
    } 
    
    public static void speedCalculator(int timeInSeconds, double distance){
        double speed = distance / (timeInSeconds / 3600);
        int [] time = {(int) speed};
        printSpeedTime(time);
    }
    
    public static void printSpeedTime(int [] times){
        int i = 0;
        String msg = "The speed is";
        for (int seconds: times){
        
        int [] minutes = new int [3];
        //convert total seconds into hours
        int numberOfHours = seconds / 3600;
        //convert the total seconds into minutes
        int numberOfMinutes = seconds / 60;
        //compute seconds to display with the minutes
        int remainingSeconds = seconds % 60;
        
        //store in new array
        minutes[0] = numberOfHours;
        minutes[1] = numberOfMinutes;
        minutes[2] = remainingSeconds;
    
        //print out the results in HR:MM:SS format
        if((minutes[0] / 10) == 0)
            msg = msg + " 0" + numberOfHours;
        else 
            msg = msg + " " + numberOfHours;
        if((minutes[1] / 10) == 0)
            msg = msg + ":0" + minutes[1];
        else
            msg = msg + ":" + minutes[1];
        if((minutes[2] / 10) == 0)
            msg = msg + ":0" + remainingSeconds;
        else
            msg = msg + ":" + remainingSeconds;
        
        i++; 
        }   
        System.out.print(msg);
    } 
}
