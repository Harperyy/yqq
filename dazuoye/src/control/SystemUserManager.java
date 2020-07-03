package control;


import model.BeanAdministrater;
import model.BeanCustomer;
import model.BeanSystemUser;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class SystemUserManager {
    public static BeanSystemUser currentUser = null;
    public BeanSystemUser loadUser(String userid,int type)throws BaseException {
        if(type != 0&&type != 1) throw  new BusinessException("请选择账户类型");
        Connection conn = null;
        try {
            conn= DBUtil.getConnection();
            String sql;
            if(type == 0)
                sql = "select * from Administrater where ad_id=?";
            else sql = "select * from Customer where cus_name=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,userid);
            java.sql.ResultSet rs=pst.executeQuery();
            if(!rs.next()) throw new BusinessException("登陆账号不存在");
            BeanSystemUser u = new BeanSystemUser();
            u.setId_name(userid);
            u.setPwd(rs.getString(3));
            System.out.println(rs.getString(2)+"   "+rs.getString(3));
            if(type==0) u.setType("管理员");
            else u.setType("用户");
            rs.close();
            pst.close();

            return u;
        } catch (SQLException e) {
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
