package com.epam.robot.ui;

import com.epam.robot.messageBus.MessageProducer;
import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.BooksQueryMessage;
import com.epam.robot.messageBus.messages.LoadedBooksMessage;
import com.epam.robot.records.Book;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame implements WindowWithGridLayout, Subscriber<LoadedBooksMessage>, MessageProducer {

    private JPanel panel;
    private GridBagConstraints constraints;

    public MainWindow() {
        super("Robot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setupLayout();
        setupMenu();
        setSize(400, 400);
        setLocationRelativeTo(null);
        subscribe(LoadedBooksMessage.class);
        send(new BooksQueryMessage());
    }
    private void setupLayout(){
        panel = setupPanel(this);
        constraints = setupConstraints();
    }
    private void setupMenu(){
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        JMenu menu = new JMenu("File");
        bar.add(menu);
        JMenuItem addURL = new JMenuItem("Add URL");
        addURL.addActionListener(this::addURLAction);
        menu.add(addURL);
    }
    private void addURLAction(ActionEvent e){
        addURLWindow window = new addURLWindow();
        window.setVisible(true);
    }

    @Override
    public void receiveMessage(LoadedBooksMessage message) {
        String[][] books = message.getBooks();
        JTable table = new JTable(books, new String[]{"Library", "Title"});
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        pack();
    }
}
