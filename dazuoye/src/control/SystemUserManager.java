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
        if("".equals(userid)||userid==null){
            throw new BusinessException("请输入用户名");
        }

        Connection conn = null;
        try {
            conn= DBUtil.getConnection();
            String sql;
            if(type == 0)
                sql = "select * from Administrater where ad_name=?";
            else sql = "select * from Customer where cus_name=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,userid);
            java.sql.ResultSet rs=pst.executeQuery();
            if(!rs.next()) throw new BusinessException("登陆账号不存在");
            BeanSystemUser u = new BeanSystemUser();
            u.setId(rs.getInt(1));
            u.setName(userid);
            u.setPwd(rs.getString(3));
            //System.out.println(rs.getString(2)+"   "+rs.getString(3));
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
    public BeanSystemUser regUser(String userid, String pwd1, String pwd2,int type) throws BaseException{

        if(type!=1 && type!= 0){
            throw  new BusinessException("请选择账户类型");
        }
        if("".equals(userid)||userid==null){
            throw new BusinessException("请输入用户名");
        }
        if("".equals(pwd1)||pwd1==null||"".equals(pwd2)||pwd2==null){
            throw new BusinessException("请输入密码");
        }
        if(!pwd1.equals(pwd2)){
            throw new BusinessException("两次密码不一致");
        }
        if(pwd1.length()<8) {
            throw new BusinessException("密码长度必须大于等于8位");
        }
        Connection conn = null;

        try {
            conn= DBUtil.getConnection();
            String sql;
            if(type == 0)
                sql = "select * from Administrater where ad_name=?";
            else sql = "select * from Customer where cus_name=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,userid);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("登陆账号已存在");
            rs.close();
            pst.close();
            if(type == 0)
                sql = "insert into Administrater(ad_name,ad_pwd) values (?,?)";
            else sql = "insert into Customer(cus_name,cus_pwd,cus_reg_time,cus_vip) values (?,?,now(),'不是')";
            pst = conn.prepareStatement(sql);
            pst.setString(1,userid);
            pst.setString(2,pwd1);
            pst.execute();
            BeanSystemUser u = new BeanSystemUser();
            u.setName(userid);
            if(type==0) u.setType("管理员");
            else u.setType("用户");
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
    public  void regCus(String name,String sex,String phone,String email ,String city) throws BaseException{
        if("".equals(sex)||sex==null) throw  new BusinessException("请选择性别");
        if("".equals(phone)||phone==null) throw  new BusinessException("请填写电话");
        if("".equals(email)||email==null) throw  new BusinessException("请填写邮箱");
        if("".equals(city)||city==null) throw  new BusinessException("请填写城市");
        Connection conn = null;
        String sql;
        java.sql.PreparedStatement pst;
        java.sql.ResultSet rs;
        try{
            conn = DBUtil.getConnection();
            sql = "update Customer set cus_sex=?,cus_phone = ?,cus_email = ?,cus_city = ? where cus_name = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,sex);
            pst.setString(2,phone);
            pst.setString(3,email);
            pst.setString(4,city);
            pst.setString(5,name);
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
