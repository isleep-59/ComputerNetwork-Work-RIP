import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Router implements Serializable {
    private String routerName;
    private Map<String, Information> information;
    private List<Router> adjRouterList;
    Boolean routerStatus;

    public Router() {
    }

    public Router(String routerName, Map<String, Information> information, Boolean routerStatus) {
        this.routerName = routerName;
        this.information = information;
        this.routerStatus = routerStatus;
    }

    @Override
    public String toString() {
        return "RouterName: " + routerName + "\n" +
                "Information: " + information.values() + "\n";
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public Map<String, Information> getinformation() {
        return information;
    }

    public void setinformation(Map<String, Information> information) {
        this.information = information;
    }

    public List<Router> getAdjRouterList() {
        return adjRouterList;
    }

    public void setAdjRouterList(List<Router> adjRouterList) {
        this.adjRouterList = adjRouterList;
    }

    public Boolean getRouterStatus() {
        return routerStatus;
    }

    public void setRouterStatus(Boolean routerStatus) {
        this.routerStatus = routerStatus;
    }
}
