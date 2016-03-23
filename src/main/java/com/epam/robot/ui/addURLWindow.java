package com.epam.robot.ui;

import javax.swing.*;
import java.awt.*;

public class addURLWindow extends JFrame implements WindowWithGridLayout {
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
        JTextField libraryName = new JTextField();
        libraryLabel.setPreferredSize(new Dimension(150, 20));
        panel.add(libraryName,constraints);
        constraints.gridx++;
        constraints.gridwidth=3;
        JTextField urlAddress = new JTextField();
        urlAddress.setPreferredSize(new Dimension(350, 20));
        panel.add(urlAddress, constraints);
        constraints.gridwidth=1;
    }
}
