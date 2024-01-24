package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    //here I declared JTextField and Choice is global bcoz we need to save data in backend through jdbc
    JTextField userText,passwordText;
    Choice logginChoice;
    JButton logginButton,cancelButton,signupButton;


    Login(){
        //super will always be the 1st statement of constructor this is for heading visible
        super("LOGIN");

        //setb background color
        getContentPane().setBackground(Color.PINK);



        JLabel username = new JLabel("UserName");
        username.setBounds(300,60,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);



        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400,100,150,20);
        add(passwordText);



        JLabel loggin = new JLabel("Loggin In AS");
        loggin.setBounds(300,140,100,20);
        add(loggin);

        logginChoice = new Choice();
        logginChoice.add("Admin");
        logginChoice.add("Customer");
        logginChoice.setBounds(400,140,150,20);
        add(logginChoice);

        logginButton = new JButton("Login");
        logginButton.setBounds(330,180,100,20);
        logginButton.addActionListener(this);
        add(logginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460,180,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(400,215,100,20);
        signupButton.addActionListener(this);
        add(signupButton);


        //by profileOne we fetch the image from the location and profileTwo helps to scaling the image and finally object is made fprofileOne where we address our profileTwo
        //and we need to label this bcoz we use customized layout
        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(10,5,250,250);
        add(profileLabel);



        //here we set page size and location where we want to see this page in our screen
        setSize(640,300);
        setLocation(400,200);
        //we will use my own customised layout not border layout
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logginButton){
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String suser = logginChoice.getSelectedItem();

            try{
                DataBase c = new DataBase();
                String query = "SELECT * FROM Signup WHERE username = '"+susername+"' AND password = '"+spassword+"' AND usertype = '"+suser+"'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if(resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser,meter);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Login!!");
                }
            }catch (Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == cancelButton){
            setVisible(false);
        } else if(e.getSource() == signupButton){
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

