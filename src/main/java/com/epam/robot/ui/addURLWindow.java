package com.epam.robot.ui;

import com.epam.robot.messageBus.MessageProducer;
import com.epam.robot.messageBus.messages.AddURLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class addURLWindow extends JFrame implements WindowWithGridLayout, MessageProducer {

    private JTextField libraryName;
    private JTextField urlAddress;

    public addURLWindow() {
        super("Add URL...");
        setupWindow();
        pack();
        setLocationRelativeTo(null);
    }

    private void setupWindow() {
        JPanel panel = setupPanel(this);
        GridBagConstraints constraints = setupConstraints();
        setupTextFields(panel, constraints);
    }

    private void setupTextFields(JPanel panel, GridBagConstraints constraints) {
        JLabel libraryLabel = new JLabel("Library name");
        panel.add(libraryLabel, constraints);
        constraints.gridx++;
        JLabel addressLabel = new JLabel("URL address");
        panel.add(addressLabel, constraints);
        constraints.gridx--;
        constraints.gridy++;
        libraryName = new JTextField();
        libraryName.setPreferredSize(new Dimension(70, 20));
        panel.add(libraryName,constraints);
        constraints.gridx++;
        urlAddress = new JTextField();
        urlAddress.setPreferredSize(new Dimension(350, 20));
        panel.add(urlAddress, constraints);
        constraints.gridy++;
        constraints.gridx++;
        JButton button = new JButton("add");
        button.addActionListener(this::addURLAction);
        panel.add(button, constraints);
    }
    private void addURLAction(ActionEvent event){
        String library = libraryName.getText();
        String addressText = urlAddress.getText();
        if (!addressText.startsWith("http://")) addressText = "http://"+addressText;
        URL address = null;
        try {
            address = new URL(addressText);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        send(new AddURLMessage(library, address));
    }
}
