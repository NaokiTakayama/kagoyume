
package kgym;

/**
 *
 * @author Naoki
 */
public class BuyData {
    private int userID;
    private String itemCode;
    private int type;
    
    public int getUserID(){
        return this.userID;
    }
    
    public void setUserID(int userID){
        this.userID = userID;
    }
    
    public String getItemCode(){
        return this.itemCode;
    }
    
    public void setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    
    public int getType(){
        return this.type;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public void BD2DTOMapping(BuyDataDTO bdd){
        bdd.setUserID(this.userID);
        bdd.setItemCode(this.itemCode);
        bdd.setType(this.type);
    }
}
