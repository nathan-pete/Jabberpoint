package Controller;

import Presentation.SlideViewerComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * <p>This is the JabberPoint.Navigation.KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class KeyController extends KeyAdapter
{
    private final SlideViewerComponent slideViewerComponent;

    public KeyController(SlideViewerComponent slideViewerComponent)
    {
        this.slideViewerComponent = slideViewerComponent;
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER, '+' -> slideViewerComponent.nextSlide();
            case KeyEvent.VK_PAGE_UP, KeyEvent.VK_UP, '-' -> slideViewerComponent.prevSlide();
            case 'q', 'Q' -> System.exit(0);
            default ->
            {
            }
        }
    }
}