package control;

import model.BeanAppraise;
import util.BaseException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppraiseManager {
    public List<BeanAppraise> loadAll() throws BaseException{
        Connection conn = null;
        List<BeanAppraise> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            java.sql.ResultSet rs;
            sql = "select * from appraise ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                BeanAppraise b = new BeanAppraise();

                b.setFre_id(rs.getInt(1));
                b.setCus_id(rs.getInt(2));
                b.setApr_text(rs.getString(3));
                b.setApr_time(rs.getTimestamp(4));
                b.setGrade(rs.getString(5));
                b.setApr_pt(rs.getString(6));
                b.setApr_id(rs.getInt(7));
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
    public List<BeanAppraise> search(int cus_id,int fre_id) throws BaseException{
        Connection conn = null;
        List<BeanAppraise> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            java.sql.ResultSet rs;
            sql = "select * from appraise where cus_id=? ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,cus_id);
            rs = pst.executeQuery();
            while(rs.next()){
                BeanAppraise b = new BeanAppraise();

                b.setFre_id(rs.getInt(1));
                b.setCus_id(rs.getInt(2));
                b.setApr_text(rs.getString(3));
                b.setApr_time(rs.getTimestamp(4));
                b.setGrade(rs.getString(5));
                b.setApr_pt(rs.getString(6));
                b.setApr_id(rs.getInt(7));
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

}
