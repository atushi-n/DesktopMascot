package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame {

    public Mascot m;
    private Point mouseDownCompCoords;
    private boolean flg = true;

    //コンストラクタ
    public Window() {
        this.mouseDownCompCoords = null;
        createWindow();
    }

    private void createWindow() {
        //マスコット生成
        createMascot(this);
        //ウィンドウ（自分）の設定

        this.setListener();//ウィンドウのマウス操作の反応を設定
        this.setSize(m.getWidth(), m.getHeight());//ウィンドウサイズ
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);//フレームの装飾をしない
        this.setBackground(new Color(0, 0, 0, 0));//背景色
        this.setFocusableWindowState(false);//最背面に表示
        this.getContentPane().setLayout(null);
        //ウィンドウのコンパネのレイアウトマネージャを指定なしにすることでpx単位でコンポーネントが置ける
        this.setVisible(true);
        this.toBack();//ウィンドウを背面にする(最背面に表示する処理とは別に必要)
        this.getContentPane().add(m);
        m.setLocation(0, 0);
        this.repaint();

    }

    public boolean getFlg(){
        return this.flg;
    }


    private void createMascot(Window window) {
        m = new Mascot(this);

        m.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("クリックされました");
                if (e.getClickCount() == 2) {
                    //匿名クラスのせいできもい書き方になってる
                    Window.this.dispatchEvent(new WindowEvent(Window.this, WindowEvent.WINDOW_CLOSING));//ウィンドウにウィンドウクローズイベントを投げる
                    flg = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        m.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                Window.this.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });


    }

    private void setListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("呼ばれました");
                Point currCoords = e.getLocationOnScreen();
                Window.this.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }

}

