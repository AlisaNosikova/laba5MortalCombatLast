package mortalkombatversion.resources;

import java.net.URL;

public class ResourcesManagerImpl implements ResourcesManager {
    @Override
    public URL getResource(String name) {
        URL destination = getClass().getClassLoader().getResource(name);
        if (destination == null) {
            throw new IllegalArgumentException("Cannot find resource " + name);
        }

        return destination;
    }
}
