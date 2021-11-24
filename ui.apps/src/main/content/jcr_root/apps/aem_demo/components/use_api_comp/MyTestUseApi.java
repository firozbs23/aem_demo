package apps.aem_demo.components.use_api_comp;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.Resource;

public class MyTestUseApi extends WCMUsePojo {

    private String firstName;
    private String lastName;
    private String fullName;
    private boolean isEnable;

    @Override
    public void activate() throws Exception {
        Resource resource = getResource();
        if (resource != null) {
            setEnable();
            setFirstName();
            setLastName();
            setFullName();
        }
    }

    private String getFirstName() {
        return firstName;
    }

    private void setFirstName() {
        this.firstName = getProperties().get("firstName", "");
    }

    private String getLastName() {
        return lastName;
    }

    private void setLastName() {
        this.lastName = getProperties().get("lastName", "");
    }

    public boolean isEnable() {
        return isEnable;
    }

    private void setEnable() {
        isEnable = true;
    }

    public String getFullName() {
        if (fullName.trim().isEmpty()) {
            return "Name not set";
        }
        return fullName;
    }

    private void setFullName() {
        fullName = firstName + " " + lastName;
    }
}
