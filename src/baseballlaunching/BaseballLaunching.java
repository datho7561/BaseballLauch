package baseballlaunching;

import java.util.Scanner;

/**
 * A program to calculate the speed needed to launch a baseball into a target
 * 10 meters away.
 * @author datho7561
 */
public class BaseballLaunching {

    // The amount the speed increases between simulations
    public static final double VELOCITY_INCREASE = 0.001;
    // The amount the time increases by during iterations of the simulation
    public static final double TIME_INCREASE = 0.001;
    
    /**
     * Finds the speed necessary to launch a baseball with the provided mass
     * and starting height at an angle of 45 degrees, making it land 40.0m
     * away.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Get input from the console
        Scanner sc = new Scanner(System.in);
        
        // Get the initial height and ball mass
        double initialH = sc.nextDouble();  // In metres above ground
        double mass = sc.nextDouble();      // In kilograms
        sc.nextLine();
        
        // Initialize the distance and launch speed
        double resultingDistance = 0;
        double launchSpeed = 0;
        
        // While the distance from the launch is less that 40 m
        while (resultingDistance < 40) {
            
            launchSpeed += VELOCITY_INCREASE;// Try a faster launch speed
            
            double h = initialH;            // Height (vertical displacement)
            double d = 0;                   // Horizontal displacement
            double angle = Math.PI/4;       // Above horizon, assume 45 degrees at launch
            double vH = launchSpeed * Math.cos(angle);
            double vV = launchSpeed * Math.sin(angle);
            
            // While the ball is above the ground
            while(h > 0) {
                
                // Calculate acceleration
                double aAir = (.5*0.4*1.23*0.00426*(vH*vH+vV*vV))/mass; // air resistance
                double aH = -aAir*Math.cos(angle);
                double aV = -aAir*Math.sin(angle)-9.8;
                
                // Calculate new velocities
                vH += aH*TIME_INCREASE;
                vV += aV*TIME_INCREASE;
                
                // Calculate horizontal and vertical displacement for this interval
                //  and add it to the current value of the displacement
                d += .5*aH*TIME_INCREASE*TIME_INCREASE + vH*TIME_INCREASE;
                h += .5*aV*TIME_INCREASE*TIME_INCREASE + vV*TIME_INCREASE;
                
                // Calculate the new angle of the ball's motion
                angle = Math.atan(vV/vH);
                
            }
            
            // Set the final horizontal displacement of the baseball to the
            // resultingDistance so that the program can determine whether or
            // not the correct speed has been found
            resultingDistance = d;
            
        }
        
        // Print out the result of the program
        System.out.println("Launch Velocity: " + launchSpeed + "m/s [45 degrees above horizontal] \n"
                + "Displacement: " + resultingDistance + "m");
        
    }
    
}
