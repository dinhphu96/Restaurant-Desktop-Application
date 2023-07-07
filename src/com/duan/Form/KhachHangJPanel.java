package com.duan.Form;

import com.duan.DAO.HoaDonDAO;
import com.duan.DAO.KhachHangDAO;
import com.duan.DAO.NhanVienDAO;
import com.duan.Model.HoaDon;
import com.duan.Model.KhachHang;
import com.duan.Model.NhanVien;
import com.duan.UI.MainJFrame;
import com.duan.UI.Test;
import com.duan.UI.ThongTinKHJDialog;
import com.duan.Untils.Auth;
import com.duan.Untils.MsgBox;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class KhachHangJPanel extends javax.swing.JPanel {

    Test test;
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    int row = -1;
    List<KhachHang> listKhachHang;
    public static KhachHang khachHang;
    DecimalFormat df = new DecimalFormat("###,###,###,###");

    public KhachHangJPanel(Test test) {
        initComponents();
        tblKhachHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tblKhachHang.getTableHeader().setOpaque(false);
        tblKhachHang.getTableHeader().setBackground(new Color(223, 228, 232));
        tblKhachHang.getTableHeader().setForeground(new Color(59, 70, 97));

        tblKhachHang.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblKhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblKhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblKhachHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tblHoaDonCuaKH.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tblHoaDonCuaKH.getTableHeader().setOpaque(false);
        tblHoaDonCuaKH.getTableHeader().setBackground(new Color(223, 228, 232));
        tblHoaDonCuaKH.getTableHeader().setForeground(new Color(59, 70, 97));

        tblHoaDonCuaKH.setRowHeight(30);

        tblHoaDonCuaKH.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblHoaDonCuaKH.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblHoaDonCuaKH.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblHoaDonCuaKH.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblHoaDonCuaKH.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        this.test = test;
        listKhachHang = khachHangDAO.selectAll();
        fillTable();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            for (KhachHang kh : listKhachHang) {
                List<HoaDon> listHoaDonCuaKhachHang = hoaDonDAO.hoaDonTheoMaKH(kh.getMaKH());

                Date hoaDonGanNhat;
                if (listHoaDonCuaKhachHang.size() <= 0) {
                    hoaDonGanNhat = null;
                } else {
                    hoaDonGanNhat = hoaDonGanNhat(listHoaDonCuaKhachHang);
                }

                Object[] row = {kh.getTenKH(), kh.getSdt(), listHoaDonCuaKhachHang.size(), hoaDonGanNhat, df.format(tongChiTieu(listHoaDonCuaKhachHang)) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }

    private Date hoaDonGanNhat(List<HoaDon> list) {
        Date hoaDonGanNhat = list.get(0).getThoiGian();
        for (HoaDon hoaDon : list) {
            if (hoaDonGanNhat.compareTo(hoaDon.getThoiGian()) < 0) {
                hoaDonGanNhat = hoaDon.getThoiGian();
            }
        }
        return hoaDonGanNhat;
    }

    private Float tongChiTieu(List<HoaDon> list) {
        Float tongChiTieu = 0.0f;
        for (HoaDon hoaDon : list) {
            tongChiTieu += hoaDon.getTongTien();
        }
        return tongChiTieu;
    }

    public void fillTableHoaDonCuaKH(Integer maKH) {
        List<HoaDon> list = hoaDonDAO.hoaDonTheoMaKH(maKH);
        DefaultTableModel model = (DefaultTableModel) tblHoaDonCuaKH.getModel();
        model.setRowCount(0);
        try {
            for (HoaDon hd : list) {
                NhanVien nv = nhanVienDAO.selectById(hd.getThuNgan());
                Object[] row = {hd.getMaHD(), hd.getThoiGian(), nv.getHoTenNV(), hd.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán", df.format(hd.getTongTien()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnThem = new com.duan.Design.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonCuaKH = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(754, 665));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Khách hàng", "Điện thoại", "Số hóa đơn", "Hóa đơn gần nhất", "Tổng chi tiêu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(153, 153, 153));
        tblKhachHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblKhachHang.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblKhachHang.setShowVerticalLines(false);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(14, 209, 134));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/plus.png"))); // NOI18N
        btnThem.setText("Thêm khách hàng mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblHoaDonCuaKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Thời gian", "Thu ngân", "Trạng thái", "Thanh toán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCuaKH.setFocusable(false);
        tblHoaDonCuaKH.setGridColor(new java.awt.Color(102, 102, 102));
        tblHoaDonCuaKH.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblHoaDonCuaKH.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblHoaDonCuaKH.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblHoaDonCuaKH.setShowVerticalLines(false);
        tblHoaDonCuaKH.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(tblHoaDonCuaKH);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("LỊCH SỬ TIÊU DÙNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, txtTimKiem});

    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        row = tblKhachHang.getSelectedRow();
        if (row == -1) {
            return;
        }
        khachHang = listKhachHang.get(row);
        fillTableHoaDonCuaKH(khachHang.getMaKH());
        ControllButtonJDialog controll = new ControllButtonJDialog(new javax.swing.JFrame(), false, test);
        controll.setLocation(630, (row * 30 + 142));
        controll.setVisible(true);
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!Auth.isManager()) {
            MainJFrame.title = "TMTP";
            MainJFrame.message = "Bạn không có quyền thêm khách hàng!";
            test.click(14);
        } else {
            KhachHang khachHang = new KhachHang();
            new ThongTinKHJDialog(new javax.swing.JFrame(), true, khachHang, test).setVisible(true);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        listKhachHang = khachHangDAO.timKiemKH("%" + txtTimKiem.getText() + "%");
        fillTable();
        if (listKhachHang.size() > 0) {
            row = 0;
            tblKhachHang.setRowSelectionInterval(row, row);
            khachHang = listKhachHang.get(row);
            fillTableHoaDonCuaKH(khachHang.getMaKH());
        } else {
            DefaultTableModel model = (DefaultTableModel) tblHoaDonCuaKH.getModel();
            model.setRowCount(0);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.Button btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHoaDonCuaKH;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
