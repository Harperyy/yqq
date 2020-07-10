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

public class FreshManager {
        public static BeanFresh currentFre = null;
     public List<BeanFresh> loadAll()throws BaseException {

        Connection conn = null;
        List<BeanFresh> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from fresh";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanFresh b = new BeanFresh();
                b.setFre_id(rs.getInt(1));
                b.setFre_name(rs.getString(2));
                b.setFre_price(rs.getDouble(3));
                b.setFre_vip_price(rs.getDouble(4));
                b.setFre_count(rs.getInt(5));
                b.setFre_size(rs.getDouble(6));

                b.setFre_remark(rs .getString(7));
                b.setFp_id(rs.getInt(8));

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
    public void addFre(String name,double price,double vip_p,double size,String remark,int tf_id)throws BaseException{
        Connection conn = null;
        if("".equals(name)||name==null) throw new BusinessException("名字不能为空");
        if("".equals(remark)||remark==null) throw new BusinessException("描述不能为空");
        if(price<=0) throw new BusinessException("价格必须大于0");
        if(vip_p<=0)throw new BusinessException("vip价格必须大于0");
        //if(count<=0)throw new BusinessException("数量必须大于0");
        if(size<=0)throw new BusinessException("规格必须大于0");


        try{
            conn = DBUtil.getConnection();
            String sql = "select * from freshtype where fp_id=?";

            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,tf_id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("此商品类别不存在，无法添加没有");
            sql = "insert into fresh(Fre_name,Fre_price,Fre_vip_price,Fre_count,Fre_size,Fre_remark,FP_id) values (?,?,?,0,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setDouble(2,price);
            pst.setDouble(3,vip_p);
            //pst.setInt(4,count);
            pst.setDouble(4,size);
            pst.setString(5,remark);
            pst.setInt(6,tf_id);
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
    public void UpFre(int id,String name,double price,double vip_p,double size,String remark)throws BaseException{
        Connection conn = null;

        if("".equals(name)||name==null) throw new BusinessException("名字不能为空");
        if("".equals(remark)||remark==null) throw new BusinessException("描述不能为空");
        if(price<=0) throw new BusinessException("价格必须大于0");
        if(vip_p<=0)throw new BusinessException("vip价格必须大于0");
       // if(count<=0)throw new BusinessException("数量必须大于0");
        if(size<=0)throw new BusinessException("规格必须大于0");

        //if("".equals(text)||text==null) throw new BusinessException("内容不能为空");
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from fresh where fre_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            java.sql.ResultSet rs = pst.executeQuery();
            if(!rs.next()) throw new BusinessException("请选择要修改的条目");
            rs.close();
            pst.close();
            sql = "update  fresh set fre_name=?,Fre_price=?,Fre_vip_price=?,Fre_size=?,fre_remark=?where fre_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,name);

            pst.setDouble(2,price);
            pst.setDouble(3,vip_p);

            pst.setDouble(4,size);
            pst.setString(5,remark);
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
    public void DeleteFre(int id)throws BaseException{
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
            sql = "select * from menurecommend where fre_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()) {
                rs.close();
                pst.close();
                sql = "delete from menurecommend where fre_id=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1,id);
                pst.execute();

            }
            rs.close();
            pst.close();
            sql = "select * from appraise where fre_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()){
                rs.close();
                pst.close();
                sql = "delete from appraise where fre_id=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1,id);
                pst.execute();
            }
            rs.close();
            pst.close();
            sql = "select * from discount_fresh where fre_id=?and df_end_time>now() and df_start_time<now()";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("该商品已参加满减活动，暂时无法删除");
            rs.close();
            pst.close();
            sql = "select * from limitedtimediscount where fre_id=? and lmd_end_time>now() and lmd_start_time<now()";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("该商品已参与促销，暂时无法删除");
            rs.close();
            pst.close();
            sql = "select * from orderdetail o1,orders o2 where o1.ord_id=o2.ord_id and o2.fre_id=? and o1.ord_state!='交易成功'";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("该商品已有订单，暂时无法删除");
            rs.close();
            pst.close();
            sql = "select * from administraterperchase where fre_id=? and sp_state!='已在库'";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()) throw new BusinessException("该商品已有订单，暂时无法删除");
            rs.close();
            pst.close();
            sql = "delete from fresh where fre_id=?";
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
