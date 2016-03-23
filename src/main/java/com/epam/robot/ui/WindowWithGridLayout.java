package com.epam.robot.ui;

import javax.swing.*;
import java.awt.*;

public interface WindowWithGridLayout{
    default JPanel setupPanel(JFrame frame){
        JPanel panel = (JPanel) frame.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        return panel;
    }
    default GridBagConstraints setupConstraints(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.weightx=50;
        constraints.weighty=50;
        constraints.insets=new Insets(5,5,5,5);
        return constraints;
    }
}
