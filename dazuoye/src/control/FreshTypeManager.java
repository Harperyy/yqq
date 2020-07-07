package control;

import model.BeanFresh;
import model.BeanFreshType;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FreshTypeManager {
    public List<BeanFreshType> loadAll()throws BaseException {
        Connection conn = null;
        List<BeanFreshType> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from freshtype";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanFreshType b = new BeanFreshType();
                b.setFP_id(rs.getInt(1));
                b.setFP_name(rs.getString(2));
                b.setFP_remark(rs.getString(3));

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
    public void addFT(String name,String remark)throws BaseException{
        Connection conn = null;
        if("".equals(name)||name==null) throw new BusinessException("名字不能为空");
        if("".equals(remark)||remark==null) throw new BusinessException("描述不能为空");

        try{
            conn = DBUtil.getConnection();
            String sql = "insert into freshtype(FP_name,FP_remark) values (?,?)";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,remark);
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
    public void UpTF(int id,String name,String remark)throws BaseException{
        Connection conn = null;
        if("".equals(name)||name==null) throw new BusinessException("名字不能为空");
        if("".equals(remark)||remark==null) throw new BusinessException("描述不能为空");

        //if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from freshtype where TF_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要修改的条目");
            rs.close();
            pst.close();
            sql = "update  freshtype set FP_name=?,FP_remark=?where TF_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,remark);

            pst.setInt(3,id);
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
    public void DeleteTF(int id)throws BaseException{
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            String sql = "select * from freshtype where TF_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要删除的条目");
            rs.close();
            pst.close();
            sql = "select * from fresh where tf_id=? and fre_count<=0";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("给类别下已有食材，无法删除");
            rs.close();
            pst.close();
            sql = "delete from freshtype where tf_id=?";
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
