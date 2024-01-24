package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {


    //Globally declaring coz these need for database work
    TextField meterText, employerText, usernameText, nameText, passwordText;
    Choice loginASCho;
    JButton createButton, backButton;


    Signup(){
        //super use for put heading in page
        super("Signup page");

//        getContentPane().setBackground(new Color(168,203,255));
        getContentPane().setBackground(Color.PINK);



        JLabel createAccount = new JLabel("Create-Account");
        createAccount.setBounds(0,0,100,20);
        add(createAccount);



        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170,50,120,20);
        add(loginASCho);



        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,100,125,20);
        //visblity off coz by default this app will show admin option when we switch it customer then this meterno and
        //metertext will show or else meterNo and emplyer label is collide with eachother
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170,100,125,20);
        meterText.setVisible(false);
        add(meterText);




        JLabel employer = new JLabel("Employer ID");
        employer.setBounds(30,100,125,20);
        employer.setVisible(true);
        add(employer);

        employerText = new TextField();
        employerText.setBounds(170,100,125,20);
        employerText.setVisible(true);
        add(employerText);



        JLabel userName = new JLabel("UserName");
        userName.setBounds(30,140,125,20);
        add(userName);

        usernameText = new TextField();
        usernameText.setBounds(170,140,125,20);
        add(usernameText);



        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170,180,125,20);
        add(nameText);


        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    DataBase c = new DataBase();
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM Signup where meter_no  = '"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        JLabel password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);



        //here we fixed the issues of collides btwn employer and meterno
        loginASCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();

                if(user.equals("Customer")){
                    employer.setVisible(false);
                    nameText.setEditable(false);//this helps to not edit nameText field
                    employerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }else {
                    employer.setVisible(true);
                    nameText.setEditable(true);
                    employerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });



        createButton = new JButton("Create");
        createButton.setBackground(new Color(66,127,219));
        createButton.setForeground(Color.BLACK);
        createButton.setBounds(50,285,100,25);
        createButton.addActionListener(this);
        add(createButton);


        backButton = new JButton("Back");
        backButton.setBackground(new Color(66,127,219));
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(180,285,100,25);
        backButton.addActionListener(this);
        add(backButton);



        ImageIcon boyOne = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image boyTwo = boyOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fboyOne = new ImageIcon(boyTwo);
        JLabel boyLabel = new JLabel(fboyOne);
        boyLabel.setBounds(320,30,250,250);
        add(boyLabel);




        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton){
            String sloginAs = loginASCho.getSelectedItem();
            String susername = usernameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try{
                DataBase c = new DataBase();
                String query = null;
                if(loginASCho.equals("Admin")){
                    query = "insert into Signup value('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+sloginAs+"')";
                }else{
                    query = "UPDATE Signup SET username = '"+susername+"', password = '"+spassword+"', usertype = '"+sloginAs+"' WHERE meter_no = '"+smeter+"'";
                }

//                query = "insert into Signup value('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+sloginAs+"')";


                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new Login();

            }catch (Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == backButton){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
