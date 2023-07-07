package com.duan.Form;

import com.duan.Design.ModelChart;
import com.duan.Design.RoundedPanel;
import com.duan.DAO.ThongKeDAO;
import com.duan.DAO.TongQuanDAO;
import com.duan.Model.DoanhThuService;
import com.duan.Model.MonUaThich;
import com.duan.Model.TongQuan;
import com.duan.UI.MainJFrame;
import com.duan.UI.Test;
import com.duan.Untils.Auth;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TongQuanJPanel extends javax.swing.JPanel {

    public List<DoanhThuService> listDTNgay;
    public List<DoanhThuService> listDTNam;
    public ThongKeDAO tkdao = new ThongKeDAO();
    public TongQuanDAO dao = new TongQuanDAO();
    public List<TongQuan> list;
    public DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public List<MonUaThich> top3;

    DecimalFormat dfc = new DecimalFormat("###,###,###,###.00");
    Test test;

    public TongQuanJPanel(Test t) {
        initComponents();
        this.test = t;
        calFrom.setDateFormatString("yyyy-MM-dd");
        calTo.setDateFormatString("yyyy-MM-dd");
        chart.addLegend("Doanh thu (VND)", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Thuế", new Color(186, 37, 37), new Color(241, 100, 120));
        chart.addLegend("Lãi", new Color(5, 125, 0), new Color(95, 209, 96));

        lineChart1.addLegend("Doanh thu (VND)", new Color(12, 84, 175), new Color(0, 108, 247));
        lineChart1.addLegend("Thuế", new Color(186, 37, 37), new Color(241, 100, 120));
        lineChart1.addLegend("Lãi", new Color(5, 125, 0), new Color(95, 209, 96));

        if (Auth.isManager()) {
            list = dao.getTongQuan("2021-01-01", df.format(new Date()));
            lblSKH.setText("" + list.get(0).getSoKhachHang());
            lblSHD.setText("" + list.get(0).getSoHoaDon());
            lblTBMHHD.setText("" + list.get(0).getTbMatHangHoaDon());
            lblTBDTHD.setText(dfc.format(list.get(0).getTbDoanhThuHoaDon()) + " VNĐ");
        }

    }

    void showNgay() {
        list = dao.getTongQuan(df.format(calFrom.getDate()), df.format(calTo.getDate()));
        lblSKH.setText("" + list.get(0).getSoKhachHang());
        lblSHD.setText("" + list.get(0).getSoHoaDon());
        lblTBMHHD.setText("" + list.get(0).getTbMatHangHoaDon());
        lblTBDTHD.setText(dfc.format(list.get(0).getTbDoanhThuHoaDon()) + " VNĐ");
    }

    public void findTOP() {
        top3 = tkdao.getTOP(df.format(calFrom.getDate()), df.format(calTo.getDate()));
        switch (top3.size()) {
            case 3:
                prg1.setValue(top3.get(0).getTyLe());
                lblTop1.setText(top3.get(0).getTenMH());
                prg1.start();
            case 2:
                prg2.setValue(top3.get(1).getTyLe());
                lblTop2.setText(top3.get(1).getTenMH());
                prg2.start();
            case 1:
                prg3.setValue(top3.get(2).getTyLe());
                lblTop3.setText(top3.get(2).getTenMH());
                prg3.start();
                break;
        }

    }

    public void printList() {

        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("ListDT");
            XSSFRow row = null;
            XSSFRow row2 = null;
            Cell cell = null;
            CellStyle style = workBook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);

            //Độ rộng của cột
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 5000);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));

            //Font chữ của header
            XSSFFont headerFont = workBook.createFont();
            headerFont.setColor(IndexedColors.WHITE.index);
            headerFont.setFontHeightInPoints((short) 15);
            headerFont.setBold(true);

            //thêm border vào dữ liệu
            CellStyle cellStyle1 = workBook.createCellStyle();
            cellStyle1.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            cellStyle1.setBorderBottom(BorderStyle.THIN);
            cellStyle1.setBorderRight(BorderStyle.THIN);
            cellStyle1.setBorderLeft(BorderStyle.THIN);
            cellStyle1.setBorderTop(BorderStyle.THIN);
            cellStyle1.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
            cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle1.setFont(headerFont);

            //Font chữ của Title
            XSSFFont titleFont = workBook.createFont();
            titleFont.setColor(IndexedColors.BLUE1.index);
            titleFont.setFontHeightInPoints((short) 25);
            titleFont.setBold(true);
            titleFont.setItalic(true);

            CellStyle cellTitle = workBook.createCellStyle();
            cellTitle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            cellTitle.setFont(titleFont);

            row2 = sheet.createRow(1);
            cell = row2.createCell(0, CellType.STRING);
            cell.setCellValue("BÁO CÁO THỐNG KÊ DOANH THU");
            cell.setCellStyle(cellTitle);

            row = sheet.createRow(3);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Thời gian");
            cell.setCellStyle(cellStyle1);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Doanh thu");
            cell.setCellStyle(cellStyle1);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Thuế");
            cell.setCellStyle(cellStyle1);

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Lợi nhuận");
            cell.setCellStyle(cellStyle1);

            for (int i = 0; i < listDTNgay.size(); i++) {
                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(listDTNgay.get(i).getThoiGian() + "");
                cell.setCellStyle(style);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(listDTNgay.get(i).getThuNhap());
                cell.setCellStyle(style);

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(listDTNgay.get(i).getThue());
                cell.setCellStyle(style);

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(listDTNgay.get(i).getLai());
                cell.setCellStyle(style);
            }

            File f = new File("D://baocao.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                workBook.write(fos);
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "In thành công!");
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getDTNgay() {
        listDTNgay = tkdao.getDoanhThuNgay(df.format(calFrom.getDate()), df.format(calTo.getDate()));

        if (listDTNgay != null) {
            for (DoanhThuService dt : listDTNgay) {
                chart.addData(new ModelChart(dt.getThoiGian() + "", new double[]{dt.getThuNhap(), dt.getThue(), dt.getLai()}));
            }
        }

        chart.start();
    }

    void getDTNam() {
        listDTNam = tkdao.getDoanhThuNam(df.format(calFrom.getDate()), df.format(calTo.getDate()));
        if (listDTNam != null) {
            for (DoanhThuService dt : listDTNam) {
                lineChart1.addData(new ModelChart(dt.getNam() + "", new double[]{dt.getThuNhap(), dt.getThue(), dt.getLai()}));
            }
        }

        lineChart1.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        TopPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pnlHD = new RoundedPanel(15,Color.CYAN);
        jLabel2 = new javax.swing.JLabel();
        lblSHD = new javax.swing.JLabel();
        ngang1 = new RoundedPanel(50,Color.white);
        pnlDTHD = new RoundedPanel(15,Color.PINK);
        jLabel4 = new javax.swing.JLabel();
        lblTBDTHD = new javax.swing.JLabel();
        ngang3 = new RoundedPanel(50,Color.white);
        pnlMHHD = new RoundedPanel(15,Color.MAGENTA);
        jLabel3 = new javax.swing.JLabel();
        lblTBMHHD = new javax.swing.JLabel();
        ngang2 = new RoundedPanel(50,Color.white);
        pnlKH = new RoundedPanel(15,Color.yellow);
        jLabel1 = new javax.swing.JLabel();
        lblSKH = new javax.swing.JLabel();
        ngang = new RoundedPanel(50,Color.white);
        jLabel9 = new javax.swing.JLabel();
        pnlDoanhThuHnay = new javax.swing.JPanel();
        panelRound2 = new com.duan.Design.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        calFrom = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        calTo = new com.toedter.calendar.JDateChooser();
        btnHienThi = new javax.swing.JButton();
        chart = new com.duan.Design.Chart();
        jLabel10 = new javax.swing.JLabel();
        btnInBaoCao = new com.duan.Design.ButtonBoder();
        LeftPanel = new javax.swing.JPanel();
        panelBoGoc3 = new com.duan.Design.PanelBoGoc();
        jPanel4 = new javax.swing.JPanel();
        prg1 = new com.duan.Design.Progress();
        lblTop1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        prg2 = new com.duan.Design.Progress();
        lblTop2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        prg3 = new com.duan.Design.Progress();
        lblTop3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        RightPanel = new javax.swing.JPanel();
        panelBoGoc2 = new com.duan.Design.PanelBoGoc();
        lineChart1 = new com.duan.Design.LineChart();
        jLabel5 = new javax.swing.JLabel();

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1100, 1096));

        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 928));
        jPanel1.setLayout(new java.awt.BorderLayout());

        TopPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Số hóa đơn");

        lblSHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSHD.setText("0");

        ngang1.setBackground(new java.awt.Color(255, 255, 255));
        ngang1.setOpaque(false);
        ngang1.setPreferredSize(new java.awt.Dimension(230, 5));

        javax.swing.GroupLayout ngang1Layout = new javax.swing.GroupLayout(ngang1);
        ngang1.setLayout(ngang1Layout);
        ngang1Layout.setHorizontalGroup(
            ngang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        ngang1Layout.setVerticalGroup(
            ngang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlHDLayout = new javax.swing.GroupLayout(pnlHD);
        pnlHD.setLayout(pnlHDLayout);
        pnlHDLayout.setHorizontalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ngang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHDLayout.setVerticalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ngang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TB Doanh thu/Hóa đơn");

        lblTBDTHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTBDTHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTBDTHD.setText("0");

        ngang3.setBackground(new java.awt.Color(255, 255, 255));
        ngang3.setOpaque(false);
        ngang3.setPreferredSize(new java.awt.Dimension(230, 5));

        javax.swing.GroupLayout ngang3Layout = new javax.swing.GroupLayout(ngang3);
        ngang3.setLayout(ngang3Layout);
        ngang3Layout.setHorizontalGroup(
            ngang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        ngang3Layout.setVerticalGroup(
            ngang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlDTHDLayout = new javax.swing.GroupLayout(pnlDTHD);
        pnlDTHD.setLayout(pnlDTHDLayout);
        pnlDTHDLayout.setHorizontalGroup(
            pnlDTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTBDTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ngang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDTHDLayout.setVerticalGroup(
            pnlDTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTHDLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTBDTHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ngang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TB Mặt hàng/Hóa đơn");

        lblTBMHHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTBMHHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTBMHHD.setText("0");

        ngang2.setBackground(new java.awt.Color(255, 255, 255));
        ngang2.setOpaque(false);
        ngang2.setPreferredSize(new java.awt.Dimension(230, 5));

        javax.swing.GroupLayout ngang2Layout = new javax.swing.GroupLayout(ngang2);
        ngang2.setLayout(ngang2Layout);
        ngang2Layout.setHorizontalGroup(
            ngang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        ngang2Layout.setVerticalGroup(
            ngang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMHHDLayout = new javax.swing.GroupLayout(pnlMHHD);
        pnlMHHD.setLayout(pnlMHHDLayout);
        pnlMHHDLayout.setHorizontalGroup(
            pnlMHHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTBMHHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMHHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ngang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMHHDLayout.setVerticalGroup(
            pnlMHHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMHHDLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTBMHHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ngang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlKH.setPreferredSize(new java.awt.Dimension(250, 108));
        pnlKH.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/Human.png"))); // NOI18N
        jLabel1.setText("    Số khách hàng          ");
        pnlKH.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 250, -1));

        lblSKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSKH.setText("0");
        pnlKH.add(lblSKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 57, 250, -1));

        ngang.setBackground(new java.awt.Color(255, 255, 255));
        ngang.setOpaque(false);

        javax.swing.GroupLayout ngangLayout = new javax.swing.GroupLayout(ngang);
        ngang.setLayout(ngangLayout);
        ngangLayout.setHorizontalGroup(
            ngangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        ngangLayout.setVerticalGroup(
            ngangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        pnlKH.add(ngang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, 5));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(pnlKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlMHHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMHHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("TỔNG QUAN KINH DOANH");

        pnlDoanhThuHnay.setBackground(new java.awt.Color(44, 68, 98));

        panelRound2.setRoundBottomLeft(10);
        panelRound2.setRoundBottomRight(10);
        panelRound2.setRoundTopLeft(10);
        panelRound2.setRoundTopRight(10);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("From:");

        calFrom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calFromMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                calFromMousePressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("To:");

        btnHienThi.setText("Hiển thị");
        btnHienThi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calTo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHienThi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHienThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(243, 243, 243));
        jLabel10.setText("DOANH THU THEO MỐC");

        javax.swing.GroupLayout pnlDoanhThuHnayLayout = new javax.swing.GroupLayout(pnlDoanhThuHnay);
        pnlDoanhThuHnay.setLayout(pnlDoanhThuHnayLayout);
        pnlDoanhThuHnayLayout.setHorizontalGroup(
            pnlDoanhThuHnayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHnayLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(pnlDoanhThuHnayLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoanhThuHnayLayout.setVerticalGroup(
            pnlDoanhThuHnayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuHnayLayout.createSequentialGroup()
                .addGroup(pnlDoanhThuHnayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoanhThuHnayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDoanhThuHnayLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        btnInBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/duan/Icon/icons8-microsoft-excel-48.png"))); // NOI18N
        btnInBaoCao.setText("IN BÁO CÁO");
        btnInBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addComponent(pnlDoanhThuHnay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDoanhThuHnay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(TopPanel, java.awt.BorderLayout.PAGE_START);

        LeftPanel.setBackground(new java.awt.Color(0, 102, 255));
        LeftPanel.setOpaque(false);
        LeftPanel.setPreferredSize(new java.awt.Dimension(545, 200));

        panelBoGoc3.setBackground(new java.awt.Color(44, 68, 98));

        jPanel4.setOpaque(false);

        prg1.setBackground(new java.awt.Color(66, 248, 84));
        prg1.setForeground(new java.awt.Color(255, 255, 0));
        prg1.setValue(60);
        prg1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblTop1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTop1.setForeground(new java.awt.Color(224, 224, 224));
        lblTop1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop1.setText("None");

        jPanel2.setOpaque(false);

        prg2.setBackground(new java.awt.Color(255, 102, 153));
        prg2.setForeground(new java.awt.Color(19, 153, 32));
        prg2.setValue(85);
        prg2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblTop2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTop2.setForeground(new java.awt.Color(224, 224, 224));
        lblTop2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop2.setText("None");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prg2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(lblTop2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblTop2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prg2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setOpaque(false);

        prg3.setBackground(new java.awt.Color(102, 102, 255));
        prg3.setForeground(new java.awt.Color(19, 153, 32));
        prg3.setValue(43);
        prg3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblTop3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTop3.setForeground(new java.awt.Color(224, 224, 224));
        lblTop3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop3.setText("None");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prg3, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(lblTop3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblTop3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prg3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prg1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(lblTop1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(lblTop1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prg1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(224, 224, 224));
        jLabel17.setText("TOP 3 mặt hàng được ưa chuộng");
        jLabel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        javax.swing.GroupLayout panelBoGoc3Layout = new javax.swing.GroupLayout(panelBoGoc3);
        panelBoGoc3.setLayout(panelBoGoc3Layout);
        panelBoGoc3Layout.setHorizontalGroup(
            panelBoGoc3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoGoc3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panelBoGoc3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelBoGoc3Layout.setVerticalGroup(
            panelBoGoc3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoGoc3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
            .addGroup(LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftPanelLayout.createSequentialGroup()
                    .addComponent(panelBoGoc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        LeftPanelLayout.setVerticalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
            .addGroup(LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LeftPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelBoGoc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel1.add(LeftPanel, java.awt.BorderLayout.LINE_START);

        RightPanel.setBackground(new java.awt.Color(255, 51, 51));
        RightPanel.setOpaque(false);
        RightPanel.setPreferredSize(new java.awt.Dimension(545, 200));

        panelBoGoc2.setBackground(new java.awt.Color(44, 68, 98));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DOANH THU LINE");

        javax.swing.GroupLayout panelBoGoc2Layout = new javax.swing.GroupLayout(panelBoGoc2);
        panelBoGoc2.setLayout(panelBoGoc2Layout);
        panelBoGoc2Layout.setHorizontalGroup(
            panelBoGoc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoGoc2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBoGoc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lineChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBoGoc2Layout.setVerticalGroup(
            panelBoGoc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoGoc2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout RightPanelLayout = new javax.swing.GroupLayout(RightPanel);
        RightPanel.setLayout(RightPanelLayout);
        RightPanelLayout.setHorizontalGroup(
            RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBoGoc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        RightPanelLayout.setVerticalGroup(
            RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBoGoc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(RightPanel, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền thực hiện!";
            test.click(14);
        } else {
            listDTNgay = tkdao.getDoanhThuNgay(df.format(calFrom.getDate()), df.format(calTo.getDate()));
            chart.addData(new ModelChart(listDTNgay.get(0).getThoiGian() + "", new double[]{listDTNgay.get(0).getThuNhap(), listDTNgay.get(0).getThue(), listDTNgay.get(0).getLai()}));
            printList();
        }

    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    private void calFromMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calFromMouseClicked
        // TODO add your handling code here:
        System.out.println(calFrom.getDate());
    }//GEN-LAST:event_calFromMouseClicked

    private void calFromMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calFromMousePressed

    }//GEN-LAST:event_calFromMousePressed
    MainJFrame main;
    private void btnHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiActionPerformed
        if (!Auth.isManager()) {
            main.title = "TMTP";
            main.message = "Bạn không có quyền thực hiện!";
            test.click(14);
        } else {
            findTOP();
            chart.clear();
            lineChart1.clear();
            showNgay();
            if (calFrom.getDate().compareTo(calTo.getDate()) > 0) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không thể lớn hơn ngày kết thúc!!!");
                return;
            } else {
                System.out.println(df.format(calFrom.getDate()));
                getDTNgay();
                getDTNam();
            }
        }
    }//GEN-LAST:event_btnHienThiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JButton btnHienThi;
    private com.duan.Design.ButtonBoder btnInBaoCao;
    private com.toedter.calendar.JDateChooser calFrom;
    private com.toedter.calendar.JDateChooser calTo;
    private com.duan.Design.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSHD;
    private javax.swing.JLabel lblSKH;
    private javax.swing.JLabel lblTBDTHD;
    private javax.swing.JLabel lblTBMHHD;
    private javax.swing.JLabel lblTop1;
    private javax.swing.JLabel lblTop2;
    private javax.swing.JLabel lblTop3;
    private com.duan.Design.LineChart lineChart1;
    private javax.swing.JPanel ngang;
    private javax.swing.JPanel ngang1;
    private javax.swing.JPanel ngang2;
    private javax.swing.JPanel ngang3;
    private com.duan.Design.PanelBoGoc panelBoGoc2;
    private com.duan.Design.PanelBoGoc panelBoGoc3;
    private com.duan.Design.PanelRound panelRound2;
    private javax.swing.JPanel pnlDTHD;
    private javax.swing.JPanel pnlDoanhThuHnay;
    private javax.swing.JPanel pnlHD;
    private javax.swing.JPanel pnlKH;
    private javax.swing.JPanel pnlMHHD;
    private com.duan.Design.Progress prg1;
    private com.duan.Design.Progress prg2;
    private com.duan.Design.Progress prg3;
    // End of variables declaration//GEN-END:variables
}
