package com.duan.Form;

import com.duan.DAO.MatHangDAO;
import com.duan.Messege.MessageInforJDialog;
import com.duan.Messege.MessageInforJDialog2;
import com.duan.Messege.MessageJDialog;
import com.duan.Messege.MessageJDialog2;
import com.duan.Model.MatHang;
import com.duan.UI.MHInterface;
import com.duan.UI.MainJFrame;
import static com.duan.UI.MainJFrame.form;
import com.duan.UI.MatHangJDialog;
import com.duan.UI.Test;
import com.duan.Untils.Auth;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MatHangJPanel extends javax.swing.JPanel implements MHInterface {

    private MatHangDAO dao = new MatHangDAO();
    List<MatHang> list = dao.selectAll();
    public static int row = -1;
    public static String tt = "";
    public static String msg = "";
    public static int inf = 0;
    DefaultTableModel model;
    Test test;
    DecimalFormat df = new DecimalFormat("###,###,###,###");
    public MatHangJPanel(Test test) {
        initComponents();
        this.test = test;
        model = (DefaultTableModel) tblMatHang.getModel();
        tblMatHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 24));
        tblMatHang.getTableHeader().setOpaque(false);
        tblMatHang.getTableHeader().setBackground(new Color(223, 228, 232));
        tblMatHang.getTableHeader().setForeground(new Color(59, 70, 97));
        tblMatHang.setRowHeight(50);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblMatHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblMatHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblMatHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblMatHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblMatHang.getColumn("Hình").setCellRenderer(new myTableCellRenderer());
        fillTable();
        lblHienThi.setText("Hiển thị từ 1 đến " + list.size());
    }

    @Override
    public void chon(int index) {
        switch (index) {
            case 1: {
                fillTable();
                break;
            }
            case 2: {
                form = 2;
                if (inf == 1) {
                    test.click(14);
                    fillTable();
                } else if (inf == 2) {
                    MessageJDialog2 obj = new MessageJDialog2(new javax.swing.JFrame(), true);
                    obj.showMessage(tt, msg);
                    if (obj.getMessageType() == MessageJDialog2.MessageType.OK) {
                        fillTable();
                    }
                } else {
                    fillTable();
                }
                inf = 0;
                List<MatHang> l = dao.selectAll();
                lblHienThi.setText("Hiển thị từ 1 đến " + l.size());
                break;

            }
            case 3: {
                if (row > -1) {
                    tblMatHang.setRowSelectionInterval(row, row);
                }
                break;
            }
        }
    }

    class myTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            return (Component) value;
        }
    }

    public void fillTable() {

        model.setRowCount(0);
        try {
            List<MatHang> list = dao.ShowTable();
            for (MatHang mh : list) {
                JLabel lb = new JLabel();
                ImageIcon icon = new ImageIcon(new ImageIcon("Images\\" + mh.getHinh()).getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
                lb.setIcon(icon);
                Object[] row = {mh.getTenMH(), "Bán tại nhà hàng", mh.getTenLoai(), df.format(mh.getGiaBan()) + " VND", lb};
                model.addRow(row);
            }
        } catch (Exception e) {
            String title = "TMTP";
            String message = "Lỗi truy vấn dữ liệu";
            MessageInforJDialog2 obj = new MessageInforJDialog2(new javax.swing.JFrame(), true);
            obj.showMessage(title, message);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMatHang = new javax.swing.JTable();
        lblHienThi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new com.duan.Design.ButtonBoder();

        tblMatHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên mặt hàng", "Kênh bán hàng", "Loại mặt hàng", "Giá thành", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMatHang.setFocusable(false);
        tblMatHang.setGridColor(new java.awt.Color(153, 153, 153));
        tblMatHang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblMatHang.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblMatHang.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblMatHang.setShowVerticalLines(false);
        tblMatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMatHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblMatHang);
        if (tblMatHang.getColumnModel().getColumnCount() > 0) {
            tblMatHang.getColumnModel().getColumn(0).setPreferredWidth(400);
            tblMatHang.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblMatHang.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblMatHang.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblMatHang.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        lblHienThi.setText("Hiển thị từ 1 đến ...");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setText("* Nhấp để xem chi tiết Mặt Hàng *");

        btnAdd.setBackground(new java.awt.Color(14, 209, 134));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/plus.png"))); // NOI18N
        btnAdd.setText("Thêm món mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHienThi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblHienThi)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblMatHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMousePressed
        if (SwingUtilities.isRightMouseButton(evt) == true || SwingUtilities.isLeftMouseButton(evt) == true) {
            row = tblMatHang.getSelectedRow();
            tblMatHang.clearSelection();
            MatHangJDialog.layMa = dao.getmaMH2((String) tblMatHang.getValueAt(row, 0));

            new MatHangJDialog(new javax.swing.JFrame(), false, test, this).setVisible(true);
        }
    }//GEN-LAST:event_tblMatHangMousePressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!Auth.isManager()) {
            MainJFrame.title = "BẠN CHỈ LÀ BẢO VỆ THÔI";
            MainJFrame.message = "Bạn không có quyền thêm món ăn!";
            inf = 1;
            chon(2);
        } else {

            MatHangJPanel.row = -1;
            new MatHangJDialog(new javax.swing.JFrame(), false, test, this).setVisible(true);
        }
    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.ButtonBoder btnAdd;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHienThi;
    private javax.swing.JTable tblMatHang;
    // End of variables declaration//GEN-END:variables

}
