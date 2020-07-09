
package desafio.backend.app;

import desafio.backend.controller.AppController;

/**
 *
 * @author marcos
 * @since 07/07/2020
 */
public class App {
    
    public static String DATA_IN_PATH;
    public static String DATA_OUT_PATH;

    public static void main(String[] args) {
        App app = new App();
    }
    
    public App() {
        String HOME_PATH = System.getProperty("user.home");
        DATA_IN_PATH = HOME_PATH + "/data/in";
        DATA_OUT_PATH = HOME_PATH + "/data/out";
        
        AppController controller = new AppController();
        controller.init();
    }
}
