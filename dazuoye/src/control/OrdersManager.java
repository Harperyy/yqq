package control;

import model.BeanFresh;
import model.BeanOrdDetail;
import model.BeanOrder;
import util.BaseException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersManager {
    public List<BeanOrder> loadAll(String key) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f where  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.cus_id=?  and f.fre_name like  ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());

            pst.setString(2,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BeanOrder b =new BeanOrder();
                b.setOrd_id(rs.getInt(1));
                b.setOrd_start_price(rs.getDouble(3));
                b.setOrd_final_price(rs.getDouble(4));
                b.setCp_id(rs.getInt(5));
                b.setOrd_time(rs.getTimestamp(6));
                b.setAdd_id(rs.getInt(7));
                b.setOrd_state(rs.getString(8));
                b.setFre_name(rs.getString(17));
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
        return  re;
    }
    public List<BeanOrder> loadX(String key) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f where  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.cus_id=? and o1.ord_state=? and f.fre_name like  ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setString(2,"下单");
            pst.setString(3,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BeanOrder b =new BeanOrder();
                b.setOrd_id(rs.getInt(1));
                b.setOrd_start_price(rs.getDouble(3));
                b.setOrd_final_price(rs.getDouble(4));
                b.setCp_id(rs.getInt(5));
                b.setOrd_time(rs.getTimestamp(6));
                b.setAdd_id(rs.getInt(7));
                b.setOrd_state(rs.getString(8));
                b.setFre_name(rs.getString(17));
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
        return  re;
    }
    public List<BeanOrder> loadP(String key) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f where  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.cus_id=? and o1.ord_state=? and f.fre_name like  ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setString(2,"配送");
            pst.setString(3,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BeanOrder b =new BeanOrder();
                b.setOrd_id(rs.getInt(1));
                b.setOrd_start_price(rs.getDouble(3));
                b.setOrd_final_price(rs.getDouble(4));
                b.setCp_id(rs.getInt(5));
                b.setOrd_time(rs.getTimestamp(6));
                b.setAdd_id(rs.getInt(7));
                b.setOrd_state(rs.getString(8));
                b.setFre_name(rs.getString(17));
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
        return  re;
    }
    public List<BeanOrder> loadS(String key) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f where  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.cus_id=? and o1.ord_state=? and f.fre_name like  ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setString(2,"送达");
            pst.setString(3,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BeanOrder b =new BeanOrder();
                b.setOrd_id(rs.getInt(1));
                b.setOrd_start_price(rs.getDouble(3));
                b.setOrd_final_price(rs.getDouble(4));
                b.setCp_id(rs.getInt(5));
                b.setOrd_time(rs.getTimestamp(6));
                b.setAdd_id(rs.getInt(7));
                b.setOrd_state(rs.getString(8));
                b.setFre_name(rs.getString(17));
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
        return  re;
    }
    public List<BeanOrder> loadT(String key) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f where  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.cus_id=? and o1.ord_state=? and f.fre_name like  ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setString(2,"退货");
            pst.setString(3,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BeanOrder b =new BeanOrder();
                b.setOrd_id(rs.getInt(1));
                b.setOrd_start_price(rs.getDouble(3));
                b.setOrd_final_price(rs.getDouble(4));
                b.setCp_id(rs.getInt(5));
                b.setOrd_time(rs.getTimestamp(6));
                b.setAdd_id(rs.getInt(7));
                b.setOrd_state(rs.getString(8));
                b.setFre_name(rs.getString(17));
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
        return  re;
    }
    public List<BeanOrder> loadC(String key) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f where  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.cus_id=? and (o1.ord_state=? or o1.ord_state=?) and f.fre_name like  ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setString(2,"订单完成");
            pst.setString(3,"订单取消");
            pst.setString(4,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){
                BeanOrder b =new BeanOrder();
                b.setOrd_id(rs.getInt(1));
                b.setOrd_start_price(rs.getDouble(3));
                b.setOrd_final_price(rs.getDouble(4));
                b.setCp_id(rs.getInt(5));
                b.setOrd_time(rs.getTimestamp(6));
                b.setAdd_id(rs.getInt(7));
                b.setOrd_state(rs.getString(8));
                b.setFre_name(rs.getString(17));
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
        return  re;
    }

    public void UpX(int id) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "update orders(ord_state) values ('送达') where ord_id=? and ord_state=? ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,"下单");
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
    public void UpP(int id) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "update orders(ord_state) values ('送达') where ord_id=? and ord_state=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,"配送");
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
    public void UpS (int id) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "update orders(ord_state) values ('订单完成') where ord_id=? and ord_state=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,"送达");
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
    public void UpS2 (int id) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "update orders(ord_state) values ('退货') where ord_id=? and ord_state=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,"送达");
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
    public void UpT(int id) throws BaseException{
        Connection conn = null;
        List<BeanOrder> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "update orders(ord_state) values ('订单取消') where ord_id=? and ord_state=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,"退货");
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
    public BeanOrdDetail loadDetail(int id) throws BaseException{
        Connection conn = null;
        BeanOrdDetail b =new BeanOrdDetail();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "select * from orders o1,orderdetail o2,fresh f,delivery d where o1.add_id=d.add_id and  o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and  o1.ord_id=? ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()){

                b.setAddress(rs.getString(29));
                b.setCount(rs.getInt(12));
                b.setFinal_price(rs.getDouble(4));
                b.setStart_price(rs.getDouble(3));
                b.setTime(rs.getTimestamp(6));
                b.setFre_name(rs.getString(17));
                b.setDisc_id(rs.getInt(15));
                b.setCou_id(rs.getInt(5));
                b.setCou_dis(rs.getDouble(3)-rs.getDouble(4)-rs.getDouble(14));
                b.setDisc_dis(rs.getDouble(14));
                rs.close();
                pst.close();


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
        return b;
    }
}
