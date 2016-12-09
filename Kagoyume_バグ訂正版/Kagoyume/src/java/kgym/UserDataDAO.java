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
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     */
    public void insert(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());
            st.setString(3, udd.getMail());
            st.setString(4, udd.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
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
     * 　ログイン処理のために、nameとpasswordを使って検索を行う
     */
    public UserDataDTO login(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM user_t WHERE name = ? AND password = ? AND deleteFlg = ?");
            st.setString(1, udd.getName());
            st.setString(2, udd.getPassword());
            st.setInt(3, 0);
            
            ResultSet rs = st.executeQuery();
            UserDataDTO resultUD = new UserDataDTO();
            if(rs.next()){
                resultUD.setUserID(rs.getInt(1));
                resultUD.setName(rs.getString(2));
                resultUD.setPassword(rs.getString(3));
                resultUD.setMail(rs.getString(4));
                resultUD.setAddress(rs.getString(5));
                resultUD.setTotal(rs.getInt(6));
                resultUD.setNewDate(rs.getTimestamp(7));
                resultUD.setDeleteFlg(rs.getInt(8));
            }else{
                resultUD = null;
            }            
            System.out.println("search completed");

            return resultUD;
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
     * 　データを更新後の確認のために、更新した行をuserIDを使って検索を行う
     */
    public UserDataDTO searchByID(UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT * FROM user_t WHERE userID = ?");
            st.setInt(1, udd.getUserID());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUD = new UserDataDTO();
            resultUD.setUserID(rs.getInt(1));
            resultUD.setName(rs.getString(2));
            resultUD.setPassword(rs.getString(3));
            resultUD.setMail(rs.getString(4));
            resultUD.setAddress(rs.getString(5));
            resultUD.setTotal(rs.getInt(6));
            resultUD.setNewDate(rs.getTimestamp(7));
            resultUD.setDeleteFlg(rs.getInt(8));
                        
            System.out.println("search completed");

            return resultUD;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /* 1件のデータの削除処理を行う。 */
    public void delete(UserDataDTO udd) throws SQLException{    
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            
            //deleteFlgを1にする
            String sql_flg = "UPDATE user_t SET deleteFlg = 1 WHERE userID = ?";
            
            st =  con.prepareStatement(sql_flg);
            st.setInt(1, udd.getUserID());
            
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
    
    /* テーブルの更新を行う */
    public void update(ArrayList<String> alterList,UserDataDTO udd) throws SQLException{
        Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            
            String sql = "UPDATE user_t SET ";
            
            //ArrayListに値が格納されているかの情報を、flagで管理
            boolean flag1 ,flag2 ,flag3 ,flag4;
            flag1 = flag2 = flag3 = flag4 = false;
            
            if(alterList.contains("name")) flag1 = true;
            if(alterList.contains("password")) flag2 = true;
            if(alterList.contains("mail")) flag3 = true;
            if(alterList.contains("address")) flag4 = true;
            
            //flagで場合分けして、SQL文に代入する値を決定
            if(flag1){
                sql += "name = ?";
            }
            if(flag2){
                if(!flag1){
                    sql += "password = ?";
                }else{
                    sql += ",password = ?";
                }
            }
            if(flag3){
                if(!flag1 && !flag2){
                    sql += "mail = ?";
                }else{
                    sql += ",mail = ?";
                }
            }
            if(flag4){
                if(!flag1 && !flag2 && !flag3){
                    sql += "address = ?";
                }else{
                    sql += ",address = ?";
                }
            }
            
            sql += " WHERE userID = ?";
            
            //flagで場合分けして、SQL文に代入する
            st =  con.prepareStatement(sql);
            if(flag1)
                st.setString(1, udd.getName());           
            if(flag2)
                st.setString(alterList.indexOf("password") + 1, udd.getPassword());
            if(flag3)
                st.setString(alterList.indexOf("mail") + 1, udd.getMail());
            if(flag4)
                st.setString(alterList.indexOf("address") + 1, udd.getAddress());
            st.setInt(alterList.size() + 1, udd.getUserID());
            st.executeUpdate();
            
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    //テーブル内の合計金額の更新をする
    public void totalUpdate(UserDataDTO udd) throws SQLException{
      Connection con = null;
        PreparedStatement st;
        try{
            con = DBManager.getConnection();
            
            String sql = "UPDATE user_t SET total = ? WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, udd.getTotal()); 
            st.setInt(2, udd.getUserID());
            st.executeUpdate();
            
            System.out.println("update completed");
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
