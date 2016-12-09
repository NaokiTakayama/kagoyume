
package kgym;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 */
public class BuyDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static BuyDataDAO getInstance(){
        return new BuyDataDAO();
    }
    
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     */
    public void insert_buy(BuyDataDTO bdd) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
            st.setInt(1, bdd.getUserID());
            st.setString(2, bdd.getItemCode());
            st.setInt(3, bdd.getType());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    
    /**
     * 　購入履歴表示のために、userIDを使って検索を行う
     */
    public ArrayList<BuyDataDTO> recordSearch(int userID) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
            st.setInt(1, userID);
            
            ResultSet rs = st.executeQuery();
            
            ArrayList<BuyDataDTO> record = new ArrayList<>();
            
            while(rs.next()){
                BuyDataDTO resultBD = new BuyDataDTO();
                resultBD.setBuyID(rs.getInt(1));
                resultBD.setUserID(rs.getInt(2));
                resultBD.setItemCode(rs.getString(3));
                resultBD.setType(rs.getInt(4));
                resultBD.setBuyDate(rs.getTimestamp(5));
                
                record.add(resultBD);
            }
            
            System.out.println("search completed");

            return record;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /* userIDに該当するデータの削除処理を行う。 */
    public void delete_buy(int userID) throws SQLException{    
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            
            String sql = "DELETE FROM buy_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, userID);
            
            st.executeUpdate();
            
            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    
}
