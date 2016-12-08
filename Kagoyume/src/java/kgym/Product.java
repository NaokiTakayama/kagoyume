package kgym;

/**
 *
 * @author Naoki
 */
public class Product {
    private String name;
    private String productID;
    private String description;
    private int price ;
    private String img;
    private String img_large;
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getProductID(){
        return this.productID;
    }
    
    public void setProductID(String id){
        this.productID = id;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    
    public int getPrice(){
        return this.price;
    }  
    
    public void setPrice(String price){
        this.price = Integer.parseInt(price);
    }   

    public String getImage(){
        return this.img;
    } 
    
    public void setImage(String img){
        this.img = img;
    } 
    
    public String getImgLarge(){
        return this.img_large;
    }
    
    public void setImgLarge(String img_large){
        this.img_large = img_large;
    }
}
