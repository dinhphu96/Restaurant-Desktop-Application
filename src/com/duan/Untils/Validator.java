/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.Untils;

import com.duan.Messege.MessageInforJDialog2;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Validator {

    public static void message(String tb) {
        String title = "TMTP";
        String message = tb;
        MessageInforJDialog2 obj = new MessageInforJDialog2(new javax.swing.JFrame(), true);
        obj.showMessage(title, message);
    }

    public static boolean isEmpty(JTextField jTextField, String thongbao) {
        if (jTextField.getText().trim().isEmpty()) {
            message(thongbao);
            jTextField.setBackground(Color.YELLOW);
            jTextField.grabFocus();
            return true;
        }
        jTextField.setBackground(Color.white);
        return false;
    }

    public static boolean isEmpty(JTextArea textArea, String thongbao) {
        if (textArea.getText().trim().isEmpty()) {
            message(thongbao);
            textArea.setBackground(Color.YELLOW);
            textArea.grabFocus();
            return true;
        }
        textArea.setBackground(Color.white);
        return false;
    }

    public static boolean isMa(JTextField jTextField, String thongbao) {
        if (jTextField.getText().matches("\\w{2}\\d{3}") == false) {
            message(thongbao);
            jTextField.setBackground(Color.YELLOW);
            jTextField.grabFocus();
            return true;
        }
        jTextField.setBackground(Color.white);
        return false;
    }

    public static boolean isEmail(JTextField txt, String thongbao) {
        if (txt.getText().matches("\\w+@gmail.com+") == false) {
            message(thongbao);
            txt.setBackground(Color.YELLOW);
            txt.grabFocus();
            return true;
        }
        txt.setBackground(Color.white);
        return false;
    }

    public static boolean checkSDT(JTextField jTextField, String thongbao) {

        if (jTextField.getText().matches("0[0-9]{9}") == false) {
            message(thongbao);
            jTextField.setBackground(Color.YELLOW);
            jTextField.grabFocus();
            return true;
        } else {
            jTextField.setBackground(Color.white);
            return false;
        }

    }

    public static boolean isNumber(JTextField jTextField, String thongbao) {
        try {
            int gia = Integer.parseInt(jTextField.getText());

        } catch (Exception e) {
            message(thongbao);
            jTextField.setBackground(Color.YELLOW);
            jTextField.grabFocus();
            return true;
        }
        jTextField.setBackground(Color.white);
        return false;
    }

}
