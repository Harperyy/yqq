package control;

import model.BeanAdPerchase;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdPerchaseManager {
    public List<BeanAdPerchase> loadAll()throws BaseException{
        Connection conn = null;
        List<BeanAdPerchase> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select a.sp_id,f.fre_id,a.sp_count,a.sp_state,f.fre_name from administraterperchase a,fresh f where a.fre_id=f.fre_id ";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs =  pst.executeQuery();
            while (rs.next()){
                BeanAdPerchase b = new BeanAdPerchase();
                b.setSp_id(rs.getInt(1));
                b.setFre_id(rs.getInt(2));
                b.setSp_count(rs.getInt(3));
                b.setSp_state(rs.getString(4));
                b.setName(rs.getString(5));
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
            if(!rs.next()){ throw new BusinessException("没有该类食材");}
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
    public List<BeanAdPerchase> query(String name, int type)throws BaseException{
        Connection conn = null;
        List<BeanAdPerchase> re = new ArrayList<>();
        if(type==-1)throw  new BusinessException("请选择订单类型");
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            if(type==0) sql = "select a.sp_id,f.fre_name,a.count,a.state from fresh f,administraterperchase a where f.fre_id=a.fre_id and a.state='已下单' and f.fre_name=?";
            else if(type==1)sql = "select a.sp_id,f.fre_name,a.count,a.state from fresh f,administraterperchase a where f.fre_id=a.fre_id and a.state='已在途'and f.fre_name=?";
            else if(type==2)sql = "select a.sp_id,f.fre_name,a.count,a.state from fresh f,administraterperchase a where f.fre_id=a.fre_id and a.state='已在库'and f.fre_name=?";
            else sql = "select a.sp_id,f.fre_name,a.count,a.state from fresh f,administraterperchase a where f.fre_id=a.fre_id and f.fre_name=?";
            //sql = "update fresh set fre_count = fre_count+? where fre_id =?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+name+"%");
            java.sql.ResultSet rs=pst.executeQuery();
            while(rs.next()){
                BeanAdPerchase b = new BeanAdPerchase();
                b.setSp_id(rs.getInt(1));
                b.setName(rs.getString(2));
                b.setSp_count(rs.getInt(3));
                b.setSp_state(rs.getString(4));
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
