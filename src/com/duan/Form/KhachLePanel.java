package com.duan.Form;

import com.duan.DAO.KhachHangDAO;
import com.duan.Design.RoundedPanel;
import com.duan.Messege.MessageInforJDialog2;
import com.duan.Model.KhachHang;
import static com.duan.UI.MainJFrame.message;
import static com.duan.UI.MainJFrame.title;
import com.duan.Untils.Auth;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.duan.Untils.MsgBox;
import java.awt.BorderLayout;

public class KhachLePanel extends javax.swing.JPanel {

    KhachHangDAO dao = new KhachHangDAO();
    private int row = -1;

    public List<KhachHang> listKhach = new ArrayList<>();

    public KhachLePanel() {
        initComponents();

        this.setSize(1043, 662);
        this.setBackground(Color.decode("#141C37"));
        btnThem.setBackground(Color.decode("#0088ff"));
        pnlKhachLe.setBackground(Color.decode("#cce7ff"));
        pnlTim.setBackground(Color.decode("#ffffff"));
        pnlCenter.setBackground(Color.decode("#ffffff"));
        pnlCumDuoi.setBackground(new Color(0, 0, 0, 0));
        pnlTong.setBackground(new Color(0, 0, 0, 0));

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

        fillTable();

    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        String tenKH = txtTimKhach.getText();
        listKhach = dao.selectByTenKH(tenKH);

        try {
            for (KhachHang kh : listKhach) {

                Object[] row = {kh.getTenKH(), kh.isGioiTinh() == true ? "Nam" : "Nữ", kh.getSdt()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }

    public void buttonThoat() {

        MenuGoiMonForm.p.pnlCenter.remove(this);
        MenuGoiMonForm.p.pnlCenter.repaint();

        MenuGoiMonForm.p.fillTable();
        MenuGoiMonForm.p.pnlCenter.add(MenuGoiMonForm.pnlHeader, BorderLayout.NORTH);
        MenuGoiMonForm.p.pnlCenter.add(MenuGoiMonForm.pnlMenu, BorderLayout.WEST);
        MenuGoiMonForm.p.pnlCenter.add(MenuGoiMonForm.pnlMenuMon, BorderLayout.CENTER);
        MenuGoiMonForm.p.pnlCenter.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTong = new javax.swing.JPanel();
        pnlCenter = new RoundedPanel(10,Color.white);
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        pnlTim = new RoundedPanel(10,Color.white);
        txtTimKhach = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlKhachLe = new RoundedPanel(10,Color.white);
        lblKhachLe = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlCumDuoi = new javax.swing.JPanel();
        btnThem = new com.duan.Design.ButtonBoder();
        btnThoat = new com.duan.Design.ButtonBoder();

        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(391, 27));
        setPreferredSize(new java.awt.Dimension(1037, 702));

        pnlTong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCenter.setOpaque(false);

        tblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên khách hàng", "Giới tính", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKhachHang.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblKhachHang.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblKhachHang.setShowVerticalLines(false);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlTong.add(pnlCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 149, 1027, 390));

        pnlTim.setOpaque(false);

        txtTimKhach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKhach.setBorder(null);
        txtTimKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKhachKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tìm Khách hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlTimLayout = new javax.swing.GroupLayout(pnlTim);
        pnlTim.setLayout(pnlTimLayout);
        pnlTimLayout.setHorizontalGroup(
            pnlTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTimLayout.setVerticalGroup(
            pnlTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTimKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlTong.add(pnlTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, 1027, 50));

        pnlKhachLe.setOpaque(false);
        pnlKhachLe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlKhachLeMouseClicked(evt);
            }
        });

        lblKhachLe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblKhachLe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/khachle.png"))); // NOI18N
        lblKhachLe.setText("   Khách lẻ");
        lblKhachLe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachLeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlKhachLeLayout = new javax.swing.GroupLayout(pnlKhachLe);
        pnlKhachLe.setLayout(pnlKhachLeLayout);
        pnlKhachLeLayout.setHorizontalGroup(
            pnlKhachLeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachLeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblKhachLe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlKhachLeLayout.setVerticalGroup(
            pnlKhachLeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachLeLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(lblKhachLe)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlTong.add(pnlKhachLe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 1027, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Khách hàng");
        pnlTong.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm khách hàng mới (F8)");
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 255, 255));
        btnThoat.setForeground(new java.awt.Color(204, 0, 0));
        btnThoat.setText("Thoát (Esc)");
        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCumDuoiLayout = new javax.swing.GroupLayout(pnlCumDuoi);
        pnlCumDuoi.setLayout(pnlCumDuoiLayout);
        pnlCumDuoiLayout.setHorizontalGroup(
            pnlCumDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCumDuoiLayout.createSequentialGroup()
                .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlCumDuoiLayout.setVerticalGroup(
            pnlCumDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCumDuoiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCumDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlCumDuoiLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThem, btnThoat});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCumDuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(pnlCumDuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        row = tblKhachHang.getSelectedRow();
        MenuGoiMonForm.btnKhach.setText(listKhach.get(row).getTenKH());
        buttonThoat();
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        buttonThoat();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        if (!Auth.isManager()) {
            title = "TMTP";
            message = "Bạn không có quyền thêm khách hàng";
            MessageInforJDialog2 obj = new MessageInforJDialog2(new javax.swing.JFrame(), true);
            obj.showMessage(title, message);
        } else {
            MenuGoiMonForm.p.pnlCenter.remove(this);
            MenuGoiMonForm.p.pnlCenter.repaint();
            MenuGoiMonForm.p.addForm(new ThemKhachJPanel());
        }
    }//GEN-LAST:event_btnThemMousePressed

    private void lblKhachLeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachLeMouseClicked
        MenuGoiMonForm.btnKhach.setText("Khách lẻ");
        row = -1;
        buttonThoat();
    }//GEN-LAST:event_lblKhachLeMouseClicked

    private void pnlKhachLeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKhachLeMouseClicked
        MenuGoiMonForm.btnKhach.setText("Khách lẻ");
        row = -1;
        buttonThoat();
    }//GEN-LAST:event_pnlKhachLeMouseClicked

    private void txtTimKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKhachKeyReleased
        fillTable();
    }//GEN-LAST:event_txtTimKhachKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.ButtonBoder btnThem;
    private com.duan.Design.ButtonBoder btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblKhachLe;
    public static javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlCumDuoi;
    private javax.swing.JPanel pnlKhachLe;
    private javax.swing.JPanel pnlTim;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtTimKhach;
    // End of variables declaration//GEN-END:variables

}
