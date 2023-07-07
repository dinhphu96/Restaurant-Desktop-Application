package com.duan.Form;

import com.duan.DAO.NhanVienDAO;
import com.duan.Model.NhanVien;
import com.duan.UI.MainJFrame;
import com.duan.UI.NhanSuJDialog;
import com.duan.UI.Test;
import com.duan.Untils.Auth;
import com.duan.Untils.MsgBox;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NhanSuPanel extends javax.swing.JPanel {

    private NhanVienDAO dao = new NhanVienDAO();
    private int row = -1;
    public static int nhanSu;
    Test test;
    public static List<NhanVien> listNhanVien;

    public NhanSuPanel(Test test) {
        initComponents();

        tblNhanSu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tblNhanSu.getTableHeader().setOpaque(false);
        tblNhanSu.getTableHeader().setBackground(new Color(223, 228, 232));
        tblNhanSu.getTableHeader().setForeground(new Color(59, 70, 97));

        tblNhanSu.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblNhanSu.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblNhanSu.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblNhanSu.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblNhanSu.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        this.test = test;
        fillTable();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanSu.getModel();
        model.setRowCount(0);
        try {
            listNhanVien = dao.selectAll();
            for (NhanVien nv : listNhanVien) {
                Object[] row = {nv.getMaNV(), nv.getHoTenNV(), nv.getSdt(), nv.getVaiTro()};
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

        btnThemnhanVien = new com.duan.Design.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanSu = new javax.swing.JTable();

        btnThemnhanVien.setBackground(new java.awt.Color(14, 209, 134));
        btnThemnhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/plus.png"))); // NOI18N
        btnThemnhanVien.setText("Thêm nhân viên mới");
        btnThemnhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemnhanVienActionPerformed(evt);
            }
        });

        tblNhanSu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblNhanSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ và Tên", "Số điện thoại", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanSu.setFocusable(false);
        tblNhanSu.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblNhanSu.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblNhanSu.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblNhanSu.setShowVerticalLines(false);
        tblNhanSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhanSuMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanSu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemnhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(btnThemnhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemnhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemnhanVienActionPerformed
        if (!Auth.isManager()) {
            MainJFrame.title = "TMTP";
            MainJFrame.message = "Bạn không có quyền thêm nhân viên";
            test.click(14);

        } else {

            row = -1;
            tblNhanSu.clearSelection();
            new NhanSuJDialog(new javax.swing.JFrame(), true, row, test).setVisible(true);
        }
    }//GEN-LAST:event_btnThemnhanVienActionPerformed

    private void tblNhanSuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanSuMousePressed
        row = tblNhanSu.getSelectedRow();

        if (row == -1) {
            return;
        }
        nhanSu = row;
        ControllButtonJDialog controll = new ControllButtonJDialog(new javax.swing.JFrame(), false, test);
        controll.setLocation(735, nhanSu * 30 + 95);
        controll.setVisible(true);
    }//GEN-LAST:event_tblNhanSuMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.Button btnThemnhanVien;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNhanSu;
    // End of variables declaration//GEN-END:variables
}
