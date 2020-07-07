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
    public List<BeanMenu> loadAll()throws BaseException{
        Connection conn = null;
        List<BeanMenu> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from menu";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
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
        if("".equals(step)||step==null) throw  new BusinessException("图片不能为空");

        try{
            conn = DBUtil.getConnection();
            String sql = "insert into menu(Menu_name,Menu_material,Menu_step,Menu_pt) values(?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,m);
            pst.setString(3,pt);
            pst.setString(4,step);
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
        if("".equals(step)||step==null) throw  new BusinessException("图片不能为空");

        try{
            conn = DBUtil.getConnection();
            String sql = "select  * menu  where menu_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw  new BusinessException("请选择要删除的条目");
            rs.cancelRowUpdates();pst.close();
            sql = "update menu set Menu_name=?,Menu_material=?,Menu_step=?,Menu_pt=? where menu_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,m);
            pst.setString(3,pt);
            pst.setString(4,step);
            pst.setInt(5,id);
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
}
