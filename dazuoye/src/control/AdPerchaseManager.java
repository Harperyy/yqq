package control;

import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class AdPerchaseManager {
    public void add(int id,int count) throws BaseException{
        Connection conn = null;
        if(count<=0) throw  new BusinessException("采购数量必须大于0");
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from fresh where fre_id =?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("没有该类食材");
            sql = "insert into administraterperchase(fre_id,sp_count,sp_state) values (?,?,'已下单')";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setInt(2,count);
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
    public void onWay(int id)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from administraterperchase where sp_id =?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("没有该采购订单");
            sql = "update administraterperchase set sp_state='已在途' where sp_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
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
    public void Complete(int id,int fre_id,int cnt)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "update fresh set fre_count = fre_count+? where fre_id =?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,cnt);
            pst.setInt(2,fre_id);
            pst.execute();
            sql = "update administraterperchase set sp_state='已在库' where sp_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
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
