package API;

import Constants.Errors;
import Constants.PresentationDesign;
import Presentation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


/**
 * XMLAccessor, reads and writes XML files
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class LoadXML implements LoadAccessor, PresentationDesign, Errors
{

    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();

    }

    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        int slideNumber, itemNumber, max = 0, maxItems = 0;
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); //Create a JDOM document
            Element doc = document.getDocumentElement();
            presentation.setTitle(getTitle(doc, PresentationDesign.SHOW_TITLE));

            NodeList slides = doc.getElementsByTagName(PresentationDesign.SLIDE);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++)
            {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(getTitle(xmlSlide, PresentationDesign.SLIDE_TITLE));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(PresentationDesign.ITEM);
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++)
                {
                    Element item = (Element) slideItems.item(itemNumber);
                    loadSlideItem(slide, item);
                }
            }
        }
        catch (IOException iox)
        {
            System.err.println(iox);
        }
        catch (SAXException sax)
        {
            System.err.println(sax.getMessage());
        }
        catch (ParserConfigurationException pcx)
        {
            System.err.println(Errors.PARSER_CONFIGURATION_ERROR);
        }
    }

    protected void loadSlideItem(Slide slide, Element item)
    {
        int level = 1;
        NamedNodeMap attributes = item.getAttributes();
        String leveltext = attributes.getNamedItem(PresentationDesign.LEVEL).getTextContent();
        if (leveltext != null)
        {
            try
            {
                level = Integer.parseInt(leveltext);
            }
            catch (NumberFormatException x)
            {
                System.err.println(Errors.NUMBER_FORMAT_ERROR);
            }
        }
        String type = attributes.getNamedItem(PresentationDesign.KIND).getTextContent();
        if (PresentationDesign.TEXT.equals(type))
        {
            slide.addSlideItem(new TextItem(level, item.getTextContent())
            {
                public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style)
                {
                    return null;
                }

                public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
                {

                }
            });
        }
        else
        {
            if (PresentationDesign.IMAGE.equals(type))
            {
                slide.addSlideItem(new BitmapItem(level, item.getTextContent()));
            }
            else
            {
                System.err.println(Errors.UNKNOWN_ELEMENT_TYPE);
            }
        }
    }

}