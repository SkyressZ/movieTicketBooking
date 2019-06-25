package movieticketbooking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Theatre {
    private String Name, Address, phoneNumber;
    private HashMap<String,ArrayList<Screen>> dateHashMap = new HashMap<String,ArrayList<Screen>>();
    private HashMap<String,Snack> snackHashMap = new HashMap<String,Snack>();
    private HashMap<String,Booking> bookingHashMap = new HashMap<String,Booking>();
    private Cost cost;

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Theatre(String Name, String Address, String phoneNumber) {
        this.Name = Name;
        this.Address = Address;
        this.phoneNumber =  phoneNumber;
    }

    public HashMap<String, ArrayList<Screen>> getDateHashMap() {
        return dateHashMap;
    }

    //*** Set Date Wise Show timings for each screen and it's seating classes
    public void setDateHashMap(String Date,Screen screen, String timing, String seatClass) {
        if(this.dateHashMap.get(Date)!=null){
            ArrayList<Screen> al = dateHashMap.get(Date);
            boolean flag = true;
            for(Screen s:al){
                if(s.getScreenName().equals(screen.getScreenName())){
                    HashMap<String,HashMap<String,Integer>> seatAvailability = s.getSeatAvailability();
                    if(seatAvailability.get(timing)!=null){
                        HashMap<String,Integer> hm = seatAvailability.get(timing);
                        if(hm.get(seatClass)!=null){
                            flag = false;
                            break;
                        }
                    }
                }
            }
            
            if(flag){
                al.add(screen);
                dateHashMap.put(Date,al);
            }
          }  
        else{
            ArrayList<Screen> al = new ArrayList<Screen>();
            al.add(screen);
            dateHashMap.put(Date,al);
        }
    }
    
    
    //***Book tickets
    public Screen getScreenObj(String date, String sname, String timing){
        if(dateHashMap.get(date)!=null){
            for(Screen ss:dateHashMap.get(date)){
                if(ss.getScreenName().equals(sname)){
                    HashMap<String,HashMap<String,Integer>> seatAvailability = ss.getSeatAvailability();
                    System.out.println(seatAvailability);
                    if(seatAvailability.get(timing)!=null){
                        return ss;
                    }
                    
                }
            }
        }
        return null;
        
    }
    
    @Override
    public String toString() {
        return String.format("Name : %s\tAddress : %s\tPhone : %s",this.Name,this.Address,this.phoneNumber);
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void displayAllShows(){
        for(Map.Entry<String,ArrayList<Screen>> hm:dateHashMap.entrySet()){
            System.out.println("Date :"+hm.getKey());
            for(Screen ss:hm.getValue()){
                ss.displayAllSeats();
            }
        }
    }

    public void displayAllShowsOfAScreen(String Date,String screenName){
        
        if(dateHashMap.get(Date)!=null){
           ArrayList<Screen> al = dateHashMap.get(Date);
           for(Screen s:al){
               if(s.getScreenName().equals(screenName)){
                   s.displayAllSeats();
               }
           }
        }
        
    }
    
    public void displayShowAtParticularTime(String Date,String screenName,String timing){
        
        if(dateHashMap.get(Date)!=null){
           ArrayList<Screen> al = dateHashMap.get(Date);
           for(Screen s:al){
               if(s.getScreenName().equals(screenName)){
                   System.out.println(s.getMovieName());
                   HashMap<String,HashMap<String,Integer>> seatAvailability = s.getSeatAvailability();
                   if(seatAvailability.get(timing)!=null){
                       HashMap<String,Integer> hm = seatAvailability.get(timing);
                       for(Map.Entry<String,Integer> e: hm.entrySet()){
                           System.out.println(e.getKey()+" : "+e.getValue());
                       }
                   }
               }
           }
        }
        
    }

    public HashMap<String, Snack> getSnackHashMap() {
        return snackHashMap;
    }

    public void setSnackHashMap(String snackName, Snack snack) {
        if(snackHashMap.get(snackName)==null){
            snackHashMap.put(snackName,snack);
        }
    }

    public HashMap<String, Booking> getBookingHashMap() {
        return bookingHashMap;
    }

    public void setBookingHashMap(String bookingId, Booking booking) {
        if(this.bookingHashMap.get(bookingId)==null){
            this.bookingHashMap.put(bookingId,booking);
        }
        
        //System.out.println(this.bookingHashMap.get(bookingId));
    }
    
    
    
    
    
}
