package API;

import Presentation.BitmapItem;
import Presentation.Presentation;
import Presentation.Slide;

/**
 * A built-in demo presentation
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation implements LoadAccessor
{

    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo Presentation");
        Slide slide1 = new Slide();
        slide1.setTitle("JabberPoint");
        slide1.addTextItem(1, "The Java prestentation tool");
        slide1.addTextItem(2, "Copyright (c) 1996-2000: Ian Darwin");
        slide1.addTextItem(2, "Copyright (c) 2000-now:");
        slide1.addTextItem(2, "Gert Florijn and Sylvia Stuurman");
        slide1.addTextItem(4, "Calling Jabberpoint without a filename");
        slide1.addTextItem(4, "will show this presentation");
        slide1.addTextItem(1, "Navigate:");
        slide1.addTextItem(3, "Next slide: PgDn or Enter");
        slide1.addTextItem(3, "Previous slide: PgUp or up-arrow");
        slide1.addTextItem(3, "Quit: q or Q");
        presentation.append(slide1);

        Slide slide2 = new Slide();
        slide2.setTitle("Demonstration of levels and styles");
        slide2.addTextItem(1, "Level 1");
        slide2.addTextItem(2, "Level 2");
        slide2.addTextItem(1, "Again level 1");
        slide2.addTextItem(1, "Level 1 has style number 1");
        slide2.addTextItem(2, "Level 2 has style number 2");
        slide2.addTextItem(3, "This is how level 3 looks like");
        slide2.addTextItem(4, "And this is level 4");
        presentation.append(slide2);

        Slide slide3 = new Slide();
        slide3.setTitle("The third slide");
        slide3.addTextItem(1, "To open a new presentation,");
        slide3.addTextItem(2, "use File->Open from the menu.");
        slide3.addTextItem(1, " ");
        slide3.addTextItem(1, "This is the end of the presentation.");
        slide3.addSlideItem(new BitmapItem(1, "JabberPoint.jpg"));
        presentation.append(slide3);
    }

    public void saveFile(Presentation presentation)
    {
        throw new IllegalStateException("Save As->Demo! called");
    }

}