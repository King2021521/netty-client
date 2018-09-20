package com.zxm.me;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author
 * @Description 登录页面
 * @Date Create in 上午 9:38 2018/9/20 0020
 */
public class LoginFrame {
    private Frame loginFrame;
    private Panel controlPanel;
    private TextField textField;

    private static NettyClientBootstrap bootstrap;

    public LoginFrame() {
        loginFrame = new Frame("netty客户端登录（Copyright© Nicholas.Tony）");
        loginFrame.setResizable(false);
        loginFrame.setLocation(700, 500);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new GridLayout(1, 1));
        loginFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        textField = new TextField(42);
        textField.setBackground(Color.WHITE);

        controlPanel = new Panel();
        controlPanel.setBackground(Color.PINK);
        controlPanel.setLayout(new GridLayout(2, 1));

        controlPanel.add(textField);
        loginFrame.add(controlPanel);
        loginFrame.setVisible(true);
    }

    public void login() {
        Button createButton = new Button("登录");
        createButton.setBackground(new Color(255, 110, 111));

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChatFrame chatFrame = new ChatFrame();
                try {
                    bootstrap = new NettyClientBootstrap(8081, "10.13.1.210", chatFrame);
                } catch (Exception ex) {
                    throw new RuntimeException("init netty client fail !!!");
                }
                String userName = textField.getText();
                bootstrap.getSocketChannel().writeAndFlush(MessageHandler.encode(MessageHandler.LOGIN, userName));
                chatFrame.showFrame();
                chatFrame.setBootstrap(bootstrap);
                loginFrame.setVisible(false);
            }
        });

        controlPanel.add(createButton);
        loginFrame.setVisible(true);
    }
}
