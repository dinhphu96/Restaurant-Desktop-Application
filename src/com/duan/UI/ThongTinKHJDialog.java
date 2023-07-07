package com.duan.UI;

import com.duan.DAO.KhachHangDAO;
import com.duan.Messege.MessageJDialog;
import com.duan.Messege.MessageJDialog2;
import com.duan.Model.KhachHang;
import com.duan.Untils.Auth;
import com.duan.Untils.Validator;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import javax.swing.JDialog;

public class ThongTinKHJDialog extends javax.swing.JDialog {

    Test test;
    KhachHangDAO dao = new KhachHangDAO();
    KhachHang khachHang;
    Boolean checkKhachHangCu;
    Validator validator = new Validator();
    MainJFrame main;

    public ThongTinKHJDialog(java.awt.Frame parent, boolean modal, KhachHang kh, Test test) {
        super(parent, modal);
        initComponents();
        panelShadow1.setOpaque(false);
        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));
        this.initMoving(ThongTinKHJDialog.this);
        setLocationRelativeTo(null);

        this.test = test;
        khachHang = kh;

        // Kiểm tra khách hàng đã có hay thêm mới
        if (this.khachHang.getMaKH() == null) {
            checkKhachHangCu = false;
            btnXoaKhachHang.setEnabled(false); // Khách hàng mới: ẩn nút xóa
        } else {
            checkKhachHangCu = true;
            load(khachHang); // Khách hàng cũ: load dữ liệu lên form
        }

    }

    void load(KhachHang kh) {
        txtHoTen.setText(kh.getTenKH());
        txtSdt.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        jDateChooserNgayThamGia.setDate(kh.getNgayThamGia());
        if (kh.isGioiTinh()) {
            cboGioiTinh.setSelectedIndex(0);
        } else {
            cboGioiTinh.setSelectedIndex(1);
        }
        txtGhiChu.setText(kh.getGhiChu());
    }

    void delete() {

        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền xóa thông tin của khách hàng!";
            test.click(14);
        } else {
            try {
                dao.delete(khachHang.getMaKH());
                main.title = "TMTP";
                main.message = "Xóa thông tin khách hàng thành công!";
                dispose();
                test.click(3);
            } catch (Exception e) {
                // Do lỗi khóa ngoại chưa xử lý
                main.title = "TMTP";
                main.message = "Xóa khách hàng thất bại!";
                test.click(14);
            }
        }
    }

    void save() {
        // Lấy dữ liệu từ form
        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền sửa thông tin của khách hàng!";
            test.click(14);
        } else {
            if (!checkForm()) {
                return;
            }

            String hoTen = txtHoTen.getText();
            Boolean gioiTinh;
            if (cboGioiTinh.getSelectedIndex() == 0) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            String sdt = txtSdt.getText();
            String email = txtEmail.getText();
            Date ngatThamGia = jDateChooserNgayThamGia.getDate();
            String ghiChu = txtGhiChu.getText();

            // Validate xong thì set thông tin khách hàng
            khachHang.setTenKH(hoTen);
            khachHang.setGioiTinh(gioiTinh);
            khachHang.setSdt(sdt);
            khachHang.setEmail(email);
            khachHang.setNgayThamGia(ngatThamGia);
            khachHang.setGhiChu(ghiChu);

            if (checkKhachHangCu == true) {
                // Khách hàng cũ đã có mã không cần set, update luôn
                dao.update(khachHang);
                // Thông báo
                main.title = "TMTP";
                main.message = "Cập nhật thông tin khách hàng thành công!";
                dispose();
                test.click(3);
                // đóng form
            } else {
                // insert
                dao.insert(khachHang);
                // thông báo
                main.title = "TMTP";
                main.message = "thêm khách hàng thành công!";
                dispose();
                test.click(3);

            }
        }
    }

    private int x, y;

    public void initMoving(JDialog frame) {
        lblHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        lblHeader.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    public boolean checkForm() {
        if (validator.isEmpty(txtHoTen, "Chưa nhập họ tên")) {
            return false;
        }

        if (validator.isEmpty(txtSdt, "Chưa nhập SDT")) {
            return false;
        }
        if (validator.checkSDT(txtSdt, "SDT không đúng")) {
            return false;
        }
        if (validator.isEmail(txtEmail, "Email không đúng")) {

            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new com.duan.Shadow.PanelShadow();
        lblHeader = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jDateChooserNgayThamGia = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnXoaKhachHang = new com.duan.Design.ButtonBoder();
        btnHuy = new com.duan.Design.ButtonBoder();
        btnLuu = new com.duan.Design.ButtonBoder();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblHeader.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(51, 51, 51));
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Thông tin khách hàng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Số điện thoại");

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Email");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Giới tính");

        cboGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboGioiTinh.setForeground(new java.awt.Color(51, 51, 51));
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Ngày tham gia");

        jScrollPane1.setViewportView(txtGhiChu);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Ghi chú");

        btnXoaKhachHang.setBackground(new java.awt.Color(255, 51, 51));
        btnXoaKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKhachHang.setText("Xóa khách hàng");
        btnXoaKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXoaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhachHangActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(204, 204, 204));
        btnHuy.setText("Hủy");
        btnHuy.setFocusable(false);
        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(0, 102, 102));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(264, 371, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addComponent(btnXoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(txtSdt)
                                    .addComponent(jLabel6)
                                    .addComponent(jDateChooserNgayThamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail)
                                    .addGroup(panelShadow1Layout.createSequentialGroup()
                                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cboGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtHoTen)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addGap(20, 20, 20))))
            .addComponent(lblHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserNgayThamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        MessageJDialog2 obj = new MessageJDialog2(new javax.swing.JFrame(), true);
        obj.showMessage("TMTP", "Bạn có muốn lưu trước khi thoát?");
        if (obj.getMessageType() == MessageJDialog2.MessageType.OK) {
            save();
        }else{
            dispose();
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXoaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhachHangActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaKhachHangActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        save();
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.Design.ButtonBoder btnHuy;
    private com.duan.Design.ButtonBoder btnLuu;
    private com.duan.Design.ButtonBoder btnXoaKhachHang;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private com.toedter.calendar.JDateChooser jDateChooserNgayThamGia;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHeader;
    private com.duan.Shadow.PanelShadow panelShadow1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextPane txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables
}
