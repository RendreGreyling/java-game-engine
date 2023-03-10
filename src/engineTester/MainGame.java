package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import textures.ModelTexture;

public class MainGame {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();


        RawModel model = OBJLoader.loadObjModel("dragon", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);


        Entity entity = new Entity(staticModel, new Vector3f(0,0,-25),0,0,0,1);
        Light light = new Light(new Vector3f(0, 0, -50), new Vector3f(1,1,1));

        Camera camera = new Camera();

        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()) {
            entity.increaseRotation(0, 1, 0);
            camera.move();

            renderer.processEntity(entity);

            renderer.render(light, camera);
            DisplayManager.updateDisplay();

        }
        renderer.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();

    }

}
