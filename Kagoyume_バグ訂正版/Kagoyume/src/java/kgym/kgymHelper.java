package kgym;

import java.util.ArrayList;

/**
 *
 * @author Naoki
 */
public class kgymHelper {
    
    public static kgymHelper getInstance(){
        return new kgymHelper();
    }
    
    //トップへのリンクを返却
    public String home(){
        return "<a href=top.jsp>トップへ戻る</a>";
    }
    
    /*
     *　県名は数字で取り扱っているので画面に表示するときは日本語に変換
     */
    public String[] exPrefnum(){
        String[] Pref = {"北海道","青森県","岩手県","宮城県","秋田県","山形県","福島県",
                        "茨城県","栃木県","群馬県","埼玉県","千葉県","東京都","神奈川県",
                        "新潟県","富山県","石川県","福井県","山梨県","長野県","岐阜県",
                        "静岡県","愛知県","三重県","滋賀県","京都府","大阪府","兵庫県",
                        "奈良県","和歌山県","鳥取県","島根県","岡山県","広島県","山口県",
                        "徳島県","香川県","愛媛県","高知県","福岡県","佐賀県","長崎県",
                        "熊本県","大分県","宮崎県","鹿児島県","沖縄県"
                        };
        return Pref;
    }
    
    //カートにて、合計金額を求める
    public int totalOfCart(ArrayList<Product> cartList){
        int total = 0;
        
        for(int i=0;i<cartList.size();i++){
            total += cartList.get(i).getPrice();
        }
        
        return total;
    }
    
    //発送方法を数字で扱っているため
    public String exTypenum(int i){
        switch(i){
            case 1:
                return "宅配便";
            case 2:
                return "クール便";
            case 3:
                return "コンビニ店頭受け取り";
        }
        return "";
    }
    
    /*
    * 　セッションやリクエストパラメータの有無を判定
    *　主にjspファイル内の場合分けの際の、コード短縮のために用いる
    */
    public boolean check_null(Object str){
        if(str == null){
            return false;
        }
        
        return true;
    }
    
    /**
     * 入力されたデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が
     * 未入力なのかのhtml文を返却する
     */
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output +="パスワード";
                }
                if(val.equals("mail")){
                    output +="E-mail";
                }
                if(val.equals("prefecture")){
                    output +="県名";
                }
                if(val.equals("city")){
                    output +="市町村名";
                }
                if(val.equals("street_number")){
                    output +="番地";
                }
                
                output +="が未記入です<br>";
            }
        return output;
    }
    
    //backPageのセッションの中身を場合分け
    public String session_chk(String ses){
        if(ses.equals("Add")){
            return "item.jsp";
        }else{
            return ses;
        }
    }
}
