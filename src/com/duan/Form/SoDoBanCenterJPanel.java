package com.duan.Form;

import com.duan.Design.RoundedPanel;
import com.duan.DAO.BanDAO;
import com.duan.DAO.HoaDonDAO;
import com.duan.Messege.MessageJDialog2;
import com.duan.Model.Ban;
import static com.duan.UI.MainJFrame.message;
import static com.duan.UI.MainJFrame.title;
import com.duan.UI.Test;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class SoDoBanCenterJPanel extends javax.swing.JPanel {

    private Test test;
    int x = 40;
    int y = 25;
    private BanDAO daoBAN = new BanDAO();
    List<Ban> listBan;
    boolean icon = true;
    private HoaDonDAO daoHD = new HoaDonDAO();

    public SoDoBanCenterJPanel(int soLuongBan, Test t) {
        initComponents();
        srollpane.setSize(1200, 500);
        this.setSize(1200, 500);
        this.setBackground(Color.decode("#2C4462"));
        srollpane.setBorder(null);
        jp.setPreferredSize(new Dimension(800, 500));
        jp.setBackground(Color.decode("#2C4462"));
        this.test = t;

        listBan = daoBAN.selectAll();

        for (int i = 1; i <= soLuongBan; i++) {
            jp.add(ban(i, listBan.get(i - 1)));
            if (i % 5 == 0) {
                y += 128;
                x = 40;
                jp.setPreferredSize(new Dimension(800, y + 128));
            }
        }
    }

    // Tạo thông tin bàn
    public JPanel ban(int i, Ban ban) {
        RoundedPanel banPanel;
        JLabel trangThailbl = new JLabel();

        if (ban.isTrangThai()) {
            banPanel = new RoundedPanel(20, Color.CYAN);
            trangThailbl.setText("Có khách");
        } else {
            banPanel = new RoundedPanel(20, Color.WHITE);
            trangThailbl.setText("Bàn trống");
        }

        banPanel.setOpaque(false);
        JLabel tenlbl = new JLabel("Bàn " + ban.getMaBan());
        tenlbl.setHorizontalAlignment(JLabel.CENTER);
        tenlbl.setPreferredSize(new Dimension(178, 30));
        tenlbl.setFont(new Font("UI Segoe", Font.BOLD, 16));

        trangThailbl.setHorizontalAlignment(JLabel.CENTER);
        trangThailbl.setFont(new Font("UI Segoe", Font.BOLD, 16));
        trangThailbl.setPreferredSize(new Dimension(178, 30));

        JSeparator sep = new JSeparator();
        sep.setPreferredSize(new Dimension(150, 4));

        banPanel.setSize(178, 108);

        banPanel.add(tenlbl);
        banPanel.add(sep);
        banPanel.add(trangThailbl);

        banPanel.setLocation(x, y);
        String mm = listBan.get(i - 1).getMaBan() + "";
        banPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        banPanel.setName(mm);
        banPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClicked(evt);
            }
        });

        x += 218;
        return banPanel;
    }

    public void MouseClicked(java.awt.event.MouseEvent evt) {

        JPanel o = (JPanel) evt.getSource();
        String name = o.getName();
        setting(name);
    }

    public void setting(String maban) {
        int ten = Integer.parseInt(maban);

        if (TatCaDonJPanel.maBanChuyen != 0 && TatCaDonJPanel.maHDChuyen != 0) {
            List<Ban> listBanCoKhach = daoBAN.selectBanCoKhach();
            for (Ban x : listBanCoKhach) {
                if (ten == x.getMaBan()) {
                    title = "TMTP";
                    message = "Bàn này có khách rồi!";
                    test.click(14);
                    return;
                }
            }

            title = "TMTP";
            message = "Chuyển sang bàn " + ten;

            MessageJDialog2 obj = new MessageJDialog2(new javax.swing.JFrame(), true);
            obj.showMessage(title, message);
            if (obj.getMessageType() == MessageJDialog2.MessageType.OK) {

                daoHD.updateMaBan(ten, TatCaDonJPanel.maHDChuyen);

                Ban b = daoBAN.selectById(TatCaDonJPanel.maBanChuyen);
                b.setTrangThai(false);
                daoBAN.update(b);

                Ban b2 = daoBAN.selectById(ten);
                b2.setTrangThai(true);
                daoBAN.update(b2);

                title = "TMTP";
                message = "Chuyển bàn thành công";
                test.click(14);
                test.click(11);
                TatCaDonJPanel.maBanChuyen = 0;
                TatCaDonJPanel.maHDChuyen = 0;
            }

        } else {

            MenuGoiMonForm.soBan = ten;

            MenuGoiMonForm.listChonMon.clear();

            test.click(12);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        srollpane = new javax.swing.JScrollPane();
        jp = new javax.swing.JPanel();

        javax.swing.GroupLayout jpLayout = new javax.swing.GroupLayout(jp);
        jp.setLayout(jpLayout);
        jpLayout.setHorizontalGroup(
            jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );
        jpLayout.setVerticalGroup(
            jpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        srollpane.setViewportView(jp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srollpane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srollpane)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jp;
    private javax.swing.JScrollPane srollpane;
    // End of variables declaration//GEN-END:variables
}
