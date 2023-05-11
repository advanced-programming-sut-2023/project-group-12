package model.Property;

import java.util.HashMap;

public class Resources extends Property {
    private ResourceType resourceType;

    public Resources(ResourceType resourceType ,int value) {
        super(value);
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }
}
