package sample;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.*;

public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        //setLayout(new GridLayout(1,4));
        add(canvas, CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(configPanel, BorderLayout.NORTH);
        pack();
    }

    public ConfigPanel getConfigPanel(){
        return this.configPanel;
    }
    public DrawingPanel getDrawingPanel() {return this.canvas;}
    public ControlPanel getControlPanel() {return this.controlPanel;}
}

