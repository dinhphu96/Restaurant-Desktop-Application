package com.duan.UI;

import com.duan.Form.MenuGoiMonForm;
import com.duan.Form.KhachLePanel;
import com.duan.DAO.BanDAO;
import java.util.List;
import com.duan.Form.MenuJPanel;
import com.duan.Form.ControllJPanel;
import com.duan.Form.TatCaDonJPanel;
import com.duan.Form.HoaDonJPanel;
import com.duan.Form.KhachHangJPanel;
import com.duan.Form.MatHangJPanel;
import com.duan.Form.NhanSuPanel;
import com.duan.Form.SoDoBanJPanel;
import com.duan.Form.SoDoBanCenterJPanel;
import com.duan.Form.TongQuanJPanel;
import com.duan.Messege.MessageInforJDialog;
import com.duan.Messege.MessageJDialog;
import static com.duan.Form.MenuGoiMonForm.pnlHeader;
import static com.duan.Form.MenuGoiMonForm.pnlMenu;
import static com.duan.Form.MenuGoiMonForm.pnlMenuMon;
import com.duan.Untils.Auth;
import com.duan.Untils.XImage;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MainJFrame extends javax.swing.JFrame implements Test {

    public static int form;
    public static int soLuongBan;
    public static String title = "";
    public static String message = "";
    public static int infor = 0;

    public MainJFrame() {
        initComponents();
        init();
        time();
        Menu.setBackgroundEntered(9);
    }

    void init() {
        this.getContentPane().setBackground(Color.decode("#2C4462"));
        this.setExtendedState(MainJFrame.MAXIMIZED_BOTH);
        lbl1.setOpaque(true);
        CenterPanel.removeAll();
        CenterPanel.add(new MenuJPanel(this), BorderLayout.WEST);
        CenterPanel.revalidate();
        btnMenu.setBackground(Color.decode("#2C4462"));
        this.setIconImage(XImage.getAppIcon());
        btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSoDoBan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTatCaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnZoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CenterPanel.add(new TongQuanJPanel(this), BorderLayout.CENTER);
        chon(new TongQuanJPanel(this), 9);
        lblAuth.setText(Auth.user.getHoTenNV());
    }

    public void time() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Date now = new Date();
                        SimpleDateFormat formater = new SimpleDateFormat();
                        formater.applyPattern("hh:mm aa");

                        String time = formater.format(now);
                        formater.applyPattern("dd/MM/yyyy");
                        String date = formater.format(now);
                        lblDongHo.setText(time);
                        lblLich.setText(date);
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnMenu = new com.duan.Design.Button();
        lbl1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnTatCaDon = new com.duan.Design.Button();
        btnSoDoBan = new com.duan.Design.Button();
        jPanel5 = new javax.swing.JPanel();
        btnZoom = new com.duan.Design.Button();
        btnExit = new com.duan.Design.Button();
        CenterPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblAuth = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        lblLich = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(44, 68, 98));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(20, 28, 55));

        btnMenu.setBackground(new java.awt.Color(20, 28, 55));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/List.png"))); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        lbl1.setBackground(new java.awt.Color(20, 28, 55));
        lbl1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/logo7.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(20, 28, 55));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTatCaDon.setBackground(new java.awt.Color(20, 28, 55));
        btnTatCaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTatCaDon.setText("Tất cả đơn");
        btnTatCaDon.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnTatCaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaDonActionPerformed(evt);
            }
        });
        jPanel2.add(btnTatCaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 55));

        btnSoDoBan.setBackground(new java.awt.Color(20, 28, 55));
        btnSoDoBan.setForeground(new java.awt.Color(255, 255, 255));
        btnSoDoBan.setText("Sơ đồ bàn");
        btnSoDoBan.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnSoDoBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoDoBanActionPerformed(evt);
            }
        });
        jPanel2.add(btnSoDoBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 55));

        jPanel5.setBackground(new java.awt.Color(20, 28, 55));

        btnZoom.setBackground(new java.awt.Color(20, 28, 55));
        btnZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/minimize.png"))); // NOI18N
        btnZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(20, 28, 55));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/Exit.png"))); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnZoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        CenterPanel.setBackground(new java.awt.Color(44, 68, 98));
        CenterPanel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CenterPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(20, 28, 55));
        jPanel3.setLayout(new java.awt.BorderLayout());

        lblAuth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAuth.setForeground(new java.awt.Color(255, 255, 255));
        lblAuth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/User.png"))); // NOI18N
        lblAuth.setText("Phương Đình Phú");
        jPanel3.add(lblAuth, java.awt.BorderLayout.LINE_START);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("© 2021 TMTP - Restaurant.com. Toàn quyền sở hữu");
        jPanel3.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(20, 28, 55));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(20, 28, 55));

        lblDongHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDongHo.setText("dongho");

        lblLich.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLich.setForeground(new java.awt.Color(255, 255, 255));
        lblLich.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLich.setText("lich");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLich, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblDongHo)
                .addGap(3, 3, 3)
                .addComponent(lblLich)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(CenterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CenterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTatCaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaDonActionPerformed
        if (MenuGoiMonForm.btnLuu.isEnabled() == false || MenuGoiMonForm.soBan == 0) {

            btnMenu.setBackground(Color.decode("#141C37"));
            btnTatCaDon.setBackground(Color.decode("#2C4462"));
            btnSoDoBan.setBackground(Color.decode("#141C37"));

            CenterPanel.removeAll();
            CenterPanel.repaint();
            CenterPanel.add(new ControllJPanel(this), BorderLayout.EAST);
            CenterPanel.add(new TatCaDonJPanel(this), BorderLayout.CENTER);
            CenterPanel.revalidate();
            TatCaDonJPanel.maBanChuyen = 0;
            TatCaDonJPanel.maHDChuyen = 0;
            return;
        }

        title = "TMTP";
        message = "Bạn có muốn thoát trước khi lưu không ?";

        MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
        obj.showMessage(title, message);
        if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
            btnMenu.setBackground(Color.decode("#141C37"));
            btnTatCaDon.setBackground(Color.decode("#2C4462"));
            btnSoDoBan.setBackground(Color.decode("#141C37"));

            CenterPanel.removeAll();
            CenterPanel.repaint();
            CenterPanel.add(new ControllJPanel(this), BorderLayout.EAST);
            CenterPanel.add(new TatCaDonJPanel(this), BorderLayout.CENTER);
            CenterPanel.revalidate();
            MenuGoiMonForm.btnLuu.setEnabled(false);
        }
    }//GEN-LAST:event_btnTatCaDonActionPerformed

    private void btnSoDoBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoDoBanActionPerformed
        if (MenuGoiMonForm.btnLuu.isEnabled() == false || MenuGoiMonForm.soBan == 0) {
            btnMenu.setBackground(Color.decode("#141C37"));
            btnTatCaDon.setBackground(Color.decode("#141C37"));
            btnSoDoBan.setBackground(Color.decode("#2C4462"));

            CenterPanel.removeAll();
            CenterPanel.repaint();

            BanDAO dao = new BanDAO();
            List list = dao.selectAll();
            soLuongBan = list.size();

            CenterPanel.add(new SoDoBanJPanel(this, soLuongBan), BorderLayout.EAST);
            CenterPanel.add(new SoDoBanCenterJPanel(soLuongBan, this), BorderLayout.CENTER);
            CenterPanel.revalidate();
            TatCaDonJPanel.maBanChuyen = 0;
            TatCaDonJPanel.maHDChuyen = 0;
            return;
        }

        title = "TMTP";
        message = "Bạn có muốn thoát trước khi lưu không ?";

        MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
        obj.showMessage(title, message);
        if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
            btnMenu.setBackground(Color.decode("#141C37"));
            btnTatCaDon.setBackground(Color.decode("#141C37"));
            btnSoDoBan.setBackground(Color.decode("#2C4462"));

            CenterPanel.removeAll();
            CenterPanel.repaint();

            BanDAO dao = new BanDAO();
            List list = dao.selectAll();
            soLuongBan = list.size();

            CenterPanel.add(new SoDoBanJPanel(this, soLuongBan), BorderLayout.EAST);
            CenterPanel.add(new SoDoBanCenterJPanel(soLuongBan, this), BorderLayout.CENTER);
            CenterPanel.revalidate();
        }
    }//GEN-LAST:event_btnSoDoBanActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        if (MenuGoiMonForm.btnLuu.isEnabled() == false || MenuGoiMonForm.soBan == 0) {

            btnMenu.setBackground(Color.decode("#2C4462"));
            btnTatCaDon.setBackground(Color.decode("#141C37"));
            btnSoDoBan.setBackground(Color.decode("#141C37"));

            CenterPanel.removeAll();
            CenterPanel.repaint();
            CenterPanel.add(new MenuJPanel(this), BorderLayout.WEST);
            CenterPanel.revalidate();
            TatCaDonJPanel.maBanChuyen = 0;
            TatCaDonJPanel.maHDChuyen = 0;

            return;
        }

        title = "TMTP";
        message = "Bạn có muốn thoát trước khi lưu không ?";

        MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
        obj.showMessage(title, message);
        if (obj.getMessageType() == MessageJDialog.MessageType.OK) {

            btnMenu.setBackground(Color.decode("#2C4462"));
            btnTatCaDon.setBackground(Color.decode("#141C37"));
            btnSoDoBan.setBackground(Color.decode("#141C37"));

            CenterPanel.removeAll();
            CenterPanel.repaint();
            CenterPanel.add(new MenuJPanel(this), BorderLayout.WEST);
            CenterPanel.revalidate();
            MenuGoiMonForm.btnLuu.setEnabled(false);
        }


    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (MenuGoiMonForm.btnLuu.isEnabled() == false || MenuGoiMonForm.soBan == 0) {
            title = "TMTP";
            message = "Bạn có chắc muốn thoát luôn ứng dụng không?";

            MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
            obj.showMessage(title, message);
            if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
                System.exit(0);
            }
            return;
        }

        title = "TMTP";
        message = "Bạn có muốn thoát trước khi lưu không ?";

        MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
        obj.showMessage(title, message);
        if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
            System.exit(0);
        }

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnZoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomActionPerformed
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnZoomActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.setExtendedState(MainJFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowActivated

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());

        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManHinhChaoJDiglog dialog = new ManHinhChaoJDiglog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel CenterPanel;
    private com.duan.Design.Button btnExit;
    private com.duan.Design.Button btnMenu;
    private com.duan.Design.Button btnSoDoBan;
    private com.duan.Design.Button btnTatCaDon;
    private com.duan.Design.Button btnZoom;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lblAuth;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblLich;
    // End of variables declaration//GEN-END:variables

    MenuJPanel Menu = new MenuJPanel(this);

    public void chon(Component form, int option) {
        CenterPanel.removeAll();
        CenterPanel.add(Menu, BorderLayout.WEST);
        Menu.setBackgroundEntered(option);
        CenterPanel.repaint();
        CenterPanel.add(form, BorderLayout.CENTER);
        CenterPanel.revalidate();
    }

    @Override
    public void click(int option) {
        switch (option) {
            case 1: {
                form = 1;
                if (infor == 1) {
                    MessageInforJDialog obj = new MessageInforJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    chon(new NhanSuPanel(this), option);
                } else if (infor == 2) {
                    MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
                        chon(new NhanSuPanel(this), option);
                    }
                } else {
                    chon(new NhanSuPanel(this), option);
                }
                infor = 0;
                break;
            }
            case 2: {
                form = 2;
                if (infor == 1) {
                    MessageInforJDialog obj = new MessageInforJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    chon(new MatHangJPanel(this), option);
                } else if (infor == 2) {
                    MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
                        chon(new MatHangJPanel(this), option);
                    }
                } else {
                    chon(new MatHangJPanel(this), option);
                }
                infor = 0;
                break;
            }
            case 3: {
                form = 3;

                if (infor == 1) {
                    MessageInforJDialog obj = new MessageInforJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    chon(new KhachHangJPanel(this), option);
                } else if (infor == 2) {
                    MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
                        chon(new KhachHangJPanel(this), option);
                    }
                } else {
                    chon(new KhachHangJPanel(this), option);
                }
                infor = 0;
                break;
            }
            case 4: {
                chon(new HoaDonJPanel(), option);
                break;
            }
            case 7: {
                Preferences pref = Preferences.userRoot().node("RememberMeDuAn1");
                pref.put("Username", "");
                pref.put("Password", "");
                Auth.clear();
                dispose();
                new LoginJFrame().setVisible(rootPaneCheckingEnabled);
                break;
            }

            case 9: {
                chon(new TongQuanJPanel(this), option);
                break;
            }
            case 10: {
                if (infor == 1) {
                    MessageInforJDialog obj = new MessageInforJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    CenterPanel.removeAll();
                    CenterPanel.repaint();
                    CenterPanel.add(new SoDoBanJPanel(this, soLuongBan), BorderLayout.EAST);
                    CenterPanel.add(new SoDoBanCenterJPanel(soLuongBan, this), BorderLayout.CENTER);
                    CenterPanel.revalidate();
                } else if (infor == 2) {
                    MessageJDialog obj = new MessageJDialog(new javax.swing.JFrame(), true, this);
                    obj.showMessage(title, message);
                    if (obj.getMessageType() == MessageJDialog.MessageType.OK) {
                        MenuGoiMonForm.listChonMon.clear();
                        MenuGoiMonForm.soBan = 0;
                        CenterPanel.removeAll();
                        CenterPanel.repaint();
                        CenterPanel.add(new SoDoBanJPanel(this, soLuongBan), BorderLayout.EAST);
                        CenterPanel.add(new SoDoBanCenterJPanel(soLuongBan, this), BorderLayout.CENTER);
                        CenterPanel.revalidate();
                    }
                } else {
                    CenterPanel.removeAll();
                    CenterPanel.repaint();

                    BanDAO dao = new BanDAO();
                    List list = dao.selectAll();
                    soLuongBan = list.size();

                    CenterPanel.add(new SoDoBanJPanel(this, soLuongBan), BorderLayout.EAST);
                    CenterPanel.add(new SoDoBanCenterJPanel(soLuongBan, this), BorderLayout.CENTER);
                    CenterPanel.revalidate();
                }
                infor = 0;
                break;
            }
            case 11: {
                CenterPanel.removeAll();
                CenterPanel.repaint();
                CenterPanel.add(new ControllJPanel(this), BorderLayout.EAST);
                CenterPanel.add(new TatCaDonJPanel(this), BorderLayout.CENTER);
                CenterPanel.revalidate();
                break;
            }
            case 12: {
                CenterPanel.removeAll();
                CenterPanel.repaint();
                CenterPanel.repaint();
                CenterPanel.add(new MenuGoiMonForm(this), BorderLayout.CENTER);
                CenterPanel.revalidate();
                break;
            }
            case 13: {
                MenuGoiMonForm.p.pnlCenter.remove(pnlHeader);
                MenuGoiMonForm.p.pnlCenter.remove(pnlMenu);
                MenuGoiMonForm.p.pnlCenter.remove(pnlMenuMon);
                MenuGoiMonForm.p.pnlCenter.repaint();
                MenuGoiMonForm.p.pnlCenter.add(new KhachLePanel());
                MenuGoiMonForm.p.pnlCenter.revalidate();
                break;
            }
            case 14: {
                MessageInforJDialog obj = new MessageInforJDialog(new javax.swing.JFrame(), true, this);
                obj.showMessage(title, message);
                infor = 0;
                break;
            }

        }
    }

}
