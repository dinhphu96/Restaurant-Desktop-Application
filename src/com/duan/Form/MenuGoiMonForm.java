package com.duan.Form;

import com.duan.DAO.BanDAO;
import com.duan.DAO.HoaDonChiTietDAO;
import com.duan.DAO.HoaDonDAO;
import com.duan.DAO.KhachHangDAO;
import com.duan.DAO.MatHangDAO;
import com.duan.Model.Ban;
import com.duan.Model.ChonMon;
import com.duan.Model.HoaDon;
import com.duan.Model.HoaDonChiTiet;
import com.duan.Model.KhachHang;
import com.duan.Model.MatHang;
import com.duan.UI.MainJFrame;
import com.duan.UI.Test;
import com.duan.UI.ThanhToanJDialog;
import com.duan.Untils.Auth;
import com.duan.Untils.MsgBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuGoiMonForm extends javax.swing.JPanel implements Test {

    public static Test test;
    private MatHangDAO dao = new MatHangDAO();
    List<MatHang> list = dao.selectAll();
    private HoaDonDAO daoHD = new HoaDonDAO();
    private KhachHangDAO daoKH = new KhachHangDAO();
    private HoaDonChiTietDAO daoHDCT = new HoaDonChiTietDAO();
    private BanDAO daoBan = new BanDAO();
    private MatHangDAO daoMH = new MatHangDAO();
    DecimalFormat df = new DecimalFormat("###,###,###,###");
    String src = "Images\\";
    int x = 5, y = 10;
    private int row = -1;
    public static MenuGoiMonForm p = new MenuGoiMonForm(test);
    public static List<ChonMon> listChonMon = new ArrayList<>();
    public static int soBan = 0;
    private int maTonTai = 0;

    public MenuGoiMonForm(Test t) {
        initComponents();
        this.test = t;
        btnKhach.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSoLuongKhach.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnlMon.setBackground(Color.decode("#2C4462"));

        for (int i = 0; i < list.size(); i++) {
            if (i % 5 == 0 && i != 0) {
                x = 5;
                y += 180;
            }
            pnlMon.add(addMon(i));
            pnlMon.setPreferredSize(new Dimension(800, y + 180));
        }

        tblChonMon.setRowHeight(30);
        tblChonMon.getTableHeader().setUI(null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        tblChonMon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblChonMon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblChonMon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        pnlBut.setBackground(Color.decode("#0089FF"));
        tblChonMon.setBackground(Color.WHITE);
        pnlKhuVuc.setBackground(Color.white);

        lblSoBan.setText("Bàn " + soBan);
        btnLuu.setEnabled(false);
        btnThanhToan.setEnabled(false);

        List<HoaDon> listHDChuaThanhToan = daoHD.selectByTrangThai(0);
        if (listHDChuaThanhToan != null) {
            for (HoaDon a : listHDChuaThanhToan) {
                if (a.getMaBan() == MenuGoiMonForm.soBan) {
                    maTonTai = a.getMaHD();
                    List<HoaDonChiTiet> listCTHD = daoHDCT.selectByMaHD(a.getMaHD());
                    KhachHang kh = daoKH.selectById(a.getMaKH());

                    if (kh == null) {
                        btnKhach.setText("Khách lẻ");
                    } else {
                        btnKhach.setText(kh.getTenKH());
                    }

                    for (HoaDonChiTiet b : listCTHD) {
                        int giaa = dao.getGiaMH(b.getMaMH());
                        String ten = dao.getTenMH(b.getMaMH());
                        MatHang mhh = daoMH.selectById(String.valueOf(b.getMaMH()));

                        ChonMon cm = new ChonMon();
                        cm.setTenMon(ten);
                        cm.setSoLuong(b.getSoLuong());
                        cm.setDonVi(mhh.getDonVi());
                        cm.setGia(giaa * b.getSoLuong());
                        MenuGoiMonForm.listChonMon.add(cm);

                    }
                    btnXoa.setEnabled(false);
                    fillTable();
                    btnLuu.setEnabled(false);
                    break;
                }
            }
        }

    }

    public JPanel addMon(int i) {
        JPanel jp = new JPanel();
        jp.setSize(165, 170);
        jp.setBackground(Color.WHITE);

        JLabel lbl = new JLabel();

        ImageIcon icon1 = new ImageIcon(new ImageIcon(src + list.get(i).getHinh()).getImage().getScaledInstance(155, 130, Image.SCALE_DEFAULT));
        lbl.setIcon(icon1);
        lbl.setText(list.get(i).getTenMH());
        lbl.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
        lbl.setVerticalTextPosition(JLabel.BOTTOM); //set text TOP,CENTER, BOTTOM of imageicon
        lbl.setForeground(Color.BLACK); //set font color of text
        lbl.setFont(new Font("UI Segoe", Font.BOLD, 16)); //set font of text
        lbl.setIconTextGap(0); //set gap of text to image
        lbl.setOpaque(false); //display background color
        lbl.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within lbl
        lbl.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within lbl

        jp.setLocation(x, y);
        x += 170;
        jp.add(lbl);

        jp.setName(String.valueOf(list.get(i).getMaMH()));

        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClicked(evt);
            }
        });

        return jp;
    }

    public void fillTable() {
        if (MenuGoiMonForm.listChonMon.size() > 0) {
            btnLuu.setEnabled(true);
            btnThanhToan.setEnabled(true);
        } else {
            btnLuu.setEnabled(false);
            btnThanhToan.setEnabled(false);
        }

        DefaultTableModel model = (DefaultTableModel) tblChonMon.getModel();
        model.setRowCount(0);

        int tongTien = 0;
        try {

            for (int i = 0; i < listChonMon.size(); i++) {
                Object[] row = {listChonMon.get(i).getTenMon(), listChonMon.get(i).getSoLuong(), listChonMon.get(i).getDonVi(), listChonMon.get(i).getGia()};
                model.addRow(row);
                tongTien += (Integer) tblChonMon.getValueAt(i, 3);
            }
            lblTongTien.setText(String.valueOf(tongTien));
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            e.printStackTrace();
        }
    }

    public void MouseClicked(java.awt.event.MouseEvent evt) {
        row = -1;
        JPanel o = (JPanel) evt.getSource();
        String maMH = o.getName();
        MatHang mh = dao.selectById(maMH);
        if (MenuGoiMonForm.listChonMon != null) {

            for (int i = 0; i < MenuGoiMonForm.listChonMon.size(); i++) {
                if (MenuGoiMonForm.listChonMon.get(i).getTenMon().equalsIgnoreCase(mh.getTenMH())) {

                    row = i;
                    tblChonMon.setRowSelectionInterval(row, row);
                    return;
                }
            }
        }

        int gia1 = 0;
        if (MenuGoiMonForm.listChonMon == null) {
            btnKhach.setEnabled(true);
            btnXoa.setEnabled(true);

            gia1 = mh.getGiaBan();
            ChonMon cm = new ChonMon();
            cm.setTenMon(mh.getTenMH());
            cm.setSoLuong(1);
            cm.setGia(mh.getGiaBan());
            cm.setDonVi(mh.getDonVi());
            MenuGoiMonForm.listChonMon.add(cm);
            fillTable();
            row = MenuGoiMonForm.listChonMon.size() - 1;
            tblChonMon.setRowSelectionInterval(row, row);
        } else {

            gia1 = mh.getGiaBan();
            ChonMon cm = new ChonMon();
            cm.setTenMon(mh.getTenMH());
            cm.setSoLuong(1);
            cm.setGia(mh.getGiaBan());
            cm.setDonVi(mh.getDonVi());
            MenuGoiMonForm.listChonMon.add(cm);
            fillTable();
            row = MenuGoiMonForm.listChonMon.size() - 1;
            tblChonMon.setRowSelectionInterval(row, row);
        }
    }

    public void addForm(Component com) {
        com.setBackground(new Color(20, 28, 55));
        pnlCenter.add(com, BorderLayout.CENTER);
        pnlCenter.revalidate();
        pnlMain.setBackground(Color.decode("#141C37"));
    }
    public void TimMon() {
        x = 5;
        y = 10;
        list.clear();
        list = dao.TimMon("%" + txtTimMA.getText() + "%");
        pnlMon.removeAll();
        pnlMon.repaint();
        for (int i = 0; i < list.size(); i++) {
            if (i % 5 == 0 && i != 0) {
                x = 5;
                y += 180;
            }
            pnlMon.add(addMon(i));
            pnlMon.setPreferredSize(new Dimension(800, y + 180));
        }
        pnlMon.revalidate();
    }

    public void showMenu(String tenMon) {
        x = 5;
        y = 10;
        list.clear();
        if (tenMon.equals("all")) {
            list = dao.selectAll();
        } else {
            list = dao.getmaMH(tenMon);
        }
        pnlMon.removeAll();
        pnlMon.repaint();
        for (int i = 0; i < list.size(); i++) {
            if (i % 5 == 0 && i != 0) {
                x = 5;
                y += 180;
            }
            pnlMon.add(addMon(i));
            pnlMon.setPreferredSize(new Dimension(800, y + 180));
        }
        pnlMon.revalidate();
    }

    public void luuHDCT(int maHD) {

        for (int i = 0; i < tblChonMon.getRowCount(); i++) {

            HoaDonChiTiet HDCT = new HoaDonChiTiet();

            HDCT.setMaHD(maHD);

            String tenMH = (String) tblChonMon.getValueAt(i, 0);
            int maMH = dao.getmaMH2(tenMH);
            HDCT.setMaMH(maMH);

            int sl = (Integer) tblChonMon.getValueAt(i, 1);
            HDCT.setSoLuong(sl);
            daoHDCT.insert(HDCT);
        }

    }

    public void luuDon(boolean trangThai) {
        HoaDon hd = new HoaDon();
        List<KhachHang> listKH = daoKH.selectAll();

        Ban b = daoBan.selectById(soBan);
        if (trangThai == false) {
            b.setTrangThai(true);
        } else {
            b.setTrangThai(false);

        }
        daoBan.update(b);

        if (MenuGoiMonForm.btnKhach.getText().equalsIgnoreCase("Khách lẻ")) {
            hd.setThuNgan(Auth.user.getMaNV());

            hd.setTrangThai(trangThai);
            hd.setMaBan(MenuGoiMonForm.soBan);
            hd.setTongTien(Float.parseFloat(lblTongTien.getText()));

            daoHD.insertKhachLe(hd);
            List<HoaDon> listHD = daoHD.selectAll();
            int maHD = listHD.get(listHD.size() - 1).getMaHD();
            luuHDCT(maHD);
        } else {
            for (KhachHang x : listKH) {
                if (x.getTenKH().equalsIgnoreCase(MenuGoiMonForm.btnKhach.getText())) {
                    hd.setMaKH(x.getMaKH());
                    break;
                }
            }

            hd.setThuNgan(Auth.user.getMaNV());

            hd.setTrangThai(trangThai);
            hd.setMaBan(MenuGoiMonForm.soBan);
            hd.setTongTien(Float.parseFloat(lblTongTien.getText()));

            daoHD.insert(hd);
            List<HoaDon> listHD = daoHD.selectAll();
            int maHD = listHD.get(listHD.size() - 1).getMaHD();
            luuHDCT(maHD);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlKhuVuc = new javax.swing.JPanel();
        pnlTinhTien = new javax.swing.JPanel();
        pnlHuy = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        pnlLuuDon = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        pnlThanhToan = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblSoBan = new javax.swing.JLabel();
        ScrollChonMon = new javax.swing.JScrollPane();
        tblChonMon = new javax.swing.JTable();
        pnlBut = new javax.swing.JPanel();
        btnCong = new javax.swing.JButton();
        btnTru = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        btnLoaiMA = new com.duan.Design.Button();
        btnKhach = new com.duan.Design.Button();
        btnSoLuongKhach = new com.duan.Design.Button();
        txtTimMA = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnTatCa = new com.duan.Design.Button();
        btnMonChinh = new com.duan.Design.Button();
        btnMonAnNhe = new com.duan.Design.Button();
        btnTrangMieng = new com.duan.Design.Button();
        btnMonChay = new com.duan.Design.Button();
        btnThucUong = new com.duan.Design.Button();
        pnlMenuMon = new javax.swing.JPanel();
        scrollMenuMon = new java.awt.ScrollPane();
        pnlMon = new javax.swing.JPanel();

        pnlMain.setBackground(new java.awt.Color(44, 68, 98));
        pnlMain.setPreferredSize(new java.awt.Dimension(1366, 768));

        pnlKhuVuc.setBackground(new java.awt.Color(255, 255, 255));

        pnlTinhTien.setLayout(new java.awt.GridLayout(1, 0));

        pnlHuy.setBackground(new java.awt.Color(255, 51, 51));
        pnlHuy.setOpaque(false);

        btnHuy.setBackground(new java.awt.Color(204, 0, 0));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setBorder(null);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHuyLayout = new javax.swing.GroupLayout(pnlHuy);
        pnlHuy.setLayout(pnlHuyLayout);
        pnlHuyLayout.setHorizontalGroup(
            pnlHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
        );
        pnlHuyLayout.setVerticalGroup(
            pnlHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );

        pnlTinhTien.add(pnlHuy);

        pnlLuuDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlLuuDon.setOpaque(false);

        btnLuu.setBackground(new java.awt.Color(102, 255, 102));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setText("Lưu đơn");
        btnLuu.setBorder(null);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLuuDonLayout = new javax.swing.GroupLayout(pnlLuuDon);
        pnlLuuDon.setLayout(pnlLuuDonLayout);
        pnlLuuDonLayout.setHorizontalGroup(
            pnlLuuDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
        );
        pnlLuuDonLayout.setVerticalGroup(
            pnlLuuDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );

        pnlTinhTien.add(pnlLuuDon);

        pnlThanhToan.setBackground(new java.awt.Color(0, 51, 255));
        pnlThanhToan.setOpaque(false);

        btnThanhToan.setBackground(new java.awt.Color(51, 51, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorder(null);
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );

        pnlTinhTien.add(pnlThanhToan);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tổng tiền:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("đ");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(102, 102, 102));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("0");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-gift-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(lblTongTien)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(10, 135, 219));

        lblSoBan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoBan.setForeground(new java.awt.Color(255, 255, 255));
        lblSoBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoBan.setText("Số bàn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSoBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSoBan, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        ScrollChonMon.setBackground(new java.awt.Color(255, 255, 255));
        ScrollChonMon.setBorder(null);
        ScrollChonMon.setOpaque(false);

        tblChonMon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblChonMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChonMon.setFocusable(false);
        tblChonMon.setGridColor(new java.awt.Color(255, 255, 255));
        tblChonMon.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblChonMon.setSelectionBackground(new java.awt.Color(204, 231, 255));
        tblChonMon.setSelectionForeground(new java.awt.Color(0, 136, 255));
        tblChonMon.setShowHorizontalLines(false);
        tblChonMon.setShowVerticalLines(false);
        tblChonMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChonMonMousePressed(evt);
            }
        });
        ScrollChonMon.setViewportView(tblChonMon);
        if (tblChonMon.getColumnModel().getColumnCount() > 0) {
            tblChonMon.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblChonMon.getColumnModel().getColumn(1).setPreferredWidth(1);
            tblChonMon.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblChonMon.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        btnCong.setBackground(new java.awt.Color(255, 255, 255));
        btnCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-plus-48.png"))); // NOI18N
        btnCong.setBorder(null);
        btnCong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCongActionPerformed(evt);
            }
        });

        btnTru.setBackground(new java.awt.Color(255, 255, 255));
        btnTru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/minimize1.png"))); // NOI18N
        btnTru.setBorder(null);
        btnTru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/layer1.jpg"))); // NOI18N
        btnXoa.setBorder(null);
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButLayout = new javax.swing.GroupLayout(pnlBut);
        pnlBut.setLayout(pnlButLayout);
        pnlButLayout.setHorizontalGroup(
            pnlButLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCong, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTru, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlButLayout.setVerticalGroup(
            pnlButLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTru, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnCong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlKhuVucLayout = new javax.swing.GroupLayout(pnlKhuVuc);
        pnlKhuVuc.setLayout(pnlKhuVucLayout);
        pnlKhuVucLayout.setHorizontalGroup(
            pnlKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlKhuVucLayout.createSequentialGroup()
                .addGroup(pnlKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollChonMon, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTinhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlKhuVucLayout.setVerticalGroup(
            pnlKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhuVucLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollChonMon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTinhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(20, 28, 55));

        btnLoaiMA.setBackground(new java.awt.Color(255, 255, 255));
        btnLoaiMA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-restaurant-menu-50.png"))); // NOI18N
        btnLoaiMA.setText("Thực đơn");
        btnLoaiMA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLoaiMA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnKhach.setBackground(new java.awt.Color(255, 255, 255));
        btnKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-customer-64.png"))); // NOI18N
        btnKhach.setText("Khách lẻ");
        btnKhach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnKhach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhachMousePressed(evt);
            }
        });

        btnSoLuongKhach.setBackground(new java.awt.Color(255, 255, 255));
        btnSoLuongKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-customer-30.png"))); // NOI18N
        btnSoLuongKhach.setText("1 Khách");
        btnSoLuongKhach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTimMA.setText("Nhập tên món ăn vào đây ...");
        txtTimMA.setToolTipText("Nhập tên món ăn vào đây ...");
        txtTimMA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimMAMouseClicked(evt);
            }
        });
        txtTimMA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimMAKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-search-35.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addComponent(btnLoaiMA, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimMA, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSoLuongKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLoaiMA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimMA))
                .addContainerGap())
        );

        pnlCenter.add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlMenu.setBackground(new java.awt.Color(20, 28, 55));

        btnTatCa.setText("Tất cả");
        btnTatCa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaActionPerformed(evt);
            }
        });

        btnMonChinh.setText("Món chính");
        btnMonChinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMonChinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonChinhActionPerformed(evt);
            }
        });

        btnMonAnNhe.setText("Món ăn nhẹ");
        btnMonAnNhe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMonAnNhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonAnNheActionPerformed(evt);
            }
        });

        btnTrangMieng.setText("Tráng miệng");
        btnTrangMieng.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTrangMieng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangMiengActionPerformed(evt);
            }
        });

        btnMonChay.setText("Món chay");
        btnMonChay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMonChay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonChayActionPerformed(evt);
            }
        });

        btnThucUong.setText("Thức uống");
        btnThucUong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThucUongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMonChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMonAnNhe, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnTrangMieng, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMonChay, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnThucUong, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMonChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMonAnNhe, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrangMieng, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMonChay, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThucUong, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCenter.add(pnlMenu, java.awt.BorderLayout.WEST);

        pnlMenuMon.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenuMon.setPreferredSize(new java.awt.Dimension(879, 0));

        scrollMenuMon.setPreferredSize(new java.awt.Dimension(869, 0));

        pnlMon.setPreferredSize(new java.awt.Dimension(869, 607));

        javax.swing.GroupLayout pnlMonLayout = new javax.swing.GroupLayout(pnlMon);
        pnlMon.setLayout(pnlMonLayout);
        pnlMonLayout.setHorizontalGroup(
            pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );
        pnlMonLayout.setVerticalGroup(
            pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );

        scrollMenuMon.add(pnlMon);

        javax.swing.GroupLayout pnlMenuMonLayout = new javax.swing.GroupLayout(pnlMenuMon);
        pnlMenuMon.setLayout(pnlMenuMonLayout);
        pnlMenuMonLayout.setHorizontalGroup(
            pnlMenuMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenuMon, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
        );
        pnlMenuMonLayout.setVerticalGroup(
            pnlMenuMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenuMon, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );

        pnlCenter.add(pnlMenuMon, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlKhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1338, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    MainJFrame main;
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (tblChonMon.getRowCount() == 0) {
            test.click(10);
            return;
        }
        main.title = "TMTP";
        main.message = "Bạn có muốn thoát không ?";
        main.infor = 2;
        test.click(10);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (maTonTai != 0) {
            HoaDon hdh = daoHD.selectById(maTonTai);
            String tongTien = lblTongTien.getText();
            tongTien = tongTien.replaceAll(",", "");
            hdh.setTongTien(Float.parseFloat(tongTien));
            if (MenuGoiMonForm.btnKhach.getText().equalsIgnoreCase("Khách lẻ")) {
                daoHD.updateMakhachNull(hdh);

            } else {
                List<KhachHang> listKH = daoKH.selectAll();
                for (KhachHang x : listKH) {
                    if (x.getTenKH().equalsIgnoreCase(MenuGoiMonForm.btnKhach.getText())) {
                        hdh.setMaKH(x.getMaKH());
                        daoHD.updateMakhach(hdh);
                        break;
                    }
                }
            }
            daoHDCT.deleteAllByMaHD(maTonTai);
            luuHDCT(maTonTai);
            maTonTai = 0;
            MainJFrame.infor = 0;
            test.click(10);
        } else {
            if (listChonMon != null) {
                luuDon(false);
                soBan = 0;
                MainJFrame.infor = 0;
                test.click(10);

            }
        }
        MenuGoiMonForm.soBan = 0;
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (maTonTai != 0) {
            if (btnLuu.isEnabled() == true) {

                HoaDon hdh = daoHD.selectById(maTonTai);
                String tongTien = lblTongTien.getText();
                tongTien = tongTien.replaceAll(",", "");
                hdh.setTongTien(Float.parseFloat(tongTien));
                if (MenuGoiMonForm.btnKhach.getText().equalsIgnoreCase("Khách lẻ")) {
                    daoHD.updateMakhachNull(hdh);

                } else {
                    List<KhachHang> listKH = daoKH.selectAll();
                    for (KhachHang x : listKH) {
                        if (x.getTenKH().equalsIgnoreCase(MenuGoiMonForm.btnKhach.getText())) {
                            hdh.setMaKH(x.getMaKH());
                            daoHD.updateMakhach(hdh);
                            break;
                        }
                    }
                }

                daoHDCT.deleteAllByMaHD(maTonTai);
                luuHDCT(maTonTai);
                ThanhToanJDialog.tongTien = lblTongTien.getText();
                new ThanhToanJDialog(new javax.swing.JFrame(), true, maTonTai, this, soBan).setVisible(true);
                maTonTai = 0;
                test.click(10);
            } else {
                ThanhToanJDialog.tongTien = lblTongTien.getText();
                new ThanhToanJDialog(new javax.swing.JFrame(), true, maTonTai, this, soBan).setVisible(true);
                maTonTai = 0;
                if (ThanhToanJDialog.check == 1) {
                    test.click(10);
                }

            }

        } else {
            if (MenuGoiMonForm.listChonMon != null) {
                ThanhToanJDialog.tongTien = lblTongTien.getText();
                new ThanhToanJDialog(new javax.swing.JFrame(), true, maTonTai, this, soBan).setVisible(true);

                if (ThanhToanJDialog.check == 1) {
                    test.click(10);
                }
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblChonMonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChonMonMousePressed
        row = tblChonMon.getSelectedRow();
    }//GEN-LAST:event_tblChonMonMousePressed

    private void btnCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCongActionPerformed
        if (row > -1) {
            int sl = (Integer) tblChonMon.getValueAt(row, 1) + 1;
            int giaa = dao.getGiaMH(dao.getmaMH2((String) tblChonMon.getValueAt(row, 0))) * sl;
            MenuGoiMonForm.listChonMon.get(row).setSoLuong(sl);
            MenuGoiMonForm.listChonMon.get(row).setGia(giaa);
            fillTable();

            tblChonMon.setRowSelectionInterval(row, row);
        }
    }//GEN-LAST:event_btnCongActionPerformed

    private void btnTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruActionPerformed
        if (row > -1) {
            if (MenuGoiMonForm.listChonMon.get(row).getSoLuong() > 1) {
                int sl = (Integer) tblChonMon.getValueAt(row, 1) - 1;
                int giaa = dao.getGiaMH(dao.getmaMH2((String) tblChonMon.getValueAt(row, 0))) * sl;
                MenuGoiMonForm.listChonMon.get(row).setSoLuong(sl);
                MenuGoiMonForm.listChonMon.get(row).setGia(giaa);
                fillTable();
                tblChonMon.setRowSelectionInterval(row, row);
            }
        }
    }//GEN-LAST:event_btnTruActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (row > -1) {
            MenuGoiMonForm.listChonMon.remove(row);
            fillTable();
            row = -1;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnKhachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachMousePressed
        if (!MenuGoiMonForm.listChonMon.isEmpty()) {
            btnLuu.setEnabled(true);
        }
        test.click(13);
    }//GEN-LAST:event_btnKhachMousePressed

    private void btnTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaActionPerformed
        showMenu("all");
    }//GEN-LAST:event_btnTatCaActionPerformed

    private void btnMonChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonChinhActionPerformed
        showMenu("Món chính");
    }//GEN-LAST:event_btnMonChinhActionPerformed

    private void btnMonAnNheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAnNheActionPerformed
        showMenu("Món nhẹ");
    }//GEN-LAST:event_btnMonAnNheActionPerformed

    private void btnTrangMiengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangMiengActionPerformed
        showMenu("Tráng miệng");
    }//GEN-LAST:event_btnTrangMiengActionPerformed

    private void btnMonChayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonChayActionPerformed
        showMenu("Món chay");
    }//GEN-LAST:event_btnMonChayActionPerformed

    private void btnThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThucUongActionPerformed
        showMenu("Giải khát");
    }//GEN-LAST:event_btnThucUongActionPerformed

    private void txtTimMAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimMAKeyReleased
        TimMon();
    }//GEN-LAST:event_txtTimMAKeyReleased

    private void txtTimMAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMAMouseClicked
        // TODO add your handling code here:
        txtTimMA.setText("");
    }//GEN-LAST:event_txtTimMAMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollChonMon;
    private javax.swing.JButton btnCong;
    private javax.swing.JButton btnHuy;
    public static com.duan.Design.Button btnKhach;
    private com.duan.Design.Button btnLoaiMA;
    public static javax.swing.JButton btnLuu;
    private com.duan.Design.Button btnMonAnNhe;
    private com.duan.Design.Button btnMonChay;
    private com.duan.Design.Button btnMonChinh;
    private com.duan.Design.Button btnSoLuongKhach;
    private com.duan.Design.Button btnTatCa;
    private javax.swing.JButton btnThanhToan;
    private com.duan.Design.Button btnThucUong;
    private com.duan.Design.Button btnTrangMieng;
    private javax.swing.JButton btnTru;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblSoBan;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlBut;
    public static javax.swing.JPanel pnlCenter;
    public static javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlHuy;
    private javax.swing.JPanel pnlKhuVuc;
    private javax.swing.JPanel pnlLuuDon;
    private javax.swing.JPanel pnlMain;
    public static javax.swing.JPanel pnlMenu;
    public static javax.swing.JPanel pnlMenuMon;
    private javax.swing.JPanel pnlMon;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JPanel pnlTinhTien;
    private java.awt.ScrollPane scrollMenuMon;
    public static javax.swing.JTable tblChonMon;
    private javax.swing.JTextField txtTimMA;
    // End of variables declaration//GEN-END:variables

    @Override
    public void click(int option) {
        switch (option) {
            case 1: {
                luuDon(true);
            }
        }
    }
}
