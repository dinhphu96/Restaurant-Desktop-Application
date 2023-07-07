/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.Form;

import com.duan.DAO.HoaDonChiTietDAO;
import com.duan.DAO.HoaDonDAO;
import com.duan.DAO.KhachHangDAO;
import com.duan.DAO.MatHangDAO;
import com.duan.DAO.NhanVienDAO;
import com.duan.Messege.MessageInforJDialog2;
import com.duan.Model.HoaDon;
import com.duan.Model.HoaDonChiTiet;
import com.duan.Model.KhachHang;
import com.duan.Model.MatHang;
import com.duan.Model.NhanVien;
import com.duan.Untils.MsgBox;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class HoaDonJPanel extends javax.swing.JPanel {

    private HoaDonDAO dao = new HoaDonDAO();
    private int row = -1;
    private HoaDonChiTietDAO daoCT = new HoaDonChiTietDAO();
    private MatHangDAO daoMH = new MatHangDAO();
    private KhachHangDAO daoKH = new KhachHangDAO();
    private NhanVienDAO daoNV = new NhanVienDAO();
    List<HoaDon> list;
    private int maHD;
    private DecimalFormat df = new DecimalFormat("###,###,###,###");

    /**
     * Creates new form HoaDonJPanel
     */
    public HoaDonJPanel() {
        initComponents();

        tblHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tblHoaDon.getTableHeader().setOpaque(false);
        tblHoaDon.getTableHeader().setBackground(new Color(223, 228, 232));
        tblHoaDon.getTableHeader().setForeground(new Color(59, 70, 97));

        tblHoaDon.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblHoaDon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblHoaDon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblHoaDon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        list = dao.selectAll();
        fillTableHoaDon(list);

        tblChiTiet.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tblChiTiet.getTableHeader().setOpaque(false);
        tblChiTiet.getTableHeader().setBackground(new Color(223, 228, 232));
        tblChiTiet.getTableHeader().setForeground(new Color(59, 70, 97));

        tblChiTiet.setRowHeight(30);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblChiTiet.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblChiTiet.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblChiTiet.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

    }

    public void fillTableHoaDon(List<HoaDon> list) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        DefaultTableModel modelChiTiet = (DefaultTableModel) tblChiTiet.getModel();
        modelChiTiet.setRowCount(0);
        if (list == null) {
            MsgBox.alert(null, "Không có dữ liệu phù hợp!");
            return;
        }
        try {
            for (HoaDon hd : list) {
                NhanVien nv = daoNV.selectById(hd.getThuNgan());
                Object[] row = {hd.getMaHD(), hd.getThoiGian(), nv.getHoTenNV(), hd.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán", df.format(hd.getTongTien()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }

    private List<HoaDon> hoaDonDaThanhToan() {
        List<HoaDon> listHoaDonDaThanhToan = new ArrayList<HoaDon>();
        for (HoaDon hd : list) {
            if (hd.isTrangThai() == true) {
                listHoaDonDaThanhToan.add(hd);
            }
        }
        return listHoaDonDaThanhToan;
    }

    private List<HoaDon> hoaDonChuaThanhToan() {
        List<HoaDon> listHoaDonChuaThanhToan = new ArrayList<HoaDon>();
        for (HoaDon hd : list) {
            if (hd.isTrangThai() == false) {
                listHoaDonChuaThanhToan.add(hd);
            }
        }
        return listHoaDonChuaThanhToan;
    }

    public void fillTableChitiet() {
        DefaultTableModel model = (DefaultTableModel) tblChiTiet.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonChiTiet> list = daoCT.selectByMaHD(maHD);

            if (list != null) {
                for (HoaDonChiTiet ct : list) {
                    MatHang mh = daoMH.selectById(String.valueOf(ct.getMaMH()));
                    Object[] row = {mh.getTenMH(), ct.getSoLuong(), df.format(mh.getGiaBan() * ct.getSoLuong()) + " VND"};
                    model.addRow(row);
                }
            }
//            MatHang mh = daoMH.selectById(String.valueOf(1));
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }

    void showChiTietDonHang() {
        // Tên khách hàng
//        List<HoaDon> listHD = dao.selectAll();
        HoaDon hd = dao.selectById(maHD);

        KhachHang kh = daoKH.selectById(hd.getMaKH());

        if (kh == null) {
            lblTenKhachHang.setText("Khách lẻ");
        } else {
            lblTenKhachHang.setText("Tên khách hàng: " + kh.getTenKH());
        }

        // Fill table chi tiết
        fillTableChitiet();
    }

    Integer rowCanTim(String txt) {
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txt.equals(String.valueOf(tblHoaDon.getValueAt(i, 0)))) {
                return row = i;

            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new com.duan.Design.Button();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTiet = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1116, 469));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý hóa đơn");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.setFocusable(false);
        tblHoaDon.setGridColor(new java.awt.Color(102, 102, 102));
        tblHoaDon.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblHoaDon.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblHoaDon.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblHoaDon.setShowVerticalLines(false);
        tblHoaDon.setVerifyInputWhenFocusTarget(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        txtTimKiem.setToolTipText("");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đã thanh toán", "Chưa thanh toán" }));
        cboTrangThai.setFocusable(false);
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(cboTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnTimKiem, cboTrangThai, txtTimKiem});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jLabel1)
                .addContainerGap(228, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Chi tiết đơn hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTenKhachHang.setText("Tên khách hàng");

        tblChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên món ăn", "Số lượng", "Giá tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTiet.setFocusable(false);
        tblChiTiet.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblChiTiet.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblChiTiet.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblChiTiet.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblChiTiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTenKhachHang)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        if (SwingUtilities.isRightMouseButton(evt) == true || SwingUtilities.isLeftMouseButton(evt) == true) {
            row = tblHoaDon.rowAtPoint(evt.getPoint());
            tblHoaDon.clearSelection();
            tblHoaDon.addRowSelectionInterval(row, row);
            row = tblHoaDon.getSelectedRow();
            maHD = (int) tblHoaDon.getValueAt(row, 0);
            showChiTietDonHang();
        }
    }//GEN-LAST:event_tblHoaDonMousePressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        tblHoaDon.clearSelection();
        if (rowCanTim(txtTimKiem.getText()) != -1) {
            tblHoaDon.setRowSelectionInterval(row, row);
            maHD = (int) tblHoaDon.getValueAt(row, 0);
            showChiTietDonHang();
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        tblHoaDon.clearSelection();
        if (rowCanTim(txtTimKiem.getText()) != -1) {
            tblHoaDon.setRowSelectionInterval(row, row);
            maHD = (int) tblHoaDon.getValueAt(row, 0);
            showChiTietDonHang();
        } else {
            String title = "TMTP";
            String message = "Không tìm thấy";
            MessageInforJDialog2 obj = new MessageInforJDialog2(new javax.swing.JFrame(), true);
            obj.showMessage(title, message);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        lblTenKhachHang.setText("");
        if (cboTrangThai.getSelectedIndex() == 0) {
            fillTableHoaDon(list);
        } else if (cboTrangThai.getSelectedIndex() == 1) {
            fillTableHoaDon(hoaDonDaThanhToan());
        } else {
            fillTableHoaDon(hoaDonChuaThanhToan());            
        }
    }//GEN-LAST:event_cboTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.Button btnTimKiem;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JTable tblChiTiet;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
