package control;

import model.BeanCoupon;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponManager {
    public List<BeanCoupon> loadAll()throws BaseException{
        Connection conn = null;
        List<BeanCoupon> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from Coupon";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCoupon b = new BeanCoupon();
                b.setCu_id(rs.getInt(1));
                b.setCu_text(rs.getString(2));
                b.setCp_need_price(rs.getDouble(3));
                b.setCp_discount(rs.getDouble(4));
                b.setCp_start_time(rs.getTimestamp(5));
                b.setCp_end_time(rs.getTimestamp(6));
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
    public List<BeanCoupon> loadCp(String key)throws BaseException{
        Connection conn = null;
        List<BeanCoupon> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from Coupon where cp_end_time<now() and cp_rexr like ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCoupon b = new BeanCoupon();
                b.setCu_id(rs.getInt(1));
                b.setCu_text(rs.getString(2));
                b.setCp_need_price(rs.getDouble(3));
                b.setCp_discount(rs.getDouble(4));
                b.setCp_start_time(rs.getTimestamp(5));
                b.setCp_end_time(rs.getTimestamp(6));
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
    public List<BeanCoupon> loadUncp(String key)throws BaseException{
        Connection conn = null;
        List<BeanCoupon> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from Coupon where cp_start_time<now() and cp_end_time>now() and cp_text like?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCoupon b = new BeanCoupon();
                b.setCu_id(rs.getInt(1));
                b.setCu_text(rs.getString(2));
                b.setCp_need_price(rs.getDouble(3));
                b.setCp_discount(rs.getDouble(4));
                b.setCp_start_time(rs.getTimestamp(5));
                b.setCp_end_time(rs.getTimestamp(6));
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

    public List<BeanCoupon> loadNotSt(String key)throws BaseException{
        Connection conn = null;
        List<BeanCoupon> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from Coupon where cp_start_time>now() and cp_text like ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCoupon b = new BeanCoupon();
                b.setCu_id(rs.getInt(1));
                b.setCu_text(rs.getString(2));
                b.setCp_need_price(rs.getDouble(3));
                b.setCp_discount(rs.getDouble(4));
                b.setCp_start_time(rs.getTimestamp(5));
                b.setCp_end_time(rs.getTimestamp(6));
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
    public void addCou(String text, double price, double dis, String start,String end)throws BaseException{
        Connection conn = null;
        if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        if(start==null) throw new BusinessException("开始时间不能为空");
        if(end==null) throw new BusinessException("结束时间不能为空");
        if(price<=dis) throw new BusinessException("折扣金额必须小于适用金额");
        //if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into Coupon(cp_text,cp_need_price,cp_discount,cp_start_time,cp_end_time) values (?,?,?,?,?)";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,text);
            pst.setDouble(2,price);
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
    public void UpCou(int id,String text, double price, double dis, String start,String end)throws BaseException{
        Connection conn = null;
        if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        if(start==null) throw new BusinessException("开始时间不能为空");
        if(end==null) throw new BusinessException("结束时间不能为空");
        if(price<=dis) throw new BusinessException("折扣金额必须小于适用金额");
        //if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from Coupon where cus_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要修改的条目");
            rs.close();
            pst.close();
            sql = "update  Coupon set cp_text=?,cp_need_price=?,cp_discount=?,cp_start_time=?,cp_end_time=? where cou_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,text);
            pst.setDouble(2,price);
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
    public void DeleteCou(int id)throws BaseException{
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            String sql = "select * from coupon where cou_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要删除的条目");
            rs.close();
            pst.close();
            sql = "delete from coupon where cou_id=?";
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
