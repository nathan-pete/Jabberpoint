package API;

import Presentation.Presentation;

import java.io.IOException;

public interface SaveAccessor
{
    void saveFile(Presentation p, String fn) throws IOException;
}