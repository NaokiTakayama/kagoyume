package kgym;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ページで入出力されるユーザー情報を持ちまわるJavaBeans。
 */
public class UserData implements Serializable{
    private String name;
    private String password;
    private String mail;
    private String prefecture;
    private String city;
    private String street_number;
    private String building;
 
    
    public UserData(){
        this.name = "";
        this.password = "";
        this.mail = "";
        this.prefecture = "";
        this.city = "";
        this.street_number = "";
        this.building= "";
    }
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name == null || name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name.trim();
        }
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        //空文字(未入力)の場合空文字をセット
        if(password == null || password.trim().length()==0){
            this.password = "";
        }else{
            //空白の削除
            String password1 = password.replace(" ", "");
            String password2 = password1.replace("　", "");
            this.password = password2;
        }
    }
    
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        //空文字(未入力)の場合空文字をセット
        if(mail == null || mail.trim().length()==0){
            this.mail = "";
        }else{
            this.mail = mail.trim();
        }
    }
    
    public String getPrefecture() {
        return prefecture;
    }
    public void setPrefecture(String prefecture) {
        //空文字(未入力)の場合空文字をセット
        if(prefecture == null || prefecture.trim().length()==0){
            this.prefecture = "";
        }else{
            this.prefecture = prefecture.trim();
        }
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        //空文字(未入力)の場合空文字をセット
        if(city == null || city.trim().length()==0){
            this.city = "";
        }else{
            this.city = city.trim();
        }
    }
        
    public String getStreetNumber() {
        return street_number;
    }
    public void setStreetNumber(String street_number) {
        //空文字(未入力)の場合空文字をセット
        if(street_number == null || street_number.trim().length()==0){
            this.street_number = "";
        }else{
            this.street_number = street_number.trim();
        }
    }
    
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        //空文字(未入力)の場合空文字をセット
        if(building == null || building.trim().length()==0){
            this.building = "";
        }else{
            this.building = building.trim();
        }
    }
    
    
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.prefecture.equals("")){
            chkList.add("prefecture");
        }
        if(this.city.equals("")){
            chkList.add("city");
        }
        if(this.street_number.equals("")){
            chkList.add("street_number");
        }
        
        return chkList;
    }
    
    
    public void UD2DTOMapping(UserDataDTO udd){
        //空欄の削除
        String name1 = this.name.replace(" ", "");
        String name2 = name1.replace("　", "");
        udd.setName(name2);
        
        udd.setPassword(this.password);
        
        //空欄の削除
        String mail1 = this.mail.replace(" ", "");
        String mail2 = mail1.replace("　", "");
        udd.setMail(mail2);
        
        //空欄の削除
        String city1 = this.city.replace(" ", "");
        String city2 = city1.replace("　", "");
        
        //空欄の削除
        String street_number1 = this.street_number.replace(" ", "");
        String street_number2 = street_number1.replace("　", "");
        
        //空欄の削除
        String building1 = this.building.replace(" ", "");
        String building2 = building1.replace("　", "");
        
        udd.setAddress(this.prefecture + " " + city2 + " " + street_number2 + " " +building2);
    }
}
