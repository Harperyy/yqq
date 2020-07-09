package control;

import model.BeanDiscount;
import model.BeanLTDiscount;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LTDiscountManager {
    public List<BeanLTDiscount> loadAll()throws BaseException {
        Connection conn = null;
        List<BeanLTDiscount> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from limitedtimediscount";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanLTDiscount b = new BeanLTDiscount();
                b.setLmd_id(rs.getInt(1));
                b.setFre_id(rs.getInt(2));
                b.setLmd_price(rs.getDouble(3));
                b.setLmd_count(rs.getInt(4));
                b.setLmd_start_time(rs.getTimestamp(5));
                b.setLmd_end_time(rs.getTimestamp(6));
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
    public List<BeanLTDiscount> loadUnCp(int id)throws BaseException {
        Connection conn = null;
        List<BeanLTDiscount> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from limitedtimediscount where lmd_start_time<now() and lmd_end_time>now() and fre_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanLTDiscount b = new BeanLTDiscount();
                b.setLmd_id(rs.getInt(1));
                b.setFre_id(rs.getInt(2));
                b.setLmd_price(rs.getDouble(3));
                b.setLmd_count(rs.getInt(4));
                b.setLmd_start_time(rs.getTimestamp(5));
                b.setLmd_end_time(rs.getTimestamp(6));
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
    public List<BeanLTDiscount> loadCp(int id)throws BaseException {
        Connection conn = null;
        List<BeanLTDiscount> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from limitedtimediscount where lmd_end_time<now() and fre_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanLTDiscount b = new BeanLTDiscount();
                b.setLmd_id(rs.getInt(1));
                b.setFre_id(rs.getInt(2));
                b.setLmd_price(rs.getDouble(3));
                b.setLmd_count(rs.getInt(4));
                b.setLmd_start_time(rs.getTimestamp(5));
                b.setLmd_end_time(rs.getTimestamp(6));
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
    public List<BeanLTDiscount> loadNotSt(int id)throws BaseException {
        Connection conn = null;
        List<BeanLTDiscount> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from limitedtimediscount where lmd_start_time>now()  and fre_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanLTDiscount b = new BeanLTDiscount();
                b.setLmd_id(rs.getInt(1));
                b.setFre_id(rs.getInt(2));
                b.setLmd_price(rs.getDouble(3));
                b.setLmd_count(rs.getInt(4));
                b.setLmd_start_time(rs.getTimestamp(5));
                b.setLmd_end_time(rs.getTimestamp(6));
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
    public void addLmd(int id, int count, double price, String start,String end)throws BaseException{
        Connection conn = null;
        if(start==null) throw new BusinessException("开始时间不能为空");
        if(end==null) throw new BusinessException("结束时间不能为空");
        if(count<=0) throw new BusinessException("数量不能小于等于0");

        if(price<=0) throw new BusinessException("价格不能小于等于0");
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into limitedtimediscount(fre_id,lmd_price,lmd_count,lmd_start_time,lmd_end_time) values (?,?,?,?,?)";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setDouble(2,price);
            pst.setInt(3,count);
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
    public void UpLmd(int lmdid,int id, int count, double price, String start,String end)throws BaseException{
        Connection conn = null;
        if(start==null) throw new BusinessException("开始时间不能为空");
        if(end==null) throw new BusinessException("结束时间不能为空");
        if(count<=0) throw new BusinessException("数量不能小于等于0");

        if(price<=0) throw new BusinessException("价格不能小于等于0");
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from limitedtimediscount whehe Lmd_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,lmdid);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要修改的条目");
            rs.close();
            pst.close();
            sql = "update  limitedtimediscount set fre_id=?,lmd_price=?,lmd_count=?,lmd_start_time=?,disc_lmd_end_timeend_time=? where Lmd_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setDouble(2,price);
            pst.setInt(3,count);
            pst.setTimestamp(4,java.sql.Timestamp.valueOf(start));
            pst.setTimestamp(5,java.sql.Timestamp.valueOf(end));
            pst.setInt(6,lmdid);
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
    public void DeleteLmd(int id)throws BaseException{
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            String sql = "select * from limitedtimediscount where Lmd_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要修改的条目");
            rs.close();
            pst.close();
            sql = "delete from limitedtimediscount where Lmd_id=?";
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
