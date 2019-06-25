package movieticketbooking;
import java.util.HashMap;
import java.util.Map;

public class Admin {
    private HashMap<String,String> adminHashMap = new HashMap<String,String>();
    
    //Check if the UserName us unique
    public boolean isAdminUnique(String userName){
        if(adminHashMap.get(userName)==null){
            return true;
        }
        return false;
    }
    
    //Create an Admin Account
    public boolean createAdmin(String userName,String Password){
        if(isAdminUnique(userName)){
            adminHashMap.put(userName,Password);
            return true;
        }
        return false;
    }
    
    //Admin Login
    public boolean adminLogin(String userName,String password){
        if(!isAdminUnique(userName)){
            String passwd = adminHashMap.get(userName);
            if(passwd.equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    //Display all users
    public void displayAll(){
        for(Map.Entry<String,String> hash : adminHashMap.entrySet()){
            System.out.println(hash.getKey()+" : "+hash.getValue());
        }
    }
}
