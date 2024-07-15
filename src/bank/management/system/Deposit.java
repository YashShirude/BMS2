package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
public class Deposit extends JFrame implements ActionListener{
    String accNo;
    int k = 0;
    List<String> times,depTypes,principals,rates,tenures,interests;
    JButton backButton, openDepositButton;

    Deposit(){
        setTitle("Deposits");

        setSize(800,580);
        setLocation(260,50);
        setVisible(true);
        setLayout(null);

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(75,75, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        bankLogoLabel.setBounds(130, 5,100, 75);
        add(bankLogoLabel);

        JLabel heading = new JLabel("Deposit Details");
        heading.setFont(new Font("Aerial", Font.BOLD, 45));
        heading.setBounds(260, 20, 400, 50);
        add(heading);

        /*
        FD / RD
        Rs. 10000   15 Months
        From 20/10/23 to 20/2/25
        Amount at Present:
        Amount on Maturity:
        */

        accNo = SqlQuery.accNo;
        SqlQuery query17 = new SqlQuery();
        query17.fetchDepositDetails(accNo);
        times = query17.depositTimes;
        depTypes = query17.depTypes;
        principals = query17.principals;
        rates = query17.rates;
        tenures  = query17.tenures;
        interests = query17.interests;

        JLabel l3 = new JLabel();
        add(l3);

        while(k < times.size()){
            l3.setText(l3.getText() + "<html>" + times.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + depTypes.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + principals.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rates.get(k) + "%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + tenures.get(k) + "&nbsp;months&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rs." + interests.get(k) + "<br><br><br><html>");
            k++;
        }

        l3.setBounds(20, 50, 780, 200);

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(700,15,45,45);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

        openDepositButton= new JButton("New Deposit");
        openDepositButton.setFont(new Font("Rale way", Font.BOLD, 25));
        openDepositButton.setBounds(310,450,200,45);
        openDepositButton.setBackground(Color.GREEN);
        openDepositButton.setForeground(Color.BLACK);
        openDepositButton.addActionListener(this);
        add(openDepositButton);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== backButton){
            setVisible(false);
            new HomePage().setVisible(true);

        }else if(ae.getSource()== openDepositButton){
            setVisible(false);
            new OpenDeposit().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit().setVisible(true);
    }
}
