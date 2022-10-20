package com.example.culture.repository;

import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.common.ConnectionUtil.CONN_UTIL;

public class JdbcCultureRepository implements CultureRepository<CultureVO> {

    @Override
    public void insert(CultureVO cultureVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into culture_basic " +
                    "(svc_nm,area_nm,place_nm,tel_no,cno) " +
                    "values(?,?,?,?,?)";
            conn = CONN_UTIL.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cultureVO.getSvc_nm());
            pstmt.setString(2, cultureVO.getArea_nm());
            pstmt.setString(3, cultureVO.getPlace_nm());
            pstmt.setString(4, cultureVO.getTel_no());
            pstmt.setLong(5, cultureVO.getCno());
            pstmt.executeUpdate();

            sql = "insert into culture_info " +
                    "(pay_ay_nm,use_tgt_info,svc_url,img_url,dtlcont,cno) " +
                    "values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cultureVO.getPay_ay_nm());
            pstmt.setString(2, cultureVO.getUse_tgt_info());
            pstmt.setString(3, cultureVO.getSvc_url());
            pstmt.setString(4, cultureVO.getImg_url());
            pstmt.setString(5, cultureVO.getDtlcont());
            pstmt.setLong(6, cultureVO.getCno());
            pstmt.executeUpdate();

            sql = "insert into culture_res " +
                    "(capacity,price,cno,revstd_day_nm,revstd_day) " +
                    "values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cultureVO.getCapacity());
            pstmt.setInt(2, cultureVO.getPrice());
            pstmt.setLong(3, cultureVO.getCno());
            pstmt.setString(4, cultureVO.getRevstd_day_nm());
            pstmt.setString(5, cultureVO.getRevstd_day());
            pstmt.executeUpdate();

            sql = "insert into culture_schedule " +
                    "(svc_opn_bgn_dt,svc_opn_end_dt,v_min,v_max,rcpt_bgn_dt,rcpt_end_dt,cno) " +
                    "values(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cultureVO.getSvc_opn_bgn_dt());
            pstmt.setString(2, cultureVO.getSvc_opn_end_dt());
            pstmt.setString(3, cultureVO.getV_min());
            pstmt.setString(4, cultureVO.getV_max());
            pstmt.setString(5, cultureVO.getRcpt_bgn_dt());
            pstmt.setString(6, cultureVO.getRcpt_end_dt());
            pstmt.setLong(7, cultureVO.getCno());
            pstmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("culture insert를 롤백합니다");
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            System.out.println("cultrue 등록에 실패했습니다");
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(pstmt,conn);
        }
    }

    //작성중
    @Override
    public PageResponse<CultureVO> selectAll(PageRequest pageRequest) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PageResponse<CultureVO> pageResponse = null;
        try {
            String sql = "select basic.cno,basic.svc_nm,basic.area_nm,basic.place_nm,basic.tel_no," +
                    "info.pay_ay_nm,info.use_tgt_info,info.svc_url,info.img_url,info.dtlcont," +
                    "res.capacity,res.price,res.revstd_day,res.revstd_day_nm," +
                    "sch.svc_opn_bgn_dt,sch.svc_opn_end_dt,sch.v_min,sch.v_max,sch.rcpt_bgn_dt,sch.rcpt_end_dt\n" +
                    "from " +
                    "(culture_basic as basic inner join culture_info as info on basic.cno=info.cno " +
                    "inner join culture_res as res on info.cno=res.cno " +
                    "inner join culture_schedule as sch on res.cno=sch.cno) " +
                    "order by basic.cno desc " +
                    "limit ? , ?;";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,pageRequest.getSkip());
            pstmt.setInt(2,pageRequest.getSize());
            rs = pstmt.executeQuery();
            List<CultureVO> cultureVOS = new ArrayList<>();
            while(rs.next()){
                CultureVO cultureVO = CultureVO.builder()
                        .cno(rs.getLong("cno"))
                        .svc_nm(rs.getString("svc_nm"))
                        .area_nm(rs.getString("area_nm"))
                        .place_nm(rs.getString("place_nm"))
                        .tel_no(rs.getString("tel_no"))
                        .pay_ay_nm(rs.getString("pay_ay_nm"))
                        .use_tgt_info(rs.getString("use_tgt_info"))
                        .svc_url(rs.getString("svc_url"))
                        .img_url(rs.getString("img_url"))
                        .dtlcont(rs.getString("dtlcont"))
                        .svc_opn_bgn_dt(rs.getString("svc_opn_bgn_dt"))
                        .svc_opn_end_dt(rs.getString("svc_opn_end_dt"))
                        .v_min(rs.getString("v_min"))
                        .v_max(rs.getString("v_max"))
                        .rcpt_bgn_dt(rs.getString("rcpt_bgn_dt"))
                        .rcpt_end_dt(rs.getString("rcpt_end_dt"))
                        .capacity(rs.getInt("capacity"))
                        .price(rs.getInt("price"))
                        .revstd_day_nm(rs.getString("revstd_day_nm"))
                        .revstd_day(rs.getString("revstd_day"))
                        .build();
                cultureVOS.add(cultureVO);
            }
             pageResponse = PageResponse.<CultureVO>withAll()
                .pageRequestDTO(pageRequest)
                .total(selectCount())
                .pageList(cultureVOS)
                .build();
            return pageResponse;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cultrue 조회에 실패했습니다");
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(rs,pstmt,conn);
        }
    }

    @Override
    public int selectCount() {
        String sql = "select count(*) from culture_basic";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cultrue 조회(count)에 실패했습니다");
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(rs,pstmt,conn);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "delete basic,info,res,sch\n" +
                "from (culture_basic as basic inner join culture_info as info on basic.cno=info.cno\n" +
                "                    inner join culture_res as res on info.cno=res.cno\n" +
                "                    inner join culture_schedule as sch on res.cno=sch.cno)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = CONN_UTIL.getConnection();

            String sql_setFk = "set foreign_key_checks=0";
            pstmt = conn.prepareStatement(sql_setFk);
            pstmt.execute();

            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            sql_setFk = "set foreign_key_checks=1";
            pstmt = conn.prepareStatement(sql_setFk);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("culture 삭제에 실패했습니다");
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(pstmt,conn);
        }
    }

    @Override
    public Optional<CultureVO> selectOne(Long cno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select basic.cno,basic.svc_nm,basic.area_nm,basic.place_nm,basic.tel_no," +
                    "info.pay_ay_nm,info.use_tgt_info,info.svc_url,info.img_url,info.dtlcont," +
                    "res.capacity,res.price,res.revstd_day,res.revstd_day_nm," +
                    "sch.svc_opn_bgn_dt,sch.svc_opn_end_dt,sch.v_min,sch.v_max,sch.rcpt_bgn_dt,sch.rcpt_end_dt\n" +
                    "from " +
                    "(culture_basic as basic inner join culture_info as info on basic.cno=info.cno " +
                    "inner join culture_res as res on info.cno=res.cno " +
                    "inner join culture_schedule as sch on res.cno=sch.cno) " +
                    "where basic.cno = ?";
            conn = CONN_UTIL.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,cno);
            rs = pstmt.executeQuery();
            rs.next();
            CultureVO cultureVO = CultureVO.builder()
                    .cno(rs.getLong("cno"))
                    .svc_nm(rs.getString("svc_nm"))
                    .area_nm(rs.getString("area_nm"))
                    .place_nm(rs.getString("place_nm"))
                    .tel_no(rs.getString("tel_no"))
                    .pay_ay_nm(rs.getString("pay_ay_nm"))
                    .use_tgt_info(rs.getString("use_tgt_info"))
                    .svc_url(rs.getString("svc_url"))
                    .img_url(rs.getString("img_url"))
                    .dtlcont(rs.getString("dtlcont"))
                    .svc_opn_bgn_dt(rs.getString("svc_opn_bgn_dt"))
                    .svc_opn_end_dt(rs.getString("svc_opn_end_dt"))
                    .v_min(rs.getString("v_min"))
                    .v_max(rs.getString("v_max"))
                    .rcpt_bgn_dt(rs.getString("rcpt_bgn_dt"))
                    .rcpt_end_dt(rs.getString("rcpt_end_dt"))
                    .capacity(rs.getInt("capacity"))
                    .price(rs.getInt("price"))
                    .revstd_day_nm(rs.getString("revstd_day_nm"))
                    .revstd_day(rs.getString("revstd_day"))
                    .build();
            return Optional.ofNullable(cultureVO);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cultrue 조회에 실패했습니다");
            throw new RuntimeException(e);
        } finally {
            CONN_UTIL.close(rs,pstmt,conn);
        }
    }
}
