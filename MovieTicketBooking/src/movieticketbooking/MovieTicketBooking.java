package movieticketbooking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MovieTicketBooking{

    public static void main(String[] args) throws Exception{
        //Variables
        int choice;
        boolean adminFlag = false ,userFlag = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //Create a Session
        Manage manage = new Manage();
        Admin admin = new Admin();
        User user = new User();
        
        //Create Accounts
        manage.createAdminAccounts(admin);
        manage.createUserAccounts(user);
        
        //Add All Theatres
        manage.addAllTheatres();
        
        //Add All Movies
        manage.addAllMovies();
        
        manage.setShows();
        manage.setTicketCosts();
        manage.addAllSnacks();
//        //Display All Accounts
//        manage.displayAllAdmin(admin);
//        manage.displayAllUser(user);
//        
//        //Display All Movies
//        manage.displayAllMovies();
//        //Display All Theatres
//        manage.displayAllTheatres();
        manage.readBookings();
        
        
        do{
            System.out.println("Enter:\n1)Login as Admin\n2)Login as User\nAnyother to Exit");
            choice = Integer.parseInt(br.readLine());
            
            if(choice==1){
                String uname,passwd;
                System.out.println("Admin Login:");
                System.out.println("Enter UserName:");
                uname = br.readLine();
                System.out.println("Enter Password:");
                passwd = br.readLine();
                
                if(manage.loginAsAdmin(admin,uname,passwd)){
                    System.out.println("Login Successful");
                    adminFlag = true;
                    
                    do{
                        System.out.println("Admin Page:\nEnter:\n1)Display All Admin\n2)Display All Users\n3)Add new Movie\n4)Add Shows\n5)Add All Ticket Costs\n6)Display All Costs\n7)Add All Snacks\nAnyother to Logout");
                        int ch = Integer.parseInt(br.readLine());
                        if(ch==1){
                            manage.displayAllAdmin(admin);
                        }
                        else if(ch==2){
                            manage.displayAllUser(user);
                        }
                        else if(ch==3){
                            
                            String movieName,Director;
                            System.out.println("Enter Movie Name");
                            movieName = br.readLine();
                            System.out.println("Enter Director");
                            Director = br.readLine();
                            manage.addOneMovie(movieName,Director);
                            
                        }
                        
                        else if(ch==4){
                            
                            String Date,theatreName,screenName,movieName,timing;
                            System.out.println("Enter Date:");
                            Date = br.readLine();
                            System.out.println("Enter Theatre Name:");
                            theatreName = br.readLine();
                            System.out.println("Enter Screen Name");
                            screenName = br.readLine();
                            System.out.println("Enter Movie Name");
                            movieName = br.readLine();
                            System.out.println("Enter Show Timing");
                            timing = br.readLine();
                            
                            manage.addOneShow(Date,theatreName,screenName,movieName,timing);
                        }
                        
                        else if(ch==5){
                            manage.setTicketCosts();
                        }
                        else if(ch==6){
                            manage.displayAllTicketCosts();
                        }
                        else if(ch==7){
                            manage.addAllSnacks();
                        }
                        else{
                            System.out.println("Admin Logged out Successfully");
                            adminFlag = false;
                        }
                        
                    }while(adminFlag);
                    
                }
                else{
                    System.out.println("Login Failed");
                    adminFlag = false;
                }
            }
            else if(choice==2){
                String uname,passwd;
                System.out.println("User Login:");
                System.out.println("Enter UserName:");
                uname = br.readLine();
                System.out.println("Enter Password:");
                passwd = br.readLine();
                
                if(manage.loginAsUser(user,uname,passwd)){
                    System.out.println("Login Successful");
                    userFlag = true;
                    
                    do{
                        System.out.println("User Page:\nEnter:\n1)Display All Movies\n2)Display All Theatres\n3)Display All Shows of A Theatre\n4)Book Shows\n5)Cancel Tickets\n6)Show all Bookings\nAnyother to Logout");
                        int ch = Integer.parseInt(br.readLine());
                        if(ch==1){
                            manage.displayAllMovies();
                        }
                        else if(ch==2){
                            manage.displayAllTheatres();
                        }
                        else if(ch==3){
                            String tname;
                            System.out.println("Enter Theatre Name:");
                            tname = br.readLine();
                            manage.displayShows(tname);
                        }
                        else if(ch==4){
                            System.out.println("Enter Date of The Show as dd-mm-yyyy format:");
                            String datee = br.readLine();
                            System.out.println("Enter the Name of the Theatre:");
                            String tname = br.readLine();
                            manage.bookTickets(uname,datee,tname);
                        }
                        else if(ch==5){
                            System.out.println("Enter Booking ID");
                            String bid = br.readLine();
                            manage.updateAfterCancel(bid);
                        }
                        else if(ch==6){     
                            manage.displayAllBookingsOfAUser(uname);
                            
                        }
                        else{
                            System.out.println("User Logged out Successfully");
                            userFlag = false;
                        }
                        
                    }while(userFlag);
                    
                }
                else{
                    System.out.println("Login Failed");
                    userFlag = false;
                }
            }
        }while(choice<3 && choice >0);
        
        System.out.println("Thank You!");
        
    }
}
