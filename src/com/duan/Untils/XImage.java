package com.duan.Untils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {

    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     * @return 
     */
    public static Image getAppIcon() {
//        String file = "com\\duan\\Icon\\logo7.png";
        
        URL url = XImage.class.getResource("/com/duan/Icon/logo1.png");
        return new ImageIcon(url).getImage();
    }

    /**
     * Sao chép file hinh vào thư mục image
     *
     * @param src là đối tượng file ảnh
     */
    public static void save(File src) {
        File dst = new File("Images", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Đọc hình ảnh từ file image
     *
     * @param fileName là tên file
     * @return ảnh đọc được
     */
    public static ImageIcon read(String fileName) {
        File path = new File("Images", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(159, 142, Image.SCALE_DEFAULT));

    }
}
