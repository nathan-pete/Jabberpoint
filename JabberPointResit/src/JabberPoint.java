import API.LoadAccessor;
import API.LoadXML;
import Constants.Errors;
import Constants.FileInfo;
import Presentation.Presentation;
import Presentation.SlideViewerFrame;
import Presentation.Style;

import javax.swing.*;
import java.io.IOException;

/**
 * JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint implements FileInfo, Errors
{

    public static void main(String[] argv)
    {
        Style.createStyles();
        Presentation presentation = new Presentation();
        SlideViewerFrame slideViewerFrame = new SlideViewerFrame(FileInfo.VERSION, presentation);
        try
        {
            if (argv.length == 0)
            {
                LoadAccessor.getDemoAccessor().loadFile(presentation, "");
            }
            else
            {
                new LoadXML().loadFile(presentation, argv[0]);
            }
            slideViewerFrame.getSlideViewerComponent().setSlideNumber(0);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, Errors.IO_EXCEPTION + ex, Errors.JABBERPOINT_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}