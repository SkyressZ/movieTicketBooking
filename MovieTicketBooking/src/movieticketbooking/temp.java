package movieticketbooking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;  

public class temp {
    
     public static void updateTicketsInFile(String Date, String theatreName,String screenName, String timing, String seatClass, int seat) throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\temp.txt");
        String path1 = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\temp1.txt");
        
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        String content = "";
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            
            if(t[0].equals(Date) && t[1].equals(theatreName) && t[3].equals(screenName) && t[4].equals(timing) && t[5].equals(seatClass)){
                t[6] = Integer.toString(seat);
                for(String tt:t){
                    content+=tt+",";
                }
                StringBuffer sb= new StringBuffer(content);
                sb.deleteCharAt(sb.length()-1);
                content  = sb.toString();
                content+="\n";
            }
            else{
                content+=line+"\n";
            }
        }
        br.close();
        //System.out.println(content);
        BufferedWriter wr = new BufferedWriter(new FileWriter(path1));
        String []st = content.split("\\$");
        for(String ss:st){
            //System.out.println(ss);
            wr.write(ss);
            wr.newLine();
        }
        //wr.write(content);
        wr.close();
    }

    
    public static void main(String []args) throws Exception{
        
        updateTicketsInFile("14-06-2019","SPI Cinemas","Screen 1","9:00AM","Box",34);
        
    }
}
