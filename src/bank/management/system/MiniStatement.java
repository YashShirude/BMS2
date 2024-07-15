package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MiniStatement extends JFrame{
    String accNo;
    List<String> messages, amounts, times, balances, tranTypes;
    int k = 0;
    MiniStatement(){
        super("Mini Statement");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(800,600);
        setLocation(20,20);

        JLabel l2 = new JLabel("CFDH Bank");
        l2.setBounds(350, 20, 100, 30);
        add(l2);

        JLabel l3 = new JLabel();
        add(l3);

        accNo = SqlQuery.accNo;
        SqlQuery query16 = new SqlQuery();
        query16.fetchTransactionalDetails(accNo);
        times = query16.times;
        messages = query16.messages;
        amounts = query16.amounts;
        tranTypes = query16.tranTypes;
        balances = query16.balances;

        while(k < times.size()){
            if(k != times.size() -1 && SqlQuery.accType.equals("Current") && Integer.parseInt(balances.get(k+1)) < Integer.parseInt(amounts.get(k)) && tranTypes.get(k).equals("Debited")){
                String msg = "OverDraft Used";
                l3.setText(l3.getText() + "<html>" + times.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + messages.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + amounts.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + tranTypes.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + balances.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span font color = 'red'>" + msg +"</span><br><br><br><html>");
            }else{
                l3.setText(l3.getText() + "<html>" + times.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + messages.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + amounts.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + tranTypes.get(k) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + balances.get(k) + "</p><br><br><br><html>");
            }
            k++;
        }

        l3.setBounds(20, 50, 780, 600);
    }
}
