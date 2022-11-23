package org.example.ui;

import lombok.Getter;
import org.example.GlobalConfig;
import org.example.util.OkHttpUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author mtasflash Created on 2022-09-27 21:54
 */
public class LoginFrame extends JFrame {

    /**
     * eyu debug服务地址
     */
    @Getter
    private final JTextField serverUrlField;
    /**
     * eyu debug服务端口
     */
    @Getter
    private final JTextField serverPortField;
    /**
     * 确定按钮
     */
    @Getter
    private final JButton submitButton;

    public LoginFrame(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(800, 600));

        serverUrlField = new JTextField();
        serverPortField = new JTextField();
        serverUrlField.setText("172.16.2.101");
        serverPortField.setText("19939");
        submitButton = new JButton("确定");
        final JLabel serverUrlLabel = new JLabel("debug服务地址:");
        final JLabel serverPortLabel = new JLabel("debug服务端口:");
        serverUrlLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        serverPortLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        submitButton.addActionListener(e -> {
            String url = serverUrlField.getText();
            int port = Integer.parseInt(serverPortField.getText());
            GlobalConfig.getInstance().buildUrl(url, port);
            String requestDebugApi = GlobalConfig.getInstance().getBaseUrl() + "?_api=GetDebugApi.all";
            String result = null;
            try {
                result = OkHttpUtil.getByRest(requestDebugApi);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(result);
            new MainFrame("功能列表", result);
        });
        //添加panel
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(0, 2));
        panel.add(serverUrlLabel);
        panel.add(serverUrlField);
        panel.add(serverPortLabel);
        panel.add(serverPortField);
        panel.add(submitButton);
    }
}
