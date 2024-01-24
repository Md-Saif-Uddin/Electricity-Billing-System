package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame{

    Splash(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/Splash.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);



        setSize(600,400);

        //this for the page location on the screen
        setLocation(500,200);

        //visible always set to be false which it not shows on screen that's y we need to fix visible true
        setVisible(true);


        try{
            //after 3 seconds page visible off
            Thread.sleep(3000);
            setVisible(false);

            //and then send to the login page
            new Login();
        }catch (Exception e){
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
