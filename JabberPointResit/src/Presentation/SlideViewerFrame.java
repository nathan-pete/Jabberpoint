package Presentation;

import Constants.FileInfo;
import Constants.SlideDesign;
import Controller.KeyController;
import Controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>The applicatiewindow for a slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerFrame extends JFrame implements SlideDesign, FileInfo
{
    private final SlideViewerComponent slideViewerComponent;

    public SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);
        this.slideViewerComponent = new SlideViewerComponent(presentation, this);
        setupWindow(slideViewerComponent, slideViewerComponent.getPresentation());
    }

    public SlideViewerComponent getSlideViewerComponent()
    {
        return this.slideViewerComponent;
    }

    public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation)
    {
        setTitle(FileInfo.TITLE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        getContentPane().add(slideViewerComponent);
        addKeyListener(new KeyController(slideViewerComponent)); 
        setMenuBar(new MenuController(this, slideViewerComponent));    
        setSize(new Dimension(SlideDesign.WIDTH, SlideDesign.HEIGHT)); 
        setVisible(true);
    }
}