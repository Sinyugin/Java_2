import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400; //позиция верхнего левого угла по Х
    private static final int POS_Y = 200; //позиция верхнего левого угла по У
    private static final int WINDOW_WIDTH = 800; // ширина окна
    private static final int WINDOW_HEIGHT = 600; //высота окна

    private Sprite[] sprites = new Sprite[1]; // создается массвив из спрайтев
    private int spritesCount;

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(canvas, g);
        }
    }
    void onDrawCanvas(GameCanvas c, Graphics g, float deltaTime) {
        update(c, deltaTime);
        render(c, g);
    }

    private void initApplication() {
        addSprite(new Background());
    }

    private void addSprite(Sprite s){
        if (spritesCount == sprites.length){
            Sprite[] temp = new Sprite[sprites.length * 2];
            System.arraycopy(sprites, 0, temp, 0, sprites.length);
            sprites = temp;
        }
        sprites[spritesCount++] = s;
    }

    private void removeSprite(){
        if (spritesCount > 1) spritesCount --;
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрывает окно при нажатии на Х
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles"); // заголовок окна программы
        GameCanvas canvas = new GameCanvas(this); // создаём новый объект класса GameCanvas
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) //если нажата первая кнопка мышки
                    addSprite(new Ball(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3) // если нажата третья кнопка мышки
                    removeSprite();
            }
        });

        add(canvas);
        initApplication();
        setVisible(true); // сделать видимым
    }

    public static void main(String[] args) {
        new MainCircles();
    }
}
