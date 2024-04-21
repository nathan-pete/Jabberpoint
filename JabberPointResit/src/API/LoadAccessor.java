package API;

import Presentation.Presentation;

import java.io.IOException;

public interface LoadAccessor
{

    static LoadAccessor getDemoAccessor()
    {
        return new DemoPresentation();
    }

    void loadFile(Presentation p, String fn) throws IOException;
}