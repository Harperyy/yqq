package control;

import model.BeanAppraise;
import model.BeanNotApr;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppraiseManager {
    public List<BeanAppraise> loadAll(String key) throws BaseException{
        Connection conn = null;
        List<BeanAppraise> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            java.sql.ResultSet rs;
            sql = "select * from appraise a,fresh f where  a.fre_id=f.fre_id and fre_name like ? and cus_id=?";
            pst = conn.prepareStatement(sql);

            pst.setString(1,"%"+key+"%");
            pst.setInt(2,SystemUserManager.currentUser.getId());
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
                b.setFre_name(rs.getString(9));
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
    public List<BeanAppraise> load(int id) throws BaseException{
        Connection conn = null;
        List<BeanAppraise> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            java.sql.ResultSet rs;
            sql = "select * from appraise  where  fre_id= ? order by apr_time";
            pst = conn.prepareStatement(sql);

            pst.setInt(1,id);

            rs = pst.executeQuery();
            while(rs.next()){
                BeanAppraise b = new BeanAppraise();


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
    public List<BeanNotApr> search() throws BaseException{
        Connection conn = null;
        List<BeanNotApr> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            java.sql.ResultSet rs;
            sql = "select o1.fre_id , f.fre_name \n" +
                    "from orderdetail o1,orders o2 ,fresh f \n" +
                    "where  f.fre_id=o1.fre_id and o1.ord_id= o2.ord_id and o2.cus_id=? and o1.fre_id  not in( \n" +
                    "\n" +
                    "select appraise.fre_id fre_id \n" +
                    "from appraise,fresh where appraise.fre_id=fresh.Fre_id and appraise.cus_id=?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            pst.setInt(2,SystemUserManager.currentUser.getId());

            rs = pst.executeQuery();
            while(rs.next()){
                BeanNotApr b = new BeanNotApr();

                b.setFre_id(rs.getInt(1));
                b.setFre_name(rs.getString(2));
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
    public void addApr(int fre_id,String t,String g,String pt)throws  BaseException{
        Connection conn = null;
        if("".equals(t)||t==null) throw  new BusinessException("内容不能为空");
        if("".equals(g)||g==null) throw  new BusinessException("等级不能为空");
        //if("".equals(pt)||pt==null) throw  new BusinessException("内容不能为空");

        try {
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "insert into appraise(fre_id,cus_id,apr_text,apr_time,apr_grade,apr_pt) values(?,?,?,now(),?,?) ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,fre_id);
            pst.setInt(2,SystemUserManager.currentUser.getId());
            pst.setString(3,t);
            pst.setString(4,g);
            pst.setString(5,pt);
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
    public void UpApr(int id,String word)throws BaseException{
        Connection conn = null;
        if("".equals(word)||word == null) throw  new BusinessException("内容不能为空");
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst ;
            sql = "select apr_text from appraise where apr_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            rs.next();
            String text =rs.getString(1);
            rs.close();pst.close();
            sql = "update appraise set apr_text=?,apr_time=now() where apr_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,text+word);
            pst.setInt(2,id);
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
    public void DeleteApr(int id)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            sql = "delete from appraise where apr_id=?";
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
