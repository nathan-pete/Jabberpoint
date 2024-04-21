package Presentation;

import Constants.SlideDesign;

import java.awt.*;
import java.util.Vector;

/**
 * <p>A slide. This class has drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide implements SlideDesign
{
    protected String title;
    protected Vector<SlideItem> items;

    public Slide()
    {
        items = new Vector<>();
    }

    public void addSlideItem(SlideItem anItem)
    {
        items.addElement(anItem);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    public void addTextItem(int level, String message)
    {
        addSlideItem(new TextItem(level, message));
    }

    public Vector<SlideItem> getSlideItems()
    {
        return items;
    }

    public int getSize()
    {
        return items.size();
    }

    public void draw(Graphics g, Rectangle area, SlideViewerComponent slide)
    {
        float scale = getScale(area);
        int y = area.y;
        
        SlideItem slideItem = new TextItem(0, getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style);
        y += slideItem.getBoundingBox(g, scale, style).height;
        for (int number = 0; number < getSize(); number++)
        {
            slideItem = getSlideItems().elementAt(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style);
            y += slideItem.getBoundingBox(g, scale, style).height;
        }
    }

    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) SlideDesign.WIDTH), ((float) area.height) / ((float) SlideDesign.HEIGHT));
    }
}