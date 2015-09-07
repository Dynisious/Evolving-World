package evolvingWorld;

/**
 * <p>
 * This is the most outward class, code should only run in here to initialise
 * and maybe finalise the application. Other than that this should only contain
 * an instance of the Game.</p>
 *
 * @author Dynisious 07/09/2015
 * @version 0.0.1
 */
public final class Application {
    public static boolean applicationAlive = true; //This boolean is the big
    //red button, do not change this value unless you want the application to
    //die instantly.

    /**
     * <p>
     * This initialises the application from the command line, taking String
     * arguments which we can interpret. This should just initialise everything
     * and then keep the application alive.</p>
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        while (applicationAlive) {
            try {
                Thread.currentThread().wait(3000);
            } catch (InterruptedException ex) {
                applicationAlive = false; //Get out.
            }
        } //Loop this until the application needs to get out now.
        System.exit(1); //Get out now.
    }

}
