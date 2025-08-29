package org.javase;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseCaptureDemo extends JFrame implements MouseMotionListener {
    public JLabel mouseHoverStatus;

    public static void main(String[] args) {
        new MouseCaptureDemo();
    }

    MouseCaptureDemo() {
        setSize(500, 500);
        setTitle("Frame displaying Coordinates of Mouse Motion");

        mouseHoverStatus = new JLabel("No Mouse Hover Detected.", JLabel.CENTER);
        add(mouseHoverStatus);
        addMouseMotionListener(this);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void mouseMoved(MouseEvent e) {
        mouseHoverStatus.setText("Mouse Cursor Coordinates => X:" + e.getX() + " | Y:" + e.getY());
    }

    public void mouseDragged(MouseEvent e) {
    }
}
