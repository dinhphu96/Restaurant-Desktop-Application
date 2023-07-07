package com.duan.UI;

import com.duan.DAO.LoaiMHDAO;
import com.duan.DAO.MatHangDAO;
import com.duan.Form.MatHangJPanel;
import com.duan.Model.LoaiMH;
import com.duan.Model.MatHang;
import com.duan.Untils.Auth;
import com.duan.Untils.MsgBox;
import com.duan.Untils.XImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import com.duan.Untils.Validator;

public class MatHangJDialog extends javax.swing.JDialog {

    private String hinh = "";
    private MatHangDAO dao = new MatHangDAO();
    private LoaiMHDAO daoLoai = new LoaiMHDAO();
    private List<MatHang> list = dao.ShowTable();
    MatHangJPanel mathang;
    private Test test;
    public static int layMa;
    MHInterface MHP;

    public MatHangJDialog(java.awt.Frame parent, boolean modal, Test test, MHInterface MHP) {
        super(parent, modal);
        initComponents();
        this.test = test;
        this.MHP = MHP;
        panelShadow1.setOpaque(false);
        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));
        this.initMoving(MatHangJDialog.this);
        setLocationRelativeTo(null);

        fillComboBoxLoaiMonAn();
        fillComboBoxDonVi();

        if (MatHangJPanel.row <= -1) {
            panelShadow1.remove(btnXoa);
            panelShadow1.remove(btnSua);
            panelShadow1.repaint();
            clearForm();
        } else {
            panelShadow1.remove(btnMoi);
            panelShadow1.remove(btnThem);
            panelShadow1.repaint();
            edit();
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
            @Override
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    public void setForm(MatHang mh) {

        txtTenMA.setText(mh.getTenMH());
        cboDonVi.setSelectedItem(mh.getDonVi());
        txtGiaBan.setText(String.valueOf(mh.getGiaBan()));
        txtGiaVon.setText(String.valueOf(mh.getGiaGoc()));
        cboLoaiMA.setSelectedItem(mh.getTenLoai());
        hinh = mh.getHinh();
        ImageIcon icon = new ImageIcon(new ImageIcon("Images\\" + hinh).getImage().getScaledInstance(159, 142, Image.SCALE_DEFAULT));
        lblHinhMH.setIcon(icon);
    }

    public MatHang getForm() {
        MatHang mh = new MatHang();

        if (MatHangJPanel.row > -1) {
            mh.setMaMH(layMa);
        }
        mh.setTenMH(txtTenMA.getText());
        mh.setDonVi((String) (cboDonVi.getSelectedItem()));
        mh.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
        mh.setGiaGoc(Integer.parseInt(txtGiaVon.getText()));
        mh.setMaLoai((String) (daoLoai.getmaLoai(cboLoaiMA.getSelectedItem())));
        mh.setHinh(hinh);
        return mh;
    }

    public void clearForm() {
        MatHang mh = new MatHang();
        setForm(mh);
        MatHangJPanel.row = -1;
        txtGiaBan.setText("");
        txtGiaVon.setText("");
        updateStatus();
    }

    public void insert() {

        MatHang mh = getForm();
        try {
            dao.insert(mh);
            MainJFrame.title = "TMTP";
            MainJFrame.message = "Thêm mới thành công!";
            
            mathang.inf = 1;
            dispose();
            MHP.chon(2);
        } catch (Exception e) {
            MainJFrame.title = "TMTP";
            MainJFrame.message = "Thêm mới thất bại!";
            mathang.inf = 1;
            MHP.chon(2);
            e.printStackTrace();
        }

    }

    public void update() {
        if (!Auth.isManager()) {
            MainJFrame.title = "TMTP";
            MainJFrame.message = "Bạn không có quyền sửa món ăn!";
            mathang.inf = 1;
            MHP.chon(2);
        } else {
            MatHang mh = getForm();
            try {
                dao.update(mh);
                MainJFrame.title = "TMTP";
                MainJFrame.message = "Cập nhật thành công!";
                mathang.inf = 1;
                dispose();
                MHP.chon(2);
            } catch (Exception e) {
                MainJFrame.title = "TMTP";
                MainJFrame.message = "Cập nhật thất bại!";
                mathang.inf = 1;
                MHP.chon(2);
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        if (!Auth.isManager()) {
            MainJFrame.title = "TMTP";
            MainJFrame.message = "Bạn không có quyền xóa món ăn!";
            mathang.inf = 1;
            MHP.chon(2);
        } else {
            if (MsgBox.confirm(this, "Bạn thực sự muốn xóa món ăn này?")) {
                try {
                    dao.delete(String.valueOf(MatHangJDialog.layMa));
                    MainJFrame.title = "TMTP";
                    MainJFrame.message = "Xóa thành công!";

                    mathang.inf = 1;
                    dispose();
                    MHP.chon(2);
                } catch (Exception e) {
                    MainJFrame.title = "TMTP";
                    MainJFrame.message = "Mặt hàng đã nằm trong hóa đơn chi tiết. Xóa thất bại!";
                    mathang.inf = 1;
                    MHP.chon(2);
                    
                }
            }
        }
    }

    public void edit() {
        MatHang mh = list.get(MatHangJPanel.row);
        setForm(mh);
        MHP.chon(3);
        updateStatus();
        ;
    }
    MatHangJPanel mhang = new MatHangJPanel(test);

    public void first() {
        MatHangJPanel.row = 0;
        edit();
    }

    public void next() {
        if (MatHangJPanel.row < list.size() - 1) {
            MatHangJPanel.row++;
            edit();
        }
    }

    public void prev() {
        if (MatHangJPanel.row > 0) {
            MatHangJPanel.row--;
            edit();
        }
    }

    public void last() {
        MatHangJPanel.row = list.size() - 1;
        edit();
    }

    public void updateStatus() {
        boolean edit = (MatHangJPanel.row >= 0);
        boolean first = (MatHangJPanel.row == 0);
        boolean last = (MatHangJPanel.row == list.size() - 1);

        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void fillComboBoxLoaiMonAn() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiMA.getModel();
        model.removeAllElements();
        List<LoaiMH> list = daoLoai.selectAll();
        for (LoaiMH lmh : list) {
            model.addElement(lmh.getTenLoai());
        }
    }

    void fillComboBoxDonVi() {
        String[] donv = {"Dĩa", "Tô", "Nồi", "Niêu", "Xoong", "Chảo", "Kg", "Thúng", "Hũ", "Lọ", "Ly", "Lon"};
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDonVi.getModel();
        model.removeAllElements();
        for (String dv : donv) {
            model.addElement(dv);
        }
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file); // lưu hình vào thư mục image
            hinh = file.getName();
            ImageIcon icon = XImage.read(hinh); // đọc hình từ file image

            lblHinhMH.setIcon(icon);
            lblHinhMH.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }

    public boolean checkForm() {
        if (Validator.isEmpty(txtTenMA, "Chưa nhập tên mặt hàng")) {
            return false;
        }

        if (cboDonVi.getSelectedItem() == null) {
            MsgBox.alert(this, "Chưa chọn đơn vị");
            return false;
        }

        if (Validator.isEmpty(txtGiaBan, "Chưa nhập giá bán")) {
            return false;
        }

        if (Validator.isEmpty(txtGiaVon, "Chưa nhập giá vốn")) {
            return false;
        }

        if (Validator.isNumber(txtGiaBan, "Giá bán phải nhập số")) {
            return false;
        }

        if (Validator.isNumber(txtGiaVon, "Giá vốn phải nhập số")) {
            return false;
        }

        if (this.hinh == null) {
            MsgBox.alert(this, "Chưa chọn hình");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        panelShadow1 = new com.duan.Shadow.PanelShadow();
        lblTenMA = new javax.swing.JLabel();
        lblTenMonAn = new javax.swing.JLabel();
        txtTenMA = new javax.swing.JTextField();
        lblLoaiMA = new javax.swing.JLabel();
        cboLoaiMA = new javax.swing.JComboBox<>();
        lblDonVi = new javax.swing.JLabel();
        cboDonVi = new javax.swing.JComboBox<>();
        lblGiaBan = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        lblGiaVon = new javax.swing.JLabel();
        txtGiaVon = new javax.swing.JTextField();
        pnlHeader = new javax.swing.JPanel();
        btnExit = new com.duan.Design.Button();
        btnMiniSize = new com.duan.Design.Button();
        btnThem = new com.duan.Design.ButtonBoder();
        btnXoa = new com.duan.Design.ButtonBoder();
        btnSua = new com.duan.Design.ButtonBoder();
        btnMoi = new com.duan.Design.ButtonBoder();
        btnFirst = new com.duan.Design.ButtonBoder();
        btnPrev = new com.duan.Design.ButtonBoder();
        btnNext = new com.duan.Design.ButtonBoder();
        btnLast = new com.duan.Design.ButtonBoder();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblHinhMH = new javax.swing.JLabel();
        buttonBoder1 = new com.duan.Design.ButtonBoder();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        lblTenMA.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTenMA.setText("Tên của món ăn");

        lblTenMonAn.setText("Tên món ăn:");

        lblLoaiMA.setText("Loại món ăn:");

        lblDonVi.setText("Đơn vị:");

        lblGiaBan.setText("Giá bán:");

        lblGiaVon.setText("Giá vốn:");

        pnlHeader.setBackground(new java.awt.Color(153, 255, 255));
        pnlHeader.setOpaque(false);
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setBackground(new java.awt.Color(255, 51, 51));
        btnExit.setBorder(null);
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
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
        pnlHeader.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 48, 25));

        btnMiniSize.setBackground(new java.awt.Color(204, 204, 204));
        btnMiniSize.setBorder(null);
        btnMiniSize.setText("_");
        btnMiniSize.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMiniSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiniSizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiniSizeMouseExited(evt);
            }
        });
        btnMiniSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiniSizeActionPerformed(evt);
            }
        });
        pnlHeader.add(btnMiniSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 50, 25));

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
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

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/delete.png"))); // NOI18N
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

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
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

        btnMoi.setBackground(new java.awt.Color(204, 204, 204));
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
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFirstMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFirstMouseExited(evt);
            }
        });
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(204, 204, 204));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/prev.png"))); // NOI18N
        btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrevMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrevMouseExited(evt);
            }
        });
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/next.png"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNextMouseExited(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(204, 204, 204));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/last.png"))); // NOI18N
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLastMouseExited(evt);
            }
        });
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jPanel3.setOpaque(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        lblHinhMH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhMH, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhMH, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        );

        buttonBoder1.setText("Chọn ảnh");
        buttonBoder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBoder1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(buttonBoder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonBoder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addComponent(lblGiaBan)
                                .addGap(196, 196, 196)
                                .addComponent(lblGiaVon))
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboDonVi, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblLoaiMA)
                                    .addComponent(lblTenMonAn)
                                    .addComponent(lblTenMA)
                                    .addComponent(txtTenMA)
                                    .addComponent(cboLoaiMA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDonVi)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtGiaVon, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelShadow1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFirst, btnLast, btnMoi, btnNext, btnPrev, btnSua, btnThem, btnXoa});

        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenMA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenMonAn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLoaiMA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLoaiMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDonVi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaBan)
                    .addComponent(lblGiaVon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaVon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelShadow1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnMoi, btnSua, btnThem, btnXoa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        btnMiniSize.setBackground(Color.lightGray);
        btnMiniSize.setForeground(Color.white);
    }//GEN-LAST:event_btnMiniSizeMouseExited

    private void btnMiniSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiniSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMiniSizeActionPerformed

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered

        btnThem.setBackground(Color.green);

    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        btnThem.setBackground(new Color(204, 204, 204));

    }//GEN-LAST:event_btnThemMouseExited

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        btnXoa.setBackground(Color.green);

    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        btnXoa.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnXoaMouseExited

    private void btnMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseEntered

        btnMoi.setBackground(Color.green);

    }//GEN-LAST:event_btnMoiMouseEntered

    private void btnMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseExited
        btnMoi.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnMoiMouseExited

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseExited
        btnSua.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnSuaMouseExited

    private void btnSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseEntered
        btnSua.setBackground(Color.green);
    }//GEN-LAST:event_btnSuaMouseEntered

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!checkForm()) {
            return;
        }
        insert();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (!checkForm()) {
            return;
        }
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseEntered
        btnFirst.setBackground(Color.green);
    }//GEN-LAST:event_btnFirstMouseEntered

    private void btnFirstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseExited
        btnFirst.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnFirstMouseExited

    private void btnPrevMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseEntered
        btnPrev.setBackground(Color.green);
    }//GEN-LAST:event_btnPrevMouseEntered

    private void btnPrevMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseExited
        btnPrev.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnPrevMouseExited

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered
        btnNext.setBackground(Color.green);
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited
        btnNext.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnNextMouseExited

    private void btnLastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseExited
        btnLast.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnLastMouseExited

    private void btnLastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseEntered
        btnLast.setBackground(Color.green);
    }//GEN-LAST:event_btnLastMouseEntered

    private void buttonBoder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBoder1ActionPerformed
        chonAnh();
    }//GEN-LAST:event_buttonBoder1ActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
//        dispose();
    }//GEN-LAST:event_formWindowLostFocus

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private com.duan.Design.ButtonBoder buttonBoder1;
    private javax.swing.JComboBox<String> cboDonVi;
    private javax.swing.JComboBox<String> cboLoaiMA;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaVon;
    private javax.swing.JLabel lblHinhMH;
    private javax.swing.JLabel lblLoaiMA;
    private javax.swing.JLabel lblTenMA;
    private javax.swing.JLabel lblTenMonAn;
    private com.duan.Shadow.PanelShadow panelShadow1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaVon;
    private javax.swing.JTextField txtTenMA;
    // End of variables declaration//GEN-END:variables
}
