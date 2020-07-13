package control;

import model.BeanMenu;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    public List<BeanMenu> loadAll(String key)throws BaseException{
        Connection conn = null;
        List<BeanMenu> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from menu where Menu_name like  ? and Menu_material like ? and Menu_step like ? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+key+"%");
            pst.setString(2,"%"+key+"%");
            pst.setString(3,"%"+key+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanMenu b = new BeanMenu();
                b.setMenu_id(rs.getInt(1));
                b.setMenu_name(rs.getString(2));
                b.setMenu_material(rs.getString(3));
                b.setMenu_pt(rs.getString(5));
                b.setMenu_step(rs.getString(4));
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
    public List<BeanMenu> load(int id )throws BaseException{
        Connection conn = null;
        List<BeanMenu> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from menu m1,menurecommend m2 where m1.Menu_id=m2.menu_id and m2.fre_id=? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanMenu b = new BeanMenu();
                b.setMenu_id(rs.getInt(1));
                b.setMenu_name(rs.getString(2));
                b.setMenu_material(rs.getString(3));
                b.setMenu_pt(rs.getString(5));
                b.setMenu_step(rs.getString(4));
                b.setMenu_text(rs.getString(8));
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
    public List<BeanMenu> search(String key1,String key2,String key3)throws BaseException{
        Connection conn = null;
        List<BeanMenu> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from menu where Menu_name like  ? and Menu_material like ? and Menu_step like ? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+key1+"%");
            pst.setString(2,"%"+key2+"%");
            pst.setString(3,"%"+key3+"%");
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanMenu b = new BeanMenu();
                b.setMenu_id(rs.getInt(1));
                b.setMenu_name(rs.getString(2));
                b.setMenu_material(rs.getString(3));
                b.setMenu_pt(rs.getString(5));
                b.setMenu_step(rs.getString(4));
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
    public  void add(String name, String m,String pt,String step)throws BaseException{
        Connection conn = null;
        if("".equals(name)||name==null) throw  new BusinessException("名称不能为空");
        if("".equals(m)||m==null) throw  new BusinessException("材料不能为空");
        if("".equals(pt)||pt==null) throw  new BusinessException("步骤不能为空");
        //if("".equals(step)||step==null) throw  new BusinessException("图片不能为空");

        try{
            conn = DBUtil.getConnection();
            String sql = "insert into menu(Menu_name,Menu_material,Menu_step,Menu_pt) values(?,?,?,'无')";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,m);
            pst.setString(3,pt);
           // pst.setString(4,step);
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
    public  void Up(int id,String name, String m,String pt,String step)throws BaseException{
        Connection conn = null;
        if("".equals(name)||name==null) throw  new BusinessException("名称不能为空");
        if("".equals(m)||m==null) throw  new BusinessException("材料不能为空");
        if("".equals(pt)||pt==null) throw  new BusinessException("步骤不能为空");
        //if("".equals(step)||step==null) throw  new BusinessException("图片不能为空");

        try{
            conn = DBUtil.getConnection();
            String sql = "select  * from menu  where menu_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw  new BusinessException("请选择要删除的条目");
            rs.close();pst.close();
            sql = "update menu set Menu_name=?,Menu_material=?,Menu_step=?,menu_pt='无' where menu_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,m);
            pst.setString(3,pt);
            //pst.setString(4,step);
            pst.setInt(4,id);
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
    public  void Delete(int id)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql;
            java.sql.PreparedStatement pst;
            java.sql.ResultSet rs;
            sql = "select * from menu where menu_id=?";
            pst  = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(!rs.next())throw  new BusinessException("请选择要删除的条目");
            rs.close();pst.close();
            sql="select * from menurecommend where menu_id=?";
            pst  = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()){
                rs.close();pst.close();
                sql = "delete from menurecommend where menu_id=?";
                pst  = conn.prepareStatement(sql);
                pst.setInt(1,id);
                pst.execute();
            }
            rs.close();pst.close();
            sql = "delete from menu where menu_id=?";
            pst  = conn.prepareStatement(sql);
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
    public  void addGoods(int fre_id,int menu_id,String text)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into menurecommend(fre_id,menu_id,rcd_text) values(?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,fre_id);
            pst.setInt(2,menu_id);
            pst.setString(3,text);
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
