import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {
    private Camera camera;
    private StaticThing score;
    private StaticThing lives;

    private int remainingLives;
    private Hero character;
    private long lastUpdateTime = 0;
    private List<ImageView> backgroundImages = new ArrayList<>();
    private int imageCount = 50;
    private int timerCounter = 0;
    private int addImageInterval = 60;

    public GameScene(Group root, Camera camera) {
        super(root, 800, 600);
        this.camera = camera;
        this.remainingLives = 3;

        initializeBackgroundImages();
        initializeLives();
        initializeHero(root);

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                if (lastUpdateTime == 0) {
                    lastUpdateTime = time;
                    return;
                }
                updateGameElements();
            }
        };

        setOnMouseClicked(event -> character.jumpAndDown());
        timer.start();
    }

    private void initializeBackgroundImages() {
        Image background = new Image("C:\\Users\\PC\\Pictures\\desert.png");
        for (int i = 0; i < imageCount; i++) {
            ImageView imageView = new ImageView(background);
            imageView.setLayoutX(background.getWidth() * i);
            imageView.setFitWidth(800);
            imageView.setFitHeight(600);

            backgroundImages.add(imageView);
            ((Group) this.getRoot()).getChildren().add(imageView);
        }
    }

    private void initializeLives() {
        score = new StaticThing(20, 20, "C:\\Users\\PC\\Pictures\\score.png");
        score.getImageView().setTranslateX(0);
        lives = new StaticThing(20, 20, "C:\\Users\\PC\\Pictures\\score.jpg");
        lives.getImageView().setTranslateX(remainingLives * 30);

        ((Group) this.getRoot()).getChildren().addAll(score.getImageView(), lives.getImageView());

        for (int i = 0; i < remainingLives; i++) {
            StaticThing lifeheart = new StaticThing(20, 20, "C:\\Users\\PC\\Pictures\\heart.jpg");
            lifeheart.getImageView().setTranslateX(240 + i * 30);
            lifeheart.getImageView().setTranslateY(10);
            ((Group) this.getRoot()).getChildren().add(lifeheart.getImageView());
        }
    }

    private void initializeHero(Group root) {
        character = new Hero(115, 415, "C:\\Users\\PC\\Pictures\\heros.png", AnimatedThing.getSit());
        root.getChildren().add(character.getSprite());
    }

    private void updateGameElements() {
        character.update();
        updateBackgroundImages();
        if (timerCounter >= addImageInterval) {
            addNewImage();
        } else {
            timerCounter++;
        }
    }

    private void updateBackgroundImages() {
        for (ImageView imageView : backgroundImages) {
            imageView.setLayoutX(imageView.getLayoutX() - 10);
        }
    }

    private void addNewImage() {
        Image mapImage = new Image("C:\\Users\\PC\\Pictures\\desert.png");
        ImageView newImage = new ImageView(mapImage);
        newImage.setLayoutX(mapImage.getWidth() * imageCount);
        backgroundImages.add(newImage);
        ((Group) this.getRoot()).getChildren().add(newImage);
        imageCount++;
        timerCounter = 0;
    }

    public static void update(Camera camera) {
        camera.setX(camera.getX() + 1);
    }

    public Camera getGameCamera() {
        return camera;
    }

    public void render() {
        double cameraX = camera.getX();
        double width = score.getImageView().getFitWidth();
        double lbX = -cameraX % width;
        double rbX = width + lbX;
        score.getImageView().setX(lbX);
        score.getImageView().setY(0);
        lives.getImageView().setX(rbX);
        lives.getImageView().setY(0);
    }
}
