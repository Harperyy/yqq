package control;

import model.BeanCoupon;
import model.BeanDiscount;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountManager {


    public List<BeanDiscount> loadAll()throws BaseException {
        Connection conn = null;
        List<BeanDiscount> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from Discount";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanDiscount b = new BeanDiscount();
                b.setDisc_id(rs.getInt(1));
                b.setDisc_text(rs.getString(2));
                b.setCount(rs.getInt(3));
                b.setDisc_discount(rs.getDouble(4));
                b.setDisc_start_time(rs.getTimestamp(5));
                b.setDisc_end_time(rs.getTimestamp(6));
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
    public void addDisc(String text, int count, double dis, String start,String end)throws BaseException{
        Connection conn = null;
        if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        if(start==null) throw new BusinessException("开始时间不能为空");
        if(end==null) throw new BusinessException("结束时间不能为空");
        if(count<=0) throw new BusinessException("数量不能小于等于0");

        if(dis<=0) throw new BusinessException("折扣不能小于等于0");
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into discount(disc_text,disc_count,disc_discount,disc_start_time,disc_end_time) values (?,?,?,?,?)";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,text);
            pst.setInt(2,count);
            pst.setDouble(3,dis);
            pst.setTimestamp(4,java.sql.Timestamp.valueOf(start));
            pst.setTimestamp(5,java.sql.Timestamp.valueOf(end));
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
    public void UpDisc(int id,String text, int count, double dis, String start,String end)throws BaseException{
        Connection conn = null;
        if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        if(start==null) throw new BusinessException("开始时间不能为空");
        if(end==null) throw new BusinessException("结束时间不能为空");
        if(count<=0) throw new BusinessException("数量不能小于等于0");

        if(dis<=0) throw new BusinessException("折扣不能小于等于0");
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from discount where disc=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next())  throw new BusinessException("请选择要删除的条目");
            rs.close();pst.close();
            sql = "update  discount set disc_text=?,disc_count=?,disc_discount=?,disc_start_time=?,disc_end_time=? where disc_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,text);
            pst.setInt(2,count);
            pst.setDouble(3,dis);
            pst.setTimestamp(4,java.sql.Timestamp.valueOf(start));
            pst.setTimestamp(5,java.sql.Timestamp.valueOf(end));
            pst.setInt(6,id);
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
    public void DeleteDisc(int id)throws BaseException{
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            String sql = "select * from discount where disc_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要删除的条目");
            rs.close();
            pst.close();
            sql = "delete from discount_fresh where disc=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            pst.close();
            sql = "delete from discount where disc_id=?";
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
