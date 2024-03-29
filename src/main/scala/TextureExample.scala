import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
 
class TextureExample {
 
	/** The texture that will hold the image details */
	var texture: Texture = null
 
 
	/**
	 * Start the example
	 */
	def start() = {
		initGL(800,600);
		init();
 
		while (true) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
 
			Display.update();
			Display.sync(100);
 
			if (Display.isCloseRequested()) {
				Display.destroy();
				System.exit(0);
			}
		}
	}
 
	/**
	 * Initialise the GL display
	 * 
	 * @param width The width of the display
	 * @param height The height of the display
	 */
	def initGL(width : Int, height: Int) = {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch { 
			case e : LWJGLException => {
				e.printStackTrace();
				System.exit(0);
			}
		}
 
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
 
        	// enable alpha blending
        	GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        	GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
 
	/**
	 * Initialise resources
	 */
	def init() = {
 
		try {
			// load texture from PNG file
			texture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("Five_Boats.jpg"))
 
			System.out.println("Texture loaded: "+texture)
			System.out.println(">> Image width: "+texture.getImageWidth())
			System.out.println(">> Image height: "+texture.getImageHeight())
			System.out.println(">> Texture width: "+texture.getTextureWidth())
			System.out.println(">> Texture height: "+texture.getTextureHeight())
			System.out.println(">> Texture ID: "+texture.getTextureID())
		} catch  {
			case e : IOException => e.printStackTrace()
		}
	}
 
	/**
	 * draw a quad with the image on it
	 */
	def render() = {
		Color.white.bind();
		texture.bind(); // or GL11.glBind(texture.getTextureID());
 
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(texture.getTextureWidth(),0);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(texture.getTextureWidth(),texture.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(0,texture.getTextureHeight());
		GL11.glEnd();
	}
}

object main {
  def main(args: Array[String]) = {
    val textureExample:TextureExample = new TextureExample();
	textureExample.start();
  }
}