package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener {

    Choice searchMeterCho, searchNameCho;
    JTable table;
    JButton search, print, close;

    customer_details(){
        super("Customer Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);



        JLabel searchMeter = new JLabel("Search Your Meter Number");
        searchMeter.setBounds(20,20,160,20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(190,20,150,20);
        add(searchMeterCho);

        try{
            DataBase c = new DataBase();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer");
            while(resultSet.next()){
                searchMeterCho.add(resultSet.getString("meterno"));

            }

        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchName = new JLabel("Search Your Name");
        searchName.setBounds(400,20,120,20);
        add(searchName);


        searchNameCho = new Choice();
        searchNameCho.setBounds(520,20,150,20);
        add(searchNameCho);

        try{
            DataBase c = new DataBase();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer");
            while(resultSet.next()){
                searchNameCho.add(resultSet.getString("name"));

            }

        }catch (Exception E){
            E.printStackTrace();
        }

        table = new JTable();
        try{
            DataBase c = new DataBase();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer");
            //by this i set table for view and i added 1 more resultset2xml file in external file for dbUtil
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,120,700,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        JLabel textArea = new JLabel("**Select your Meter Number and Name both for details**");
        textArea.setBounds(20,50,500,20);
        add(textArea);

        search = new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setBounds(20,90,80,20);
        search.addActionListener(this);
        add(search);


        print = new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(120,90,80,20);
        print.addActionListener(this);
        add(print);


        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600,90,80,20);
        close.addActionListener(this);
        add(close);



        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            String query_search = "SELECT * FROM new_customer WHERE meterno = '"+searchMeterCho.getSelectedItem()+"' and name = '"+searchNameCho.getSelectedItem()+"'";
            try{
                DataBase c = new DataBase();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch(Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try{
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new customer_details();
    }
}

