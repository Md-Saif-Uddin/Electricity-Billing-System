package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {

    JLabel heading, customerName, meterNum, meterNumText, phone, email, address, city, state;
    TextField nameText, phoneText, emailText, addressText, cityText, stateText;
    JButton next, cancel;


    newCustomer(){
        super("New Customer");


        //here we are using panel coz in this page we are splitting page into two part 1st left side is for image and right side for page form
        JPanel panel = new JPanel();
        //and set layout null and modifying it as per our needs
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);



        heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);




        customerName = new JLabel("New Customer");
        customerName.setBounds(50,80,100,20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180,80,150,20);
        panel.add(nameText);




        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,120,100,20);
        panel.add(meterNum);

        meterNumText = new JLabel("");
        meterNumText.setBounds(180,120,150,20);
        panel.add(meterNumText);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterNumText.setText("" + Math.abs(number));



        phone = new JLabel("Phone");
        phone.setBounds(50,160,100,20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180,160,150,20);
        panel.add(phoneText);




        email = new JLabel("Email");
        email.setBounds(50,200,100,20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180,200,150,20);
        panel.add(emailText);



        address = new JLabel("Address");
        address.setBounds(50,240,100,20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180,240,150,20);
        panel.add(addressText);



        city = new JLabel("City");
        city.setBounds(50,280,100,20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180,280,150,20);
        panel.add(cityText);



        state = new JLabel("State");
        state.setBounds(50,320,100,20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180,320,150,20);
        panel.add(stateText);



        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230,390,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);



        setLayout(new BorderLayout());
        //here we add panel in frame coz in this page we are using frame default (in other page like login page we are setting layout null)
        add(panel, "Center");




        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boys.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel,"West");

        setSize(700,500);
        setLocation(350,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == next){
            String sname = nameText.getText();
            String smeter = meterNumText.getText();
            String sphone = phoneText.getText();
            String eemail = emailText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();


            String query_customer = "INSERT INTO new_customer VALUES ('"+sname+"','"+smeter+"','"+sphone+"','"+eemail+"','"+saddress+"','"+scity+"','"+sstate+"')";
            String query_signup = "INSERT INTO signup VALUES ('"+smeter+"','','"+sname+"','','')";

            try{
                DataBase c = new DataBase();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null, "Customer details added successfully!!!");
                setVisible(false);
                new meterInfo(smeter);

            }catch (Exception E){
                E.printStackTrace();
            }

        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
