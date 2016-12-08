package kgym;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * ユーザー情報を持ちまわるJavaBeans
 */
public class UserDataDTO {
    private int userID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private int deleteFlg;
    
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;        
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;       
    }
    
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;     
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    
    public Timestamp getNewDate() {
        return newDate;
    }
    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }
    
    public int getDeleteFlg() {
        return deleteFlg;
    }
    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }
    
    
    public void DTO2UDMapping(UserData ud){
        ud.setName(this.name);
        
        ud.setPassword(this.password);
        
        ud.setMail(this.mail);
        
        String[] address_ud = this.address.split(" ");
        ud.setPrefecture(address_ud[0]);
        ud.setCity(address_ud[1]);
        ud.setStreetNumber(address_ud[2]);
        
        /*
        * buildingが存在するかで場合分け
        * 配列addressに上の要素以外があるかで判定
        */
        if(address_ud.length == 3){
            ud.setBuilding("");
        }else{
            ud.setBuilding(address_ud[3]);
        }
    }
    
    public ArrayList<String> userList (UserDataDTO udd){
        ArrayList<String> userL = new ArrayList<>();
        if(!(this.name.equals(udd.getName()))){
            userL.add("name");
        }
        if(!(this.password.equals(udd.getPassword()))){ 
            userL.add("password");
        }
        if(!(this.mail.equals(udd.getMail()))){
            userL.add("mail");
        }
        if(!(this.address.equals(udd.getAddress()))){
            userL.add("address");
        }
        return userL;
    }
    
    //購入合計金額の更新を行う
    public void totalSum(int plusTotal){
        this.total += this.total + plusTotal;
    }
}


