package movieticketbooking;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Manage {
    
    //ArrayList<Theatre> theatreList = new ArrayList<Theatre>();
    HashMap<String,Theatre> theatreHashMap = new HashMap<String,Theatre>();
    //ArrayList<Movie> movieList = new ArrayList<Movie>();
    HashMap<String,Movie> movieHashMap = new HashMap<String,Movie>();
    HashMap<String,Theatre> allBookingsHashMap = new HashMap<String,Theatre>(); 
    
    //Create Admin accounts
    public void createAdminAccounts(Admin admin) throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\AdminAccounts.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));      
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            if(admin.createAdmin(t[0],t[1])){
                System.out.println("Admin "+t[0]+" Created");
            }
            else{
                System.out.println("Admin "+t[0]+" already Exists");
            }
        }
    }
    
    //Admin Login
    public boolean loginAsAdmin(Admin admin, String userName, String password){
        if(admin.adminLogin(userName,password)){
            return true;
        }
        return false;
    }
    
    //Add All Theatre Details
    public void addAllTheatres() throws Exception{
        
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\AllTheatres.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            Theatre theatre = new Theatre(t[0],t[1],t[2]);
            if(theatreHashMap.get(t[0])==null){
                theatreHashMap.put(t[0],theatre);
            }
        }
    }
    
    //Display All Theatres
    public void displayAllTheatres(){
        System.out.println("All Theatres:");
        for(Map.Entry<String,Theatre> e: theatreHashMap.entrySet()){
            System.out.println(e.getValue());
        }
    }
    
    //Add All Movies Details
    public void addAllMovies() throws Exception{
        
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\AllMovies.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
           
            if(movieHashMap.get(t[0])==null){
                Movie movie = new Movie(t[0],t[1]);
                movieHashMap.put(t[0],movie);
            }
            
        }
    }
    
    public void addOneMovie(String movieName,String Director) throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\AllMovies.txt");
        String line;
        BufferedWriter wr = new BufferedWriter(new FileWriter(path,true));
        wr.write("\r\n");
        String s = String.format("%s,%s",movieName,Director);
        wr.write(s);
        wr.close();
        Movie movie = new Movie(movieName,Director);
        if(movieHashMap.get(movieName)==null){
                movieHashMap.put(movieName,movie);
            }
        
    }
    
    public void addOneShow(String Date, String theatreName, String screenName, String movieName,String timing) throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\SetShows.txt");
        String path1 = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\Screen.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new FileWriter(path,true));
        
        BufferedReader rdr = new BufferedReader(new FileReader(path1));
        
        String line = "";
        while((line = rdr.readLine())!=null){
            String []st = line.split(",");
            if(st[0].equals(theatreName) && st[1].equals(screenName)){
                
            for(int i=2;i<st.length;i+=2){
                
                String seatClass = st[i];
                Integer count = Integer.parseInt(st[i+1]);
                
                if(count > 0){
                    String s = String.format("%s,%s,%s,%s,%s,%s,%s",Date,theatreName,screenName,movieName,timing,seatClass,Integer.toString(count));
                    wr.write("\r\n");
                    wr.write(s);
                }
                 
                boolean fflag = true;
                if(theatreHashMap.get(theatreName)!=null){
                    Theatre tl = theatreHashMap.get(theatreName);
                        if(tl.getDateHashMap()!=null){
                            HashMap<String, ArrayList<Screen>> dhm = tl.getDateHashMap();

                            if(dhm.get(Date)!=null){
                                for(Screen ss : dhm.get(Date)){
                                    if(ss.getScreenName().equals(screenName)){
                                        ss.setSeatAvailability(timing,seatClass,count);
                                        tl.setDateHashMap(Date,ss,timing,seatClass);
                                        System.out.println("Show added Successfully to "+theatreName);
                                        fflag = false;

                                    }
                                }
                            }
                        }
                        
                        if(fflag){
                            Screen screen = new Screen(theatreName,screenName,movieName);
                            screen.setSeatAvailability(timing,seatClass,count);
                            tl.setDateHashMap(Date,screen,timing,seatClass);
                            System.out.println("Show added Successfully to "+theatreName);  
                        }
                    }

                }
                break;
            }
        }
                
            wr.close();
        
    }

    
    //Display All Theatres
    public void displayAllMovies(){
        System.out.println("All Movies:");
        for(Map.Entry<String,Movie> e : movieHashMap.entrySet()){
            System.out.println(e.getValue());
        }
    }
    
    //Display all users
    public void displayAllAdmin(Admin admin){
        System.out.println("All Admin:");
        admin.displayAll();
    }
    
    //Create User accounts
    public void createUserAccounts(User user) throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\UserAccounts.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            if(user.createUser(t[0],t[1])){
                System.out.println("User "+t[0]+" Created");
            }
            else{
                System.out.println("User "+t[0]+" already Exists");
            }
        }
    }
    
    //Add All Snack Items
    public void addAllSnacks() throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\Snacks.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            Snack snack = new Snack(t[0],t[1],Double.parseDouble(t[2]));
            
            if(theatreHashMap.get(t[0])!=null){
                theatreHashMap.get(t[0]).setSnackHashMap(t[1],snack);
                System.out.println("Snack added Successfully to "+t[0]);
            }
        }  
    }
    
    //Display All Snacks
    public void displayAllSnacks(String Name){
        System.out.println("All Snacks:");
        if(theatreHashMap.get(Name)!=null){
            for(Map.Entry<String,Snack> entry: theatreHashMap.get(Name).getSnackHashMap().entrySet()){
                System.out.println(entry.getValue());
            }
        }
        
        
    }
    
    //Set Ticket Costs for every Theatre
    public void setTicketCosts()throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\TicketCost.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            Cost cost = new Cost(t[0],Double.parseDouble(t[1]),Double.parseDouble(t[2]),Double.parseDouble(t[3]),Double.parseDouble(t[4]),Double.parseDouble(t[5]));
            if(theatreHashMap.get(t[0])!=null){
                theatreHashMap.get(t[0]).setCost(cost);
                System.out.println("Cost of Tickets added Successfully to "+t[0]);
            }
            else{
                System.out.println("Invalid Theatre Name");
            }
            
        }
    }
    
    //Display All Costs
    public void displayAllTicketCosts(){
        System.out.println("All Ticket Costs:");
        for(Map.Entry<String,Theatre> e : theatreHashMap.entrySet()){
            System.out.println(e.getValue().getCost());
            System.out.println("_________________________");
        }
    }   
    
    //display all bookings of a user  
    public void displayAllBookingsOfAUser(String uname){
        
         for(Map.Entry<String,Theatre> e : allBookingsHashMap.entrySet()){
             System.out.println("hii");
             for(Map.Entry<String,Booking> ee : e.getValue().getBookingHashMap().entrySet()){
                 if(ee.getValue().getUserName().equals(uname)){
                     System.out.println(ee.getValue());
                     System.out.println("________________________ ");
                 }
             }
         }
    }
    
    
    //User Login
    public boolean loginAsUser(User user, String userName, String password){
        if(user.userLogin(userName,password)){
            return true;
        }
        return false;
    }
    
    //Display all users
    public void displayAllUser(User user){
        System.out.println("All Users:");
        user.displayAll();
    }
    
    //***Set Shows
    public void setShows()throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\SetShows.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            boolean flag = true;
            if(theatreHashMap.get(t[1])!=null){
                Theatre tl = theatreHashMap.get(t[1]);
                    if(tl.getDateHashMap()!=null){
                        HashMap<String, ArrayList<Screen>> dhm = tl.getDateHashMap();
                        
                        if(dhm.get(t[0])!=null){
                            for(Screen ss : dhm.get(t[0])){
                                if(ss.getScreenName().equals(t[2])){
                                    ss.setSeatAvailability(t[4],t[5],Integer.parseInt(t[6]));
                                    tl.setDateHashMap(t[0],ss,t[4],t[5]);
                                    System.out.println("Show added Successfully to "+t[1]);
                                    flag = false;

                                }
                            }
                        }
                    }
                    if(flag){
                        Screen screen = new Screen(t[1],t[2],t[3]);
                        screen.setSeatAvailability(t[4],t[5],Integer.parseInt(t[6]));
                        tl.setDateHashMap(t[0],screen,t[4],t[5]);
                        System.out.println("Show added Successfully to "+t[1]);  
                    }
                }
            }
        }
    
    
    //***Display All Shows of A Theatre
    public void displayShows(String theatreName){
       
        if(theatreHashMap.get(theatreName)!=null){
            theatreHashMap.get(theatreName).displayAllShows();
        } 
       
    }
    
    //***Display All Shows of a Screen of A Theatre
    public void displayShows(String Date,String theatreName, String screenName,String timing){
        
        if(theatreHashMap.get(theatreName)!=null){
            theatreHashMap.get(theatreName).displayShowAtParticularTime(Date,screenName,timing);
        } 
        
    }
   
    ///***Update Seats after Cancelling
    public void updateAfterCancel(String bookingId) throws Exception{
        if(allBookingsHashMap.get(bookingId)!=null){
            Theatre theatre = allBookingsHashMap.get(bookingId);
            HashMap<String,Booking> bhm = theatre.getBookingHashMap();
            if(bhm.get(bookingId)!=null){
                Booking booking = bhm.get(bookingId);
                HashMap<String,ArrayList<Screen>> dateHashMap = theatre.getDateHashMap();
                if(dateHashMap.get(booking.getDate())!=null){
                    ArrayList<Screen> screenList = dateHashMap.get(booking.getDate());
                    for(Screen ss : screenList){
                        if(ss.getScreenName().equals(booking.getScreenName())){
                            ss.updateSeatAvailability(booking.getDate(),booking.getTheatre().getName(),booking.getTiming(),"Gold",booking.getCountGold());
                            ss.updateSeatAvailability(booking.getDate(),booking.getTheatre().getName(),booking.getTiming(),"Silver",booking.getCountSilver());
                            ss.updateSeatAvailability(booking.getDate(),booking.getTheatre().getName(),booking.getTiming(),"Elite",booking.getCountElite());
                            ss.updateSeatAvailability(booking.getDate(),booking.getTheatre().getName(),booking.getTiming(),"Box",booking.getCountBox());
                            ss.updateSeatAvailability(booking.getDate(),booking.getTheatre().getName(),booking.getTiming(),"Balcony",booking.getCountBalcony());
                            System.out.println("Cancelled successfully");
                            removeBookingData(bookingId);
                        }
                    }
                }
            }
            
        }
        else{
            System.out.println("Invalid Booking ID");
        }
            
    }
    
    //Store Booking Data
    public void storeBookingData(Booking booking) throws Exception{
        String path = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\Bookings.txt";
        String s = String.format("%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d,%d",booking.getUserName(),booking.getBookingId(),booking.getDate(),booking.getTheatre().getName(),booking.getScreenName(),booking.getMovie(),booking.getTiming(),booking.getCountBox(),booking.getCountBalcony(),booking.getCountElite(),booking.getCountGold(),booking.getCountSilver());
        BufferedWriter wr = new BufferedWriter(new FileWriter(path,true));
        wr.write(s+"\r\n");
        wr.close();
        
    }
    
    public void removeBookingData(String bookingId) throws Exception{
        
        System.out.println("Remove Booking is working");
        
        String path = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\Bookings.txt";
        String path1 = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\CancelledBookings.txt";
        //String s = String.format("%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d,%d",booking.getUserName(),booking.getBookingId(),booking.getDate(),booking.getTheatre().getName(),booking.getScreenName(),booking.getMovie(),booking.getTiming(),booking.getCountBox(),booking.getCountBalcony(),booking.getCountElite(),booking.getCountGold(),booking.getCountSilver());
        BufferedReader br = new BufferedReader(new FileReader(path));
        String s = "";
        String del = "";
        String line;
        while((line = br.readLine())!=null){
            String []st = line.split(",");
            if(st[1].equals(bookingId)){
                del+=line+"\r\n";
            }
            else{
                s+=line+"\r\n";
            }
        }
        
        br.close();
        
        BufferedWriter wr = new BufferedWriter(new FileWriter(path));
        wr.write(s+"\r\n");
        wr.close();
        BufferedWriter wrr = new BufferedWriter(new FileWriter(path1));
        wrr.write(del+"\r\n");
        wrr.close();
    }
        
    public void createBookingObjects(String user, String bookingID, String Date, String theatreName, String screenName, String movieName, String timing, String box, String balc, String e, String g, String sil) throws Exception{
       
        
            if(theatreHashMap.get(theatreName)!=null){
                Theatre t = theatreHashMap.get(theatreName);
                Booking booking = new Booking(user,t,bookingID,Date,screenName,timing,movieName);
                booking.setCountBox(Integer.parseInt(box));
                booking.setCountBalcony(Integer.parseInt(balc));
                booking.setCountElite(Integer.parseInt(e));
                booking.setCountGold(Integer.parseInt(g));
                booking.setCountSilver(Integer.parseInt(sil));

                t.setBookingHashMap(bookingID,booking);
                System.out.println("Booking : "+bookingID+" for "+user+" Stored");
                
                if(allBookingsHashMap.get(bookingID)==null){
                    allBookingsHashMap.put(bookingID,t);
                }
                
                //System.out.println(t.getBookingHashMap().get(bookingID));
            }
        
        
    }
    
    public void readBookings() throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\Bookings.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            
            createBookingObjects(t[0],t[1],t[2],t[3],t[4],t[5],t[6],t[7],t[8],t[9],t[10],t[11]);
        }
    }
    
  
    //***Book Tickets
    public void bookTickets(String userName,String Date, String theatreName)throws Exception{
        
        boolean ch = true;
        String seatClass;
        Integer count;
        double totalCost = 0; 
        String b="";
        Booking booking = null;
        boolean billFlag = false;
        Cost costObj = null;
        int cc;
        double snackCost = 0;
        boolean snackFlag = false;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("Enter the Screen Name");
            String screenName = br.readLine();
            //displayShows(Date,theatreName,screenName);
            do{
               System.out.println("Enter the Show Timing"); 
               String timing = br.readLine();
               displayShows(Date,theatreName,screenName,timing);
               //booking
               if(theatreHashMap.get(theatreName)!=null){
                    Theatre t = theatreHashMap.get(theatreName);
                    //System.out.println(theatreName);
                    Screen ss = t.getScreenObj(Date,screenName,timing);
                    if(ss!=null){
                        //System.out.println("Screen obj ss created");
                        HashMap<String,HashMap<String,Integer>> hm = ss.getSeatAvailability();
                        //System.out.println(hm);
                        if(hm.get(timing)!=null){
                            //System.out.println("Generated Random No");
                            Random rand = new Random();
                            b = Integer.toString(rand.nextInt(10000000));
                            booking = new Booking(userName,t,b,Date,screenName,timing,ss.getMovieName());
                            HashMap<String,Integer> hm1 = hm.get(timing);

                            do{
                                System.out.println("Enter SeatClass:");
                                seatClass = br.readLine();
                                System.out.println("Enter No of Seats:");
                                count = Integer.parseInt(br.readLine());

                                if(hm1.get(seatClass)!=null){
                                    Integer temp = hm1.get(seatClass);
                                    if(temp >= count && temp>0){
                                        billFlag = true;
                                        costObj = t.getCost();
                                        if(seatClass.equals("Gold")){
                                            booking.setCountGold(booking.getCountGold()+count);
                                            totalCost+=(costObj.getGold())*count;
                                        }
                                        else if(seatClass.equals("Balcony")){
                                            booking.setCountBalcony(booking.getCountBalcony()+count);
                                            totalCost+=(costObj.getBalcony())*count;
                                        }
                                        else if(seatClass.equals("Box")){
                                            booking.setCountBox(booking.getCountBox()+count);
                                            totalCost+=(costObj.getBox())*count;
                                        }
                                        else if(seatClass.equals("Elite")){
                                            booking.setCountElite(booking.getCountElite()+count);
                                            totalCost+=(costObj.getElite())*count;

                                        }
                                        else if(seatClass.equals("Silver")){
                                            booking.setCountSilver(booking.getCountSilver()+count);
                                            totalCost+=(costObj.getSilver())*count;
                                        }
                                        temp-=count;
                                        ss.setSeatAvailability(timing,seatClass,temp);
                                        updateTicketsInFile(Date,theatreName,screenName,timing,seatClass,temp);
                                        //update
                                    }
                                    else{
                                        System.out.println("Available Tickets are less than "+count+" Please Book Again!");
                                    }
                                }

                                System.out.println("Press 1 to continue booking, anyother to stop");
                                 if(!(br.readLine().equals("1"))){
                                     ch = false;
                                 }
                            }while(ch);

                            if(billFlag){
                                
                                booking.setUserName(userName);

                                do{
                                System.out.println("1)Add Snack Item\n2)Display All Snacks\nAnyother to Proceed to Bill");
                                cc = Integer.parseInt(br.readLine());
                                if(cc==1){
                                    System.out.println("Enter the Snack Name");
                                    String sname = br.readLine();
                                    System.out.println("Enter the Quantity");
                                    double qty = Double.parseDouble(br.readLine());
                                    HashMap<String,Snack> snackHash=  t.getSnackHashMap();
                                    if(snackHash.get(sname)!=null){
                                        Snack sn = snackHash.get(sname);
                                        snackCost+= (qty * sn.getCost());
                                    }
                                    if(snackCost > 0.0 && !snackFlag){
                                        snackFlag = true;
                                    }
                                }
                                else if(cc==2){
                                    displayAllSnacks(t.getName());
                                }
                                else{
                                    break;
                                }
                            }while(true);

                                t.setBookingHashMap(b,booking);
                                storeBookingData(booking);

                                if(allBookingsHashMap.get(b)==null){
                                    allBookingsHashMap.put(b,t);
                                }

                                System.out.println("_________________________________________");

                                System.out.println("BILL:\n");
                                System.out.println("Tickets booked SuccessFully");
                                System.out.println(booking);
                                System.out.println("Cost:");
                                System.out.println("Ticket Cost : "+totalCost);
                                System.out.println("GST : "+(totalCost*costObj.getGst()));
                                if(snackFlag){
                                    System.out.println("Snack Cost : "+snackCost);
                                }
                                System.out.println("Total Cost : "+(snackCost + totalCost + totalCost*costObj.getGst()));


                                System.out.println("_________________________________________");

                                billFlag = false;
                                totalCost = 0;
                                snackFlag = false; 

                            }
                            else{
                                System.out.println("You Haven't Booked Any Tickets");
                            }

                            
                        }
                    }
                }
                
               System.out.println("Enter 1 to Choose Another Timing, AnyOther to Continue");
            }while(Integer.parseInt(br.readLine())==1);
            
            System.out.println("Enter 1 to Choose Another Screen, AnyOther to Continue");
            
        }while(Integer.parseInt(br.readLine())==1);
    }
    
     
//***update Tickets
     public static void updateTicketsInFile(String Date, String theatreName,String screenName, String timing, String seatClass, int seat) throws Exception{
        String path = new String("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\MovieTicketBooking\\src\\movieticketbooking\\SetShows.txt");
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        String content = "";
        while((line = br.readLine())!=null){
            String []t = line.split(",");
            
            if(t[0].equals(Date) && t[1].equals(theatreName) && t[2].equals(screenName) && t[4].equals(timing) && t[5].equals(seatClass)){
                t[6] = Integer.toString(seat);
                for(String tt:t){
                    content+=tt+",";
                }
                StringBuffer sb= new StringBuffer(content);
                sb.deleteCharAt(sb.length()-1);
                content  = sb.toString();
                content+="$";
            }
            else{
                content+=line+"$";
            }
        }
        br.close();
        //System.out.println(content);
        BufferedWriter wr = new BufferedWriter(new FileWriter(path));
        String []st = content.split("\\$");
        for(String ss:st){
            //System.out.println(ss);
            wr.write(ss);
            wr.newLine();
        }
        
        wr.close();
    }
}
