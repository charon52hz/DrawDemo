package demo4.decorator;

import demo4.utils.CanvasUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackgroundImport implements Import{

    @Override
    public void input(String path) {
        try {
            BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(path)));
            CanvasUtils.addImage(image).redraw().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
