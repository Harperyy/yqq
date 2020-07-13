package control;

import model.*;
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
    public List<BeanFresh> loadByType(int id)throws BaseException {

        Connection conn = null;
        List<BeanFresh> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from fresh where fp_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
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
    public List<BeanFresh> recommend()throws BaseException {

        Connection conn = null;
        List<BeanFresh> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql ;
            java.sql.PreparedStatement pst ;
            java.sql.ResultSet rs;


            sql = "select * from fresh where fre_id in(" +
                    "select f.fre_id fre_id from fresh f,orders o1,orderdetail o2 where o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and o1.cus_id=?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,SystemUserManager.currentUser.getId());
            rs= pst.executeQuery();
            int cnt=0;
            while(rs.next()){
                cnt++;
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
            if(cnt<10){
                sql = "select * from fresh where fre_id not in(" +
                        "select f.fre_id fre_id from fresh f,orders o1,orderdetail o2 where o1.ord_id=o2.ord_id and o2.fre_id=f.fre_id and o1.cus_id=?)";
                pst = conn.prepareStatement(sql);
                pst.setInt(1,SystemUserManager.currentUser.getId());
                rs= pst.executeQuery();

                while(rs.next()){
                    cnt++;
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
    public List<BeanFresh> load(String key)throws BaseException {

        Connection conn = null;
        List<BeanFresh> re = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from fresh where fre_name like ? or fre_remark like ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+key+"%");
            pst.setString(2,"%"+key+"%");
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
    public BeanFresh search(int id)throws BaseException {

        Connection conn = null;
        BeanFresh b = new BeanFresh();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from fresh f1,freshtype f2 where f1.fp_id=f2.fp_id and f1.fre_id=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);

            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){

                b.setFre_id(rs.getInt(1));
                b.setFre_name(rs.getString(2));
                b.setFre_price(rs.getDouble(3));
                b.setFre_vip_price(rs.getDouble(4));
                b.setFre_count(rs.getInt(5));
                b.setFre_size(rs.getDouble(6));

                b.setFre_remark(rs .getString(7));
                b.setFp_id(rs.getInt(8));
                b.setFp_name(rs.getString(10));


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
    public void buy(int fre_id,int count,int add_id)throws BaseException{
         Connection conn = null;
         if(count<=0) throw  new BusinessException("数量不能小于等于0");
        int cp_id=-1,disc_id=-1,lmd_id=-1,disc_count=0,lmd_count=0;
        double cp=0,disc=10,lmd=0,vip=0;
        int cnt=count;
        double p=0;
         try{
             conn = DBUtil.getConnection();
             String sql ;
             java.sql.PreparedStatement pst;
             sql = "select fre_count from fresh where fre_id=?";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,fre_id);
             java.sql.ResultSet rs = pst.executeQuery();
             rs.next();
             if(rs.getInt(1)<count ) throw new BusinessException("库存不足");
             rs.close();pst.close();
//             查找优惠券
             sql = "SELECT lmd_id,lmd_count,lmd_price from limitedtimediscount where fre_id = ? and lmd_start_time<now() and lmd_end_time>now()";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,fre_id);
              rs = pst.executeQuery();
             if(rs.next()){
                 lmd_id=rs.getInt(1);
                 lmd_count = rs.getInt(2);
                 lmd = rs.getDouble(3);

             }
             rs.close();
             pst.close();
//             查找是否是vip
             sql = "select * from customer where cus_id=? and cus_vip='是' and cus_vip_end_time>now()";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,SystemUserManager.currentUser.getId());
             rs = pst.executeQuery();
             if(rs.next()) sql = "select Fre_vip_price from fresh where fre_id=?";
             else sql = "select fre_price from fresh where fre_id=?";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,fre_id);
             rs = pst.executeQuery();
             rs.next();
             p = rs.getDouble(1);
//             比价vip与促销价
            if(p<lmd||lmd_id==-1){
                p = p*cnt;lmd_id=-1;
            }
            else{
                if(cnt<count) p = lmd*cnt;
                else p = lmd*lmd_count+p*(cnt-lmd_count);
            }
            rs.close();pst.close();
//            如果没用促销价，则可以用满折
             if(lmd_id==-1){
                 sql = "SELECT d1.disc_id , d1.disc_discount,d1.disc_count from discount d1,discount_fresh d2 where d1.disc_id=d2.disc_id and d2.fre_id = ? and d1.disc_end_time>NOW()and d1.disc_start_time <NOW() and d1.disc_count<=?";
                 pst = conn.prepareStatement(sql);
                 pst.setInt(1,fre_id);
                 pst.setInt(2,count);
                 rs = pst.executeQuery();
                 if(rs.next()){
                     disc_id = rs.getInt(1);
                     disc = rs.getDouble(2);
                     disc_count = rs.getInt(3);

                 }
             }



             rs.close();
             pst.close();
//             查找优惠券
             sql = "select cp_id,cp_discount from coupon \n" +
                     "where cp_start_time <now() and cp_end_time>now() and cp_need_price < ? \n" +
                     "having cp_discount = ( \n" +
                     "select max(cp_discount) cp_discount from coupon \n" +
                     "where cp_start_time <now() and cp_end_time>now() and cp_need_price < ?\n" +
                     ")";
             pst = conn.prepareStatement(sql);
             pst.setDouble(1,p);
             pst.setDouble(2,p);
             rs = pst.executeQuery();
             if(rs.next()){
                 cp_id=rs.getInt(1);
                 cp=rs.getDouble(2);
             }


             rs.close();
             pst.close();

//            生成订单

             sql = "insert into orders(cus_id,ord_start_price,ord_final_price,cp_id,ord_time,add_id,ord_state) values (?,?,?,?,now(),?,'下单')";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,SystemUserManager.currentUser.getId());
             pst.setDouble(2,p);
             pst.setDouble(3,p-p*(1-disc/10)-cp);
             pst.setInt(4,cp_id);
             pst.setInt(5,add_id);
             pst.execute();
             pst.close();

//             详细订单
             sql = "select max(ord_id) from orders ";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
             rs.next();
             int id = rs.getInt(1);
             rs.close();pst.close();

             sql = "insert into orderdetail(ord_id,fre_id,orddet_count,orddet_price,orddet_disc,disc_id) values (?,?,?,?,?,?)";
             pst = conn.prepareStatement(sql);
             pst.setInt(1,id);
             pst.setInt(2,fre_id);
             pst.setInt(3,cnt);
             pst.setDouble(4,p/count);
             pst.setDouble(5,disc);
             pst.setInt(6,disc_id);
             pst.execute();
//             更新库存
             sql = "update fresh set fre_count = fre_count-? where fre_id=? ";

             pst = conn.prepareStatement(sql);
             pst.setInt(1,count);
             pst.setInt(2,fre_id);
             pst.execute();
             pst.close();
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
    public  int queryCnt(int id)throws BaseException{
         Connection conn = null;
         int re;
         try{
             conn = DBUtil.getConnection();
             String sql = "select fre_count from fresh where fre_id=?";
             java.sql.PreparedStatement pst = conn.prepareStatement(sql);
             pst.setInt(1,id);
             java.sql.ResultSet rs = pst.executeQuery();
             rs.next();
             re = rs.getInt(1);
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
    public static void main(String[] args){
         try {
             List<BeanFresh> l = (new FreshManager()).loadByType(1);
             System.out.println(l.get(0).getFre_name());
         } catch (BaseException e) {
             e.printStackTrace();
         }
    }
}
