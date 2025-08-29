package org.javase.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.util.Objects;

public class LandingPage extends JFrame {
    public LandingPage() {
        setTitle("LandingPage");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);

        URL icon = Objects.requireNonNull(LandingPage.class.getResource("/java.png"));
        setIconImage(new ImageIcon(icon).getImage());

        JPanel titleBar = new JPanel();
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        titleBar.setBackground(new Color(0, 91, 194, 255));

        JButton minimizeButton = new JButton("-");
        JButton maximizeButton = new JButton("⬜");
        JButton closeButton = new JButton("X");

        minimizeButton.addActionListener(_ -> setState(Frame.ICONIFIED));
        maximizeButton.addActionListener(_ -> {
            if (getExtendedState() == Frame.MAXIMIZED_BOTH) {
                setExtendedState(Frame.NORMAL);
            } else {
                setExtendedState(Frame.MAXIMIZED_BOTH);
            }
        });
        closeButton.addActionListener(e -> dispose());

        titleBar.add(minimizeButton);
        titleBar.add(maximizeButton);
        titleBar.add(closeButton);

        final Point[] dragPoint = {null};
        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dragPoint[0] = e.getPoint();
            }
        });

        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point current = e.getLocationOnScreen();
                setLocation(current.x - dragPoint[0].x, current.y - dragPoint[0].y);
            }
        });

        setOpacity(0.6f);

        setLayout(new BorderLayout());
        add(titleBar, BorderLayout.NORTH);

        // Add your main content here
        JPanel content = new JPanel();
        content.add(new JLabel("Hello World"));
        add(content, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ignore) {
        }

        var page = new LandingPage();
        page.setVisible(true);
    }
}
