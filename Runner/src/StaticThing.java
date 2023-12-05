import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    private double sizeX;
    private double sizeY;
    private ImageView imageView;

    public StaticThing(double sizeX, double sizeY, String fileName) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initializeImageView(fileName);
    }

    private void initializeImageView(String fileName) {
        Image image = new Image("file:" + fileName);
        imageView = new ImageView(image);
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
        imageView.setFitWidth(sizeX);
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
        imageView.setFitHeight(sizeY);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
