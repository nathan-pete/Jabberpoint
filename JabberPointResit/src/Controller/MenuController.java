package Controller;

import API.LoadAccessor;
import API.LoadXML;
import API.SaveAccessor;
import API.SaveXML;
import Constants.About;
import Constants.Errors;
import Constants.FileInfo;
import Constants.MenuOptions;
import Presentation.SlideViewerComponent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class MenuController extends MenuBar implements MenuOptions, Errors, FileInfo
{

    private final SlideViewerComponent slideViewerComponent;
    private final Frame parent;
    private MenuItem menuItem;
    private final Menu fileMenu = new Menu(MenuOptions.FILE);
    private final Menu viewMenu = new Menu(MenuOptions.VIEW);
    private final Menu helpMenu = new Menu(MenuOptions.HELP);

    public MenuController(Frame frame, SlideViewerComponent slideViewerComponent)
    {
        parent = frame;
        this.slideViewerComponent = slideViewerComponent;
        nextSlide();
        previousSlide();
        moveToSlide();
        about();
        loadPresentation();
        add(fileMenu);
        newPresentation();
        savePresentation();
        exitPresentation();
        setHelpMenu(helpMenu);
    }

    public void nextSlide()
    {
        viewMenu.add(menuItem = mkMenuItem(MenuOptions.NEXT));
        menuItem.addActionListener(actionEvent -> slideViewerComponent.nextSlide());
    }

    public void previousSlide()
    {
        viewMenu.add(menuItem = mkMenuItem(MenuOptions.PREV));
        menuItem.addActionListener(actionEvent -> slideViewerComponent.prevSlide());
    }

    public void moveToSlide()
    {
        viewMenu.add(menuItem = mkMenuItem(MenuOptions.GOTO));
        menuItem.addActionListener(actionEvent ->
        {
            String pageNumberStr = JOptionPane.showInputDialog(MenuOptions.PAGE_NUMBER);
            int pageNumber = Integer.parseInt(pageNumberStr);
            if (pageNumber < slideViewerComponent.getPresentation().getSize() + 1)
            {
                slideViewerComponent.setSlideNumber(pageNumber - 1);
            }
        });
    }

    public void about()
    {
        add(viewMenu);
        helpMenu.add(menuItem = mkMenuItem(MenuOptions.ABOUT));
        menuItem.addActionListener(actionEvent -> About.show(parent));
    }

    public void newPresentation()
    {
        fileMenu.add(menuItem = mkMenuItem(MenuOptions.NEW));
        menuItem.addActionListener(actionEvent ->
        {
            slideViewerComponent.clear();
            parent.repaint();
        });
    }

    public void savePresentation()
    {
        fileMenu.add(menuItem = mkMenuItem(MenuOptions.SAVE));
        menuItem.addActionListener(actionEvent ->
        {
            SaveAccessor xmlSaver = new SaveXML();
            try
            {
                xmlSaver.saveFile(slideViewerComponent.getPresentation(), FileInfo.SAVE_FILE);
            }
            catch (IOException exc)
            {
                JOptionPane.showMessageDialog(parent, Errors.IO_EXCEPTION + exc,
                    Errors.SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void loadPresentation()
    {
        fileMenu.add(menuItem = mkMenuItem(MenuOptions.OPEN));
        menuItem.addActionListener(actionEvent ->
        {
            LoadAccessor xmlLoader = new LoadXML();
            try
            {
                xmlLoader.loadFile(slideViewerComponent.getPresentation(), FileInfo.TEST_FILE);
                slideViewerComponent.setSlideNumber(0);
            }
            catch (IOException exc)
            {
                JOptionPane.showMessageDialog(parent, Errors.IO_EXCEPTION + exc,
                    Errors.LOAD_ERROR, JOptionPane.ERROR_MESSAGE);
            }
            parent.repaint();
        });
    }

    public void exitPresentation()
    {
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(MenuOptions.EXIT));
        menuItem.addActionListener(actionEvent -> slideViewerComponent.getPresentation().exit(0));
    }

    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}