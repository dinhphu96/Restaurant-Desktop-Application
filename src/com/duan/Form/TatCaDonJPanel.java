package com.duan.Form;

import com.duan.DAO.BanDAO;
import com.duan.DAO.HoaDonChiTietDAO;
import com.duan.Design.ButtonBoder;
import com.duan.DAO.HoaDonDAO;
import com.duan.DAO.KhachHangDAO;
import com.duan.Model.HoaDon;
import com.duan.Shadow.PanelShadow;
import com.duan.UI.Test;
import com.duan.UI.ThanhToanJDialog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class TatCaDonJPanel extends javax.swing.JPanel {
//    String pattern = "HH:mm:ss";
//    SimpleDateFormat a = new SimpleDateFormat(pattern);
    Test test;
    int x = 20, y = 5;
    private int maTonTai = 0;
    private HoaDonDAO daoHD = new HoaDonDAO();
    private KhachHangDAO daoKH = new KhachHangDAO();
    private HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
    private BanDAO daoBAN = new BanDAO();
    List<HoaDon> list = daoHD.hoaDonChuaThanhToan();
    public static int maBanChuyen = 0;
    public static int maHDChuyen = 0;

    public TatCaDonJPanel(Test test) {
        initComponents();
        this.test = test;
        this.setOpaque(false);
        pnlmain.setPreferredSize(new Dimension(1090, 500));
        pnlmain.setBackground(Color.decode("#2C4462"));
        srollpane.setSize(1090, 680);
        srollpane.setBorder(null);
        this.setBackground(Color.red);
        showDon();    
//        System.out.println(a.format(list.get(0).getThoiGian()));
    }

    public void showDon() {
        pnlmain.removeAll();
        pnlmain.repaint();
        for (int i = 0; i < list.size(); i++) {
            
            pnlmain.add(don(1, 5 + i, list.get(i).getMaBan(), list.get(i).getTongTien(), list.get(i).getMaHD(),list.get(i).getThoiGian()+""));
            if ((i + 1) % 4 == 0 && i != 0) {
                x = 20;
                y += 160;
                pnlmain.setPreferredSize(new Dimension(1090, y + 160));
            } else {
                x += 264;
            }            
        }
        
        pnlmain.add(don(0, 0, 0, 0, 0,""));
        pnlmain.revalidate();
    }
    int s = 0, m = 0, h = 0, mm = 0;

    private JPanel don(int khuvuc, int SLKhach, int ban, float TongTien, int maHD, String tg) {
        PanelShadow DonHang = new PanelShadow();
        DonHang.setSize(254, 152);
        JPanel pnlTop = new JPanel();
        JLabel lblKhuVuc = new JLabel();
        JLabel lblSoLuongKhach = new JLabel();
        JLabel lblBan = new JLabel();
        JPanel pnlBot = new JPanel();
        JLabel lblTime = new JLabel();
        JLabel lblTongTien = new JLabel();
        ButtonBoder btnThanhToan = new ButtonBoder();
        ButtonBoder btnChuyenban = new ButtonBoder();

        lblKhuVuc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblKhuVuc.setText("Khu vực " + khuvuc);
        ImageIcon icon1 = new ImageIcon(new ImageIcon("Images\\table.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        lblKhuVuc.setIcon(icon1);
        lblKhuVuc.setHorizontalAlignment(JLabel.CENTER);
        lblSoLuongKhach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSoLuongKhach.setText("  " + SLKhach);
        ImageIcon icon3 = new ImageIcon(new ImageIcon("Images\\member.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        lblSoLuongKhach.setIcon(icon3);
        lblSoLuongKhach.setHorizontalAlignment(JLabel.CENTER);

        GroupLayout pnlTopLayout = new GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(pnlTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlTopLayout.createSequentialGroup()
                        .addComponent(lblKhuVuc, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblSoLuongKhach, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTopLayout.setVerticalGroup(
                pnlTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblKhuVuc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSoLuongKhach, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        lblBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblBan.setHorizontalAlignment(SwingConstants.CENTER);
        lblBan.setText("" + ban);

       
        lblTime.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        
        lblTime.setText(tg);
        ImageIcon icon2 = new ImageIcon(new ImageIcon("Images\\alarm.png").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
        lblTime.setIcon(icon2);
        lblTime.setHorizontalAlignment(JLabel.CENTER);

        DecimalFormat df = new DecimalFormat("###,###,###,###");
        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lblTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongTien.setText(df.format(TongTien) + "đ");
        ImageIcon icon4 = new ImageIcon(new ImageIcon("Images\\money1.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        lblTongTien.setIcon(icon4);
        lblTongTien.setHorizontalAlignment(JLabel.CENTER);
        GroupLayout pnlBotLayout = new GroupLayout(pnlBot);
        pnlBot.setLayout(pnlBotLayout);
        pnlBotLayout.setHorizontalGroup(
                pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBotLayout.createSequentialGroup()
                                .addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblTongTien, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBotLayout.setVerticalGroup(
                pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTongTien, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        );

        btnThanhToan.setBackground(new java.awt.Color(51, 204, 255));
        btnThanhToan.setText("Thanh toán");

        btnChuyenban.setBackground(new java.awt.Color(255, 102, 102));
        btnChuyenban.setText("Chuyển bàn");

        GroupLayout DonHangLayout = new GroupLayout(DonHang);
        DonHang.setLayout(DonHangLayout);
        DonHangLayout.setHorizontalGroup(DonHangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(DonHangLayout.createSequentialGroup().addContainerGap()
                        .addGroup(DonHangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pnlTop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblBan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlBot, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.TRAILING, DonHangLayout.createSequentialGroup()
                                        .addGap(0, 1, Short.MAX_VALUE)
                                        .addComponent(btnChuyenban, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThanhToan, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
        );
        DonHangLayout.setVerticalGroup(
                DonHangLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(DonHangLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGap(12, 12, 12)
                                .addComponent(pnlTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblBan, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(pnlBot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(DonHangLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThanhToan, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnChuyenban, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(DonHang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(DonHang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnChuyenban.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                TatCaDonJPanel.maBanChuyen = ban;
                TatCaDonJPanel.maHDChuyen = maHD;
                test.click(10);
                
            }
        });

        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThanhToanJDialog.tongTien = df.format((int)TongTien);                
                new ThanhToanJDialog(new javax.swing.JFrame(), true, maHD, test, ban).setVisible(true);
                test.click(11);
            }
        });

        DonHang.setLocation(x, y);
        return DonHang;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        srollpane = new javax.swing.JScrollPane();
        pnlmain = new javax.swing.JPanel();

        javax.swing.GroupLayout pnlmainLayout = new javax.swing.GroupLayout(pnlmain);
        pnlmain.setLayout(pnlmainLayout);
        pnlmainLayout.setHorizontalGroup(
            pnlmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        pnlmainLayout.setVerticalGroup(
            pnlmainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );

        srollpane.setViewportView(pnlmain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlmain;
    private javax.swing.JScrollPane srollpane;
    // End of variables declaration//GEN-END:variables
}
