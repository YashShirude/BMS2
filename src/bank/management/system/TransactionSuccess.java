package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionSuccess extends JFrame implements ActionListener {
    String pin, accNo;
    JButton backButton;
    TransactionSuccess(){
        accNo = SqlQuery.accNo;

        SqlQuery query9 = new SqlQuery();
        pin = query9.pin;

        setTitle("Transfer Successful");
        setSize(650,600);
        setLocation(325,55);
        setVisible(true);
        setLayout(null);

        JLabel heading = new JLabel("Transaction Successful");
        heading.setFont(new Font("Aerial", Font.BOLD, 48));
        heading.setBounds(50, 250, 600, 40);
        add(heading);

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(550,15,45,45);
        backButton.setBackground(Color.ORANGE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

        getContentPane().setBackground(Color.ORANGE);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == backButton){
            new HomePage().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TransactionSuccess();
    }
}
