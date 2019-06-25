package movieticketbooking;
import java.util.HashMap;
import java.util.Map;
public class User {
    HashMap<String,String> userHashMap = new HashMap<String,String>();
    
    public boolean isUserUnique(String userName){
        if(userHashMap.get(userName)==null){
            return true;
        }
        return false;
    }
    
    //Create an Admin Account
    public boolean createUser(String userName,String Password){
        if(isUserUnique(userName)){
            userHashMap.put(userName,Password);
            return true;
        }
        return false;
    }
    
    //User Login
    public boolean userLogin(String userName,String password){
        if(!isUserUnique(userName)){
            String passwd = userHashMap.get(userName);
            if(passwd.equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    //Display all Users
    public void displayAll(){
        for(Map.Entry<String,String> hash : userHashMap.entrySet()){
            System.out.println(hash.getKey()+" : "+hash.getValue());
        }
    }
    
    
}
