package frame;


import javax.swing.*;
import java.awt.*;


public class Mascot extends JPanel {


    private static final String IMAGE_NAME = "maid.png";    //maid.pngに戻す
    private static final int COLS = 3;//列
    private static final int ROWS = 4;//行

    JLabel jLabel;
    int showImageX = 0;
    int showImageY = 0;

    final int mascotWidth;
    final int mascotHeight;
    int[] x;
    int[] y;

    private int moveX;
    private int moveY;

    private Window window;


    private int windowWidth;
    private int windowHeight;

    //コンストラクタ
    public Mascot(Window window) {
        this.window = window;
        //ディスプレイサイズ取得
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle desktopBounds = env.getMaximumWindowBounds();
        windowWidth = desktopBounds.width;
        windowHeight = desktopBounds.height;
        //画像読み込み
        ClassLoader cl = this.getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(cl.getResource(IMAGE_NAME));


        //画像の横と縦を取得
        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();


        //一体分の幅と高さを取得
        mascotWidth = imageWidth / COLS;
        mascotHeight = imageHeight / ROWS;


        jLabel = new JLabel(icon);
        jLabel.setSize(imageWidth, imageHeight);

        this.setSize(mascotWidth, mascotHeight);
        this.add(jLabel);

        this.setBackground(new Color(0, 0, 0, 0));//JPanelの背景を透明に


        x = new int[COLS];
        y = new int[ROWS];


        x[0] = 0;
        x[1] = mascotWidth * -2;
        x[2] = mascotWidth * -1;


        for (int i = 0; i < ROWS; i++) {
            y[i] = mascotHeight * -i;
        }

    }


    public String checkCollision(String direction) {


        showImageY = direction.equals("前") ? 0 : showImageY;
        showImageY = direction.equals("左") ? 1 : showImageY;
        showImageY = direction.equals("右") ? 2 : showImageY;
        showImageY = direction.equals("後") ? 3 : showImageY;
        // 前=0
        // 左=1
        // 右=2
        // 後=3

        //範囲外であれば反対の向きに変更

        System.out.println(windowHeight + ":" + (window.getLocation().y + mascotHeight));
        if (windowHeight < (window.getLocation().y + mascotHeight)) {
            System.out.println("下に行きすぎ");
            return "後";
        }

        if (window.getLocation().y < 0) {
            System.out.println("上に行きすぎ");
            return "前";
        }

        if (window.getLocation().x < 0) {
            System.out.println("左に行きすぎ");
            return "右";
        }

        if (windowWidth < (window.getLocation().x + mascotWidth)) {
            System.out.println("右に行きすぎ");
            return "左";
        }

        return direction;

    }

    //directio=向き num=歩く回数
    public void walk(String direction, int num) {

        for (int i = 0; i < num; i++) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            direction = checkCollision(direction);

            showImageY = direction.equals("前") ? 0 : showImageY;
            showImageY = direction.equals("左") ? 1 : showImageY;
            showImageY = direction.equals("右") ? 2 : showImageY;
            showImageY = direction.equals("後") ? 3 : showImageY;


            showImageX = showImageX == 1 ? 0 : 1;//1なら0に、1でなければ1に
            System.out.println(x[showImageX]);
            jLabel.setLocation(x[showImageX], y[showImageY]);//画像が切り替わる(これも位置が切り替わっているだけ)

            direction(direction);

            window.setLocation(window.getX() + moveX, window.getY() + moveY);//位置が切り替わる
            System.out.println(this.getLocation());
        }
        stop(direction);
    }

    private void direction(String direction) {
        switch (direction) {
            case "前":
                showImageY = 0;
                moveX = 0;
                moveY = 8;
                break;

            case "左":
                showImageY = 1;
                moveX = -8;
                moveY = 0;
                break;

            case "右":
                showImageY = 2;
                moveX = 8;
                moveY = 0;
                break;

            case "後":
                showImageY = 3;
                moveX = 0;
                moveY = -8;
                break;

        }
    }

    private void stop(String direction) {
        showImageY = direction.equals("前") ? 0 : showImageY;
        showImageY = direction.equals("左") ? 1 : showImageY;
        showImageY = direction.equals("右") ? 2 : showImageY;
        showImageY = direction.equals("後") ? 3 : showImageY;
        jLabel.setLocation(x[2], y[showImageY]);

    }


}