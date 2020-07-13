package control;

import model.BeanAddress;
import util.BaseException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressManager {
    public List<BeanAddress> load()throws BaseException{
        Connection conn = null;
        List<BeanAddress> re= new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from delivery where cus_id=?";
            java.sql.PreparedStatement pst;
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanAddress b = new BeanAddress();
                b.setAdd_id(rs.getInt(1));
                b.setCus_id(rs.getInt(2));
                b.setProvince(rs.getString(3));
                b.setCity(rs.getString(4));
                b.setBlock(rs.getString(5));
                b.setAdd_text(rs.getString(6));
                b.setAdd_phone(rs.getString(7));
                b.setAdd_peo_name(rs.getString(8));
                re.add(b);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return re;
    }
    public void  addAddr(String province,String city,String block,String add_text,String add_phone,String add_peo_name )throws BaseException{
        Connection conn = null;
        List<BeanAddress> re= new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into  delivery(cus_id,province,city,block,add_text,add_phone,add_peo_name) values (?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst;
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setString(2,province);
            pst.setString(3,city);
            pst.setString(4,block);
            pst.setString(5,add_text);
            pst.setString(6,add_phone);
            pst.setString(7,add_peo_name);
            pst.execute();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DbException(e);
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    }
}
