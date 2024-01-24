package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {

    JLabel heading, meternum, name, nameText, address, addressText, unitconsumed, month;
    TextField unitText;
    Choice meternumCho, monthCho;
    JButton submit, cancel;

    calculate_bill(){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahume",Font.BOLD,20));
        panel.add(heading);

        meternum = new JLabel("Meter Number");
        meternum.setBounds(50,80,100,20);
        panel.add(meternum);

        meternumCho = new Choice();
        try{
            DataBase c = new DataBase();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer");
            while (resultSet.next()){
                meternumCho.add(resultSet.getString("meterno"));

            }
        }catch (Exception E){
            E.printStackTrace();
        }

        meternumCho.setBounds(180,80,100,20);
        panel.add(meternumCho);


        name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180,120,150,20);
        panel.add(nameText);

        address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180,160,150,20);
        panel.add(addressText);


        //we r putting it twice first in normal try catch and 2nd time in itemListener override code
        //coz 1st for showing all details properly
        //2nd for change details acc to the meternumCho
        try{
            DataBase c = new DataBase();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer WHERE meterno = '"+meternumCho.getSelectedItem()+"'");
            while (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }
        meternumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    DataBase c = new DataBase();
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer WHERE meterno = '"+meternumCho.getSelectedItem()+"'");
                    while (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(50,200,100,20);
        panel.add(unitconsumed);

        unitText = new TextField();
        unitText.setBounds(180,200,150,20);
        panel.add(unitText);

        month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,240,150,20);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);


        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel,"East");



        setSize(650,400);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String smeterno = meternumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();

            int totalBill = 0;
            int unit = Integer.parseInt(sunit);
            String query_tax = "SELECT * FROM tax";
            try{
                DataBase c = new DataBase();
                ResultSet resultSet = c.statement.executeQuery(query_tax);

                while(resultSet.next()){
                    totalBill += unit * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));

                }

            }catch(Exception E){
                E.printStackTrace();
            }
            String query_total_bill = "INSERT INTO bill VALUES('"+smeterno+"','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";
            try{
                DataBase c = new DataBase();
                c.statement.executeUpdate(query_total_bill);

                //code runs line by line if data is not updated in sql then this jpaneoption page not seen
                JOptionPane.showMessageDialog(null, "Customer Bill Successfully Updated!!!");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }

        }else{
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}

