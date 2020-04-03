package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton deleteBtn = new JButton("Undo");
    BufferedImage img;
    JFileChooser fileChooser = new JFileChooser();

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(deleteBtn);
        add(exitBtn);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        deleteBtn.addActionListener(this::delete);
    }

    private void save(ActionEvent event) {
        fileChooser.showSaveDialog(this.frame.getDrawingPanel());
        File file = fileChooser.getSelectedFile();
        try {
            ImageIO.write(frame.canvas.image, "PNG", file);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    private void load(ActionEvent event) {
        fileChooser.showOpenDialog(this.frame.getDrawingPanel());
        img = new BufferedImage(DrawingPanel.W, DrawingPanel.H, BufferedImage.TYPE_INT_ARGB);
        try {
            File file = fileChooser.getSelectedFile();
            img = ImageIO.read(file);
            frame.canvas.image = img;
            frame.getDrawingPanel().graphics = img.createGraphics();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent event){
        this.frame.getDrawingPanel().createOffscreenImage();
        this.frame.getDrawingPanel().repaint();
    }

    private void exit(ActionEvent event){
        System.exit(0);
    }

    private void delete(ActionEvent event){
       frame.canvas.deleteShape();
    }

}
