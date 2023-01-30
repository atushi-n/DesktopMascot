package frame;

import main.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {

    private JButton createButton;
    private Manager manager;

    public UserInterface() {
        super();
        manager = new Manager();

        createButton = new JButton("create");
        createButton.addActionListener(this);
        this.add(createButton);

        this.setSize(new Dimension(200, 100));//ウィンドウサイズ
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//×を押してウィンドウが閉じると、プログラムも終了する
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        manager.createMascot();
    }
}
