package com.zxm.me;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author
 * @Description
 * @Date Create in 上午 11:12 2018/9/19 0019
 */
public class ChatFrame {
    private Frame mainFrame;
    private TextArea textArea;
    private Panel controlPanel;
    private TextField textField;

    private NettyClientBootstrap bootstrap;

    public ChatFrame() {
        prepareGUI();
        send();
    }

    private void prepareGUI() {
        mainFrame = new Frame("netty客户端工具（Copyright© Nicholas.Tony）");
        mainFrame.setResizable(false);
        mainFrame.setLocation(700, 500);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        textArea = new TextArea(40, 30);
        textArea.setBackground(new Color(255, 250, 205));

        textField = new TextField(42);
        textField.setBackground(Color.WHITE);

        controlPanel = new Panel();
        controlPanel.setBackground(Color.PINK);
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        mainFrame.add(textArea);
        controlPanel.add(textField);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(false);
    }

    public void showFrame() {
        this.mainFrame.setVisible(true);
    }

    public void setBootstrap(NettyClientBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void print(String content) {
        this.textArea.append(content + "\r\n");
        mainFrame.setVisible(true);
    }

    public void send() {
        Button createButton = new Button("发送");
        createButton.setBackground(new Color(255, 110, 111));

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                bootstrap.getSocketChannel().writeAndFlush(MessageHandler.encode(MessageHandler.MESSAGE, message));
                textField.setText("");
            }
        });

        controlPanel.add(createButton);
        mainFrame.setVisible(true);
    }
}
