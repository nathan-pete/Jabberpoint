package Presentation;

import java.awt.*;

/**
 * <p>Style stands for Indent, Color, Font and Leading.</p>
 * <p>The link between a style number and a item level is hard-linked:
 * in Slide the style is grabbed for an item
 * with a style number the same as the item level.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style
{
    private static final String FONTNAME = "Helvetica";
    private static Style[] styles; // de styles
    int indent;
    Color color;
    Font font;
    int fontSize;
    int leading;

    public Style(int indent, Color color, int points, int leading)
    {
        this.indent = indent;
        this.color = color;
        extracted(points);
        this.leading = leading;
    }

    public static void createStyles()
    {
        styles = new Style[5];
        styles[0] = new Style(0, Color.red, 48, 20);    
        styles[1] = new Style(20, Color.blue, 40, 10);  
        styles[2] = new Style(50, Color.black, 36, 10);  
        styles[3] = new Style(70, Color.black, 30, 10);  
        styles[4] = new Style(90, Color.black, 24, 10);   
    }

    public static Style getStyle(int level)
    {
        if (level >= styles.length)
        {
            level = styles.length - 1;
        }
        return styles[level];
    }

    private void extracted(int points)
    {
        font = new Font(FONTNAME, Font.BOLD, fontSize = points);
    }

    public String toString()
    {
        return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
    }

    public Font getFont(float scale)
    {
        return font.deriveFont(fontSize * scale);
    }
}
