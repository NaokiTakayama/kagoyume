package kgym;

import java.util.ArrayList;
import java.net.URLEncoder;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author Naoki
 */
public class YahooAPI {
    private final String API_ID = "dj0zaiZpPURhd0VjVm5US1lDbSZzPWNvbnN1bWVyc2VjcmV0Jng9N2Q-";
    
    //商品検索のURL
    private final String shopSearchURL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch";
    
    //商品コード検索のURL
    private final String codeSearchURL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup";

    public static YahooAPI getInstance(){
      return new YahooAPI();  
    }
    
    
    //キーワードでの商品検索(おすすめ順にソート(予定)、２０件)
    public ArrayList<Product> searchForBuy(String keyword){
        try{
            //取得したkeywordをUTF-8のパーセントエンコーディング形式に変換
            String keyword_Enc = URLEncoder.encode(keyword, "UTF8"); 
            
            URL url = new URL(shopSearchURL + "?appid=" + API_ID + "&query=" + keyword_Enc + "&image_size=300");
            
            //xmlファイルの取得
            Document document= urlDocument(url);
            
            ArrayList<Product> productSearch = parseSearch(document);
            
            return productSearch;
        }catch(Exception e){
            return null;
        }
    }

    //取得したxmlファイル内の情報を取得(searchForBuyについて)
    public ArrayList<Product> parseSearch(Document document){
        try{
            ArrayList<Product> searchProduct = new ArrayList<>();
            
            //root値（ここではResultset）を取得
            Element root_resultset = document.getDocumentElement();
            
            //ノードResultの取得
            Node child_result = root_resultset.getFirstChild();
            
            NodeList resultList = child_result.getChildNodes(); 
            
            
            for(int k=0;k<resultList.getLength();k++){
                
                //Resultの子ノードを取得
                Node child_resultOf = resultList.item(k);
                
                //取得したResultの子ノードがHitかで場合分け
                if(child_resultOf.getNodeName().equals("Hit")){
                    Product product = parseOfHit(child_resultOf);
                    
                    searchProduct.add(product);
                }
            }
            
            return searchProduct;
        }catch(Exception e){
            return null;
        }
    }

    //商品コードからの情報の取得
    public ArrayList<Product> searchForRecord(ArrayList<BuyDataDTO> recordList){
        try{
            ArrayList<Product> productRecord = new ArrayList<>();
            
            for(BuyDataDTO bdd:recordList){
                
                //URL url_1 = new URL(codeSearchURL + "?appid=" + API_ID + "&itemcode=" + bdd.getItemCode() + "&responsegroup=small" );
                URL url = new URL(codeSearchURL + "?appid=" + API_ID + "&itemcode=" + bdd.getItemCode() + "&responsegroup=medium"+ "&image_size=300");

                Document recordDoc = urlDocument(url);
                
                Product record = parseRecord(recordDoc);
                
                productRecord.add(record);
            }
            return productRecord;
        }catch(Exception e){
            return null;
        }
    }

    //URLからProductインスタンスを返す(searchForRecordについて)
    public Document urlDocument(URL url) throws Exception{
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        urlconn.setRequestMethod("GET");
        urlconn.setInstanceFollowRedirects(false);
        urlconn.connect();

        InputStream is = urlconn.getInputStream();

        //xmlファイルの取得
        Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        
        urlconn.disconnect();
        
        return document;
    }
    
    
    //取得したxmlファイル内の情報を取得(searchForRecordについて)
    public Product parseRecord(Document document){
        try{    
            Product record = new Product();
            
            //root値（ここではResultset）を取得
            Element root_resultset = document.getDocumentElement();
            
            //ノードResultの取得
            Node child_result = root_resultset.getFirstChild();
            
            NodeList resultList = child_result.getChildNodes();
            
            for(int k=0;k<resultList.getLength();k++){
                
                //Resultの子ノードを取得
                Node child_resultOf = resultList.item(k);
                
                //取得したResultの子ノードがHitかで場合分け
                if(child_resultOf.getNodeName().equals("Hit")){
                    record = parseOfHit(child_resultOf);
                }
            }
            
            return record;
        }catch(Exception e){
            return null;
        }
    }
    
    //xmlファイル処理の、Hitでの処理をまとめたもの
    public Product parseOfHit(Node hit){
        Product product = new Product();
        NodeList hitList = hit.getChildNodes();

        for(int i=0;i<hitList.getLength();i++){

            //Hitの子ノードを取得
            Node child_hitOf = hitList.item(i);

            if(child_hitOf.getNodeName().equals("Name")){
               product.setName(child_hitOf.getTextContent());
            }

            if(child_hitOf.getNodeName().equals("Code")){
                product.setProductID(child_hitOf.getTextContent());
            }

            if(child_hitOf.getNodeName().equals("Description")){
                product.setDescription(child_hitOf.getTextContent());
            }

            if(child_hitOf.getNodeName().equals("Image")){

                //ノードImageを取得
                Node child_image = hitList.item(i);
                NodeList imageList = child_image.getChildNodes();

                for(int j=0;j<imageList.getLength();j++){

                    //Imageの子ノードを取得
                    Node child_imageOf = imageList.item(j);

                    if(child_imageOf.getNodeName().equals("Medium")){
                        product.setImage(child_imageOf.getTextContent());
                    }
                }
            }

            if(child_hitOf.getNodeName().equals("ExImage")){

                //ノードExImageを取得
                Node child_image = hitList.item(i);
                NodeList imageList = child_image.getChildNodes();

                for(int j=0;j<imageList.getLength();j++){

                    //ExImageの子ノードを取得
                    Node child_imageOf = imageList.item(j);

                    if(child_imageOf.getNodeName().equals("Url")){
                        product.setImgLarge(child_imageOf.getTextContent());
                    }
                }
            }

            if(child_hitOf.getNodeName().equals("Price")){
                product.setPrice(child_hitOf.getTextContent());
            }
        }
        return product;
    }
}
