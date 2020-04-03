package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JLabel sizeLabel;
    JSpinner sidesField;
    JSpinner sizeField;
    String[] Colors = {"Black", "Random"};
    String[] Figures = {"Regular Polygon","Square", "Rectangle", "Circle", "Triangle", "Graph"};
    String[] Graph = {"Node", "Edge"};
    JComboBox colorCombo;
    JComboBox figuresCombo;
    JComboBox graphCombo;
    String selectedFigure = "Regular Polygon";


    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout(0, 10, 20));
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); // default number of sides
        sizeLabel = new JLabel("Size: ");
        sizeField = new JSpinner(new SpinnerNumberModel(0, 0, 50, 2));
        sizeField.setValue(20);
        colorCombo = new JComboBox(Colors);
        figuresCombo = new JComboBox(Figures);
        graphCombo = new JComboBox(Graph);
        graphCombo.setVisible(false);
        add(figuresCombo);
        add(sizeLabel);
        add(sizeField);
        add(sidesLabel);
        add(sidesField);
        add(colorCombo);
        add(graphCombo);

        figuresCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Object o = comboBox.getSelectedItem();
                selectedFigure = o.toString();
                System.out.println(selectedFigure);
                switch (selectedFigure) {
                    case "Square":
                    case "Rectangle":
                        sidesField.setValue(4);
                        break;
                    case "Triangle":
                        sidesField.setValue(3);
                        break;
                    case "Circle":
                        sidesField.setValue(0);
                        break;
                }
            }
        });

        graphCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Object o = comboBox.getSelectedItem();
                selectedFigure = o.toString();
                System.out.println(selectedFigure);
                switch (selectedFigure) {
                    case "Node":
                        sidesField.setValue(4);
                        break;
                    case "Line":
                        sidesField.setValue(3);
                }
            }
        });
    }

}
