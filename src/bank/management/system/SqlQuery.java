package bank.management.system;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlQuery {

    static String accNo;
    public static String appnNo, stringBalance, accType, name, pin;
    public static int remTransactions, remOverdraft, balance, totalPrincipal = 0,totalFd = 0,totalRd = 0;
    public List<String> times = new ArrayList<>();
    public List<String> messages = new ArrayList<>();
    public List<String> amounts = new ArrayList<>();
    public List<String> balances = new ArrayList<>();
    public List<String> tranTypes = new ArrayList<>();
    public List<String> depositTimes = new ArrayList<>();
    public List<String> depTypes = new ArrayList<>();
    public List<String> principals = new ArrayList<>();
    public List<String> rates = new ArrayList<>();
    public List<String> tenures = new ArrayList<>();
    public List<String> interests = new ArrayList<>();

    SqlQuery(){

    }
    SqlQuery(String accNo){
        SqlQuery.accNo = accNo;
        try {
            Conn c0 = new Conn();
            String q0 = "SELECT appnNo,balance,accType,pin FROM financialDetails WHERE accNo = '" + accNo + "'";
            ResultSet output = c0.s.executeQuery(q0);
            if (output.next()) {
                appnNo = output.getString(1);
                stringBalance = output.getString(2);
                balance = Integer.parseInt(stringBalance);
                accType = output.getString(3);
                pin = output.getString(4);
            }else{
                System.out.println("No results found.");
            }
            output.close();

            String q1 = "SELECT name FROM personalDetails WHERE appnNo = '" + appnNo + "'";
            ResultSet output1 = c0.s.executeQuery(q1);
            if (output1.next()) {
                name = output1.getString(1);
            }else{
                System.out.println("No results found.");
            }
            output1.close();

            String q2 = "SELECT remainingtrans,remainingOverdraft FROM transaction WHERE accNo = '" + accNo + "'";
            ResultSet output2 = c0.s.executeQuery(q2);
            if (output2.next()) {
                remTransactions = output2.getInt(1);
                remOverdraft = output2.getInt(2);
            }else{
                System.out.println("No results found.");
            }
            output2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insertTransactionDetails(String acc,String msg,String amt,String tranType,String balance){
        LocalDateTime currentDateTime = LocalDateTime.now();
        try{
            Conn c1 = new Conn();
            String q3 = "insert into transactionDetails values('"+acc+"','"+msg+"','"+amt+"','"+tranType+"','"+balance+"','"+currentDateTime+"')";
            c1.s.executeUpdate(q3);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction(String acc, int remTrans,int remOverdraft){
        try{
            Conn c2 = new Conn();
            String q4 = "UPDATE transaction SET remainingtrans = '"+remTrans+"', remainingOverdraft = '"+remOverdraft+"' WHERE accNo = '"+acc+"'";
            c2.s.executeUpdate(q4);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateFinancialDetails(String acc,String balance){
        try{
            Conn c3 = new Conn();
            String q5 = "UPDATE financialDetails SET balance = '"+balance+"' WHERE accNo = '"+acc+"'";
            c3.s.executeUpdate(q5);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void fetchTransactionalDetails(String acc){
        try{
            Conn c4 = new Conn();
            String q6 = "SELECT time,message,amount,tranType,balance FROM transactionDetails WHERE accNo = '" + acc + "' ORDER BY time DESC";
            ResultSet output3 = c4.s.executeQuery(q6);
                while (output3.next()) {
                    times.add(output3.getString("time"));
                    messages.add(output3.getString("message"));
                    amounts.add(output3.getString("amount"));
                    tranTypes.add(output3.getString("tranType"));
                    balances.add(output3.getString("balance"));
                }
            output3.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void fetchDepositDetails(String acc){
        totalPrincipal = 0;
        totalFd = 0;
        totalRd = 0;
        try{
            Conn c5 = new Conn();
            String q7 = "SELECT time,accType,principal,rate,tenure,interest FROM deposit WHERE accNo = '" + acc + "' ORDER BY time DESC";
            ResultSet output4 = c5.s.executeQuery(q7);
            while (output4.next()) {
                depositTimes.add(output4.getString("time"));
                depTypes.add(output4.getString("accType"));
                principals.add(output4.getString("principal"));
                rates.add(output4.getString("rate"));
                tenures.add(output4.getString("tenure"));
                interests.add(output4.getString("interest"));
            }
            for (int i=0;i<principals.size();i++) {
                totalPrincipal += Integer.parseInt(principals.get(i));
                if(depTypes.get(i).equals("RD")){
                    totalRd++;
                }else{
                    totalFd++;
                }
            }
            output4.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
