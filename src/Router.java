import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Router implements Serializable {
    private String routerName;
    private Map<String, Information> information;
    private Map<String, Integer> adjRouterMissCount;
    private Boolean routerStatus;

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

    public void setAdjRouterMissCount(String routerName, Integer i) {
        this.adjRouterMissCount.put(routerName, i);
    }

    public Map<String, Integer> getAdjRouterMissCount() {
        return adjRouterMissCount;
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

    public Boolean getRouterStatus() {
        return routerStatus;
    }

    public void setRouterStatus(Boolean routerStatus) {
        this.routerStatus = routerStatus;
    }
}
