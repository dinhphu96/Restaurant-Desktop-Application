package com.duan.UI;

import com.duan.DAO.NhanVienDAO;
import com.duan.Model.NhanVien;
import com.duan.Untils.Auth;
import com.duan.Untils.MsgBox;
import com.duan.Untils.Validator;
import com.duan.Untils.XImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class NhanSuJDialog extends javax.swing.JDialog {

    NhanVienDAO dao = new NhanVienDAO();
    int row = -1;
    Test test;
    List<NhanVien> listNhanVien;
    String imageName = "no_image.jpg";
    Validator validator = new Validator();
    MainJFrame main;

    public NhanSuJDialog(java.awt.Frame parent, boolean modal, int row, Test test) {
        super(parent, modal);
        initComponents();
        panelShadow1.setOpaque(false);
        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));
        this.initMoving(NhanSuJDialog.this);
        setLocationRelativeTo(null);

        this.test = test;
        this.row = row;
        listNhanVien = dao.selectAll();

        if (this.row != -1) {
            setForm(listNhanVien.get(this.row));
            updateStatus();
        }
    }

    private int x, y;

    public void initMoving(JDialog frame) {
        pnlHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        pnlHeader.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    void updateStatus() {
        boolean edit = (row >= 0);
        boolean first = (row == 0);
        boolean last = (row == listNhanVien.size() - 1);

        txtMaNV.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void clear() {
        txtHoVaTen.setText("");
        txtMaNV.setText("");
        txtMatKhau.setText("");
        rdoNam.setSelected(true);
        imageName = "no_image.jpg";
        ImageIcon icon = new ImageIcon(new ImageIcon("Images\\" + imageName).getImage().getScaledInstance(152, 185, Image.SCALE_DEFAULT));
        lblHinhAnh.setIcon(icon);
        jDateChooserNgaySinh.setDate(new Date());
        cboVaiTro.setSelectedIndex(0);

        txtDienThoai.setText("");
        txtEmail.setText("");
        jDateChooserNgayBatDau.setDate(new Date());
        txtDiaChi.setText("");

        row = -1;
        updateStatus();
    }

    void setForm(NhanVien nv) {
        txtHoVaTen.setText(nv.getHoTenNV());
        txtMaNV.setText(nv.getMaNV());
        txtMatKhau.setText(nv.getMatKhau());

        if (nv.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        imageName = nv.getHinh();
        ImageIcon icon = new ImageIcon(new ImageIcon("Images\\" + imageName).getImage().getScaledInstance(152, 185, Image.SCALE_DEFAULT));
        lblHinhAnh.setIcon(icon);
        jDateChooserNgaySinh.setDate(nv.getNgaySinh());
        if (nv.getVaiTro().equals("Quản lý")) {
            cboVaiTro.setSelectedIndex(0);
        } else if (nv.getVaiTro().equals("Thu Ngân")) {
            cboVaiTro.setSelectedIndex(1);
        } else if (nv.getVaiTro().equals("Phục vụ")) {
            cboVaiTro.setSelectedIndex(2);
        } else {
            cboVaiTro.setSelectedIndex(3);
        }
        txtDienThoai.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        jDateChooserNgayBatDau.setDate(nv.getNgayBatDau());
        txtDiaChi.setText(nv.getDiaChi());
    }

    public boolean checkForm() {
        if (validator.isEmpty(txtMaNV, "Chưa nhập mã nhân viên")) {
            return false;
        }

        if (validator.isEmpty(txtHoVaTen, "Chưa nhập tên nhân viên")) {
            return false;
        }
        if (validator.isEmpty(txtDienThoai, "Chưa nhập số điện thoại")) {
            return false;
        }
        if (validator.isEmpty(txtEmail, "Chưa nhập Email")) {

            return false;
        }
        if (validator.isEmpty(txtDiaChi, "Chưa nhập địa chỉ")) {
            return false;
        }

        if (imageName.equalsIgnoreCase("no_image.jpg")) {
            MsgBox.alert(this, "Chưa chọn hình");
            return false;
        }

        if (validator.isMa(txtMaNV, "Mã nhân viên sai định dạng")) {
            return false;
        }

        if (validator.checkSDT(txtDienThoai, "SDT không đúng")) {
            return false;
        }

        if (validator.isEmail(txtEmail, "Email không đúng")) {
            return false;
        }

        return true;
    }

    NhanVien getForm() {

        // Bắt lỗi
        NhanVien nv = new NhanVien();

        // Lấy dữ liệu từ form
        String hoTen = txtHoVaTen.getText();
        String maNV = txtMaNV.getText();
        String matKhau = txtMatKhau.getText();
        Boolean gioTinh;
        if (rdoNam.isSelected()) {
            gioTinh = true;
        } else {
            gioTinh = false;
        }
        String hinhAnh = imageName;
        Date ngaySinh = jDateChooserNgaySinh.getDate();
        String vaiTro = cboVaiTro.getItemAt(cboVaiTro.getSelectedIndex());

        String sdt = txtDienThoai.getText();
        String email = txtEmail.getText();
        Date ngayBatDau = jDateChooserNgayBatDau.getDate();
        String diaChi = txtDiaChi.getText();

        // set thông tin nhân viên 
        nv.setHoTenNV(hoTen);
        nv.setMaNV(maNV);
        nv.setMatKhau(matKhau);
        nv.setHinh(hinhAnh);
        nv.setGioiTinh(gioTinh);
        nv.setNgaySinh(ngaySinh);
        nv.setVaiTro(vaiTro);
        nv.setSdt(sdt);
        nv.setEmail(email);
        nv.setNgayBatDau(ngayBatDau);
        nv.setDiaChi(diaChi);

        return nv;
    }

    void first() {
        row = 0;
        setForm(listNhanVien.get(row));
    }

    void prev() {
        if (row > 0) {
            row = row - 1;
            setForm(listNhanVien.get(row));
        }
    }

    void next() {
        if (row < listNhanVien.size() - 1) {
            row = row + 1;
            setForm(listNhanVien.get(row));
        }
    }

    void last() {
        row = listNhanVien.size() - 1;
        setForm(listNhanVien.get(row));
    }

    void insert() {
        NhanVien nv = getForm();
        try {
            dao.insert(nv);
            main.title = "TMTP";
            main.message = "Thêm nhân viên thành công!";
            main.infor = 1;
            dispose();
            test.click(1);
        } catch (Exception e) {
            main.title = "TMTP";
            main.message = "Thêm nhân viên thất bại!";
            test.click(14);
        }

    }

    void update() {
        NhanVien nv = getForm();
        try {
            dao.update(nv);
            main.title = "TMTP";
            main.message = "Cập nhật nhân viên thành công!";
            main.infor = 1;
            dispose();
            test.click(1);
        } catch (Exception e) {
            main.title = "TMTP";
            main.message = "Cập nhật nhân viên thất bại!";
            test.click(14);
        }
    }

    void delete() {
        if (MsgBox.confirm(this, "Bạn có muốn xóa hay không?")) {
            String manv = txtMaNV.getText();
            try {
                dao.delete(manv);
                main.title = "TMTP";
                main.message = "Xóa nhân viên thành công!";
                main.infor = 1;
                dispose();
                test.click(1);
            } catch (Exception e) {
                main.title = "TMTP";
                main.message = "Xóa nhân viên thất bại!";
                test.click(14);
            }
        }
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // lưu hình vào thư mục image
            imageName = file.getName();
            ImageIcon icon = XImage.read(imageName); // đọc hình từ file image

            lblHinhAnh.setIcon(icon);
            lblHinhAnh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        panelShadow1 = new com.duan.Shadow.PanelShadow();
        lblTen = new javax.swing.JLabel();
        lblHoVaTen = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblHinhAnh = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        cboVaiTro = new javax.swing.JComboBox<>();
        lblNgaySinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblDienThoai = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        btnThem = new com.duan.Design.ButtonBoder();
        btnXoa = new com.duan.Design.ButtonBoder();
        btnSua = new com.duan.Design.ButtonBoder();
        btnMoi = new com.duan.Design.ButtonBoder();
        btnFirst = new com.duan.Design.ButtonBoder();
        btnPrev = new com.duan.Design.ButtonBoder();
        btnNext = new com.duan.Design.ButtonBoder();
        btnLast = new com.duan.Design.ButtonBoder();
        pnlHeader = new javax.swing.JPanel();
        btnExit = new com.duan.Design.Button();
        btnMiniSize = new com.duan.Design.Button();
        jDateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        jDateChooserNgayBatDau = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblTen.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTen.setText("Thông tin nhân viên");

        lblHoVaTen.setText("Họ và tên:");

        lblMaNV.setText("Mã nhân viên:");

        lblMatKhau.setText("Mật khẩu:");

        lblGioiTinh.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        lblVaiTro.setText("Vai trò:");

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Thu ngân", "Phục vụ", "Khác" }));

        lblNgaySinh.setText("Ngày sinh:");

        lblEmail.setText("Email:");

        lblDienThoai.setText("Điện thoại:");

        jLabel1.setText("Ngày bắt đầu:");

        jLabel2.setText("Địa chỉ:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setBorder(null);
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-add-20.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemMouseExited(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/layer1.jpg"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaMouseExited(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-update-20.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuaMouseExited(evt);
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-refresh-20.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMoiMouseExited(evt);
            }
        });
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(204, 204, 204));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(204, 204, 204));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/prev.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(204, 204, 204));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        pnlHeader.setBackground(new java.awt.Color(153, 255, 255));
        pnlHeader.setOpaque(false);
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setBackground(new java.awt.Color(255, 51, 51));
        btnExit.setBorder(null);
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.setFocusable(false);
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        pnlHeader.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 48, 25));

        btnMiniSize.setBackground(new java.awt.Color(204, 204, 204));
        btnMiniSize.setBorder(null);
        btnMiniSize.setForeground(new java.awt.Color(255, 255, 255));
        btnMiniSize.setText("_");
        btnMiniSize.setFocusable(false);
        btnMiniSize.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMiniSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiniSizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiniSizeMouseExited(evt);
            }
        });
        pnlHeader.add(btnMiniSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 50, 25));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTen)
                                    .addComponent(lblNgaySinh)
                                    .addComponent(lblDienThoai)
                                    .addComponent(lblMatKhau)
                                    .addComponent(lblMaNV)
                                    .addComponent(lblHoVaTen)
                                    .addGroup(panelShadow1Layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu))
                                    .addComponent(txtMaNV)
                                    .addComponent(txtMatKhau)
                                    .addComponent(txtHoVaTen)
                                    .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDienThoai)
                                    .addComponent(lblGioiTinh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(cboVaiTro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEmail)
                                        .addComponent(lblVaiTro)
                                        .addComponent(jDateChooserNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                        .addComponent(btnChonAnh)
                                        .addGap(39, 39, 39))))
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(404, 404, 404))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addComponent(pnlHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelShadow1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFirst, btnLast, btnMoi, btnNext, btnPrev, btnSua, btnThem, btnXoa});

        panelShadow1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboVaiTro, jDateChooserNgayBatDau, jDateChooserNgaySinh, txtDienThoai, txtEmail, txtHoVaTen, txtMaNV, txtMatKhau});

        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblTen)
                        .addGap(18, 18, 18)
                        .addComponent(lblHoVaTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblMaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMatKhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNgaySinh))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonAnh)
                        .addGap(18, 18, 18)
                        .addComponent(lblVaiTro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(lblDienThoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelShadow1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnMoi, btnSua, btnThem, btnXoa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        // TODO add your handling code here:
        String them = "Images\\icons8-add-20.gif";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnThem.setIcon(icon1);
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        // TODO add your handling code here:
        String them = "Images\\icons8-add-20.png";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnThem.setIcon(icon1);
    }//GEN-LAST:event_btnThemMouseExited

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        // TODO add your handling code here:
        String them = "Images\\trash.gif";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnXoa.setIcon(icon1);
    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        // TODO add your handling code here:
        String them = "Images\\layer1.jpg";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnXoa.setIcon(icon1);
    }//GEN-LAST:event_btnXoaMouseExited

    private void btnSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseEntered
        // TODO add your handling code here:
        String them = "Images\\icons8-update-20.gif";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnSua.setIcon(icon1);
    }//GEN-LAST:event_btnSuaMouseEntered

    private void btnSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseExited
        // TODO add your handling code here:
        String them = "Images\\icons8-update-20.png";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnSua.setIcon(icon1);
    }//GEN-LAST:event_btnSuaMouseExited

    private void btnMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseEntered
        // TODO add your handling code here:
        String them = "Images\\icons8-refresh-20.gif";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnMoi.setIcon(icon1);
    }//GEN-LAST:event_btnMoiMouseEntered

    private void btnMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseExited
        // TODO add your handling code here:
        String them = "Images\\icons8-refresh-20.png";
        ImageIcon icon1 = new ImageIcon(new ImageIcon(them).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        btnMoi.setIcon(icon1);
    }//GEN-LAST:event_btnMoiMouseExited

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(Color.decode("#FF6B6B"));
        btnExit.setForeground(Color.RED);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMiniSizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniSizeMouseEntered
        btnMiniSize.setBackground(Color.BLACK);
        btnMiniSize.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnMiniSizeMouseEntered

    private void btnMiniSizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniSizeMouseExited
        btnMiniSize.setBackground(Color.LIGHT_GRAY);
        btnMiniSize.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnMiniSizeMouseExited

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        chonAnh();
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền thêm Nhân viên!";
            test.click(14);
        } else {
            if (!checkForm()) {
                return;
            }
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền xóa Nhân viên!";
            test.click(14);
        } else {
            delete();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền sửa Nhân viên!";
            test.click(14);
        } else {
            if (!checkForm()) {
                return;
            }
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        first();
        updateStatus();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
        updateStatus();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
        updateStatus();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
        updateStatus();
    }//GEN-LAST:event_btnLastActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private com.duan.Design.Button btnExit;
    private com.duan.Design.ButtonBoder btnFirst;
    private com.duan.Design.ButtonBoder btnLast;
    private com.duan.Design.Button btnMiniSize;
    private com.duan.Design.ButtonBoder btnMoi;
    private com.duan.Design.ButtonBoder btnNext;
    private com.duan.Design.ButtonBoder btnPrev;
    private com.duan.Design.ButtonBoder btnSua;
    private com.duan.Design.ButtonBoder btnThem;
    private com.duan.Design.ButtonBoder btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JFileChooser fileChooser;
    private com.toedter.calendar.JDateChooser jDateChooserNgayBatDau;
    private com.toedter.calendar.JDateChooser jDateChooserNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblVaiTro;
    private com.duan.Shadow.PanelShadow panelShadow1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
