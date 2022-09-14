import java.io.Serializable;
import java.util.Map;

public class Router implements Serializable {
    private String routerName;
    private Map<String, Information> information;   //目的网络为主键
    private Map<Router, Integer> adjRouterMissCount;    //路由器为主键
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

    public void setAdjRouterMissCount(Router router, Integer i) {
        this.adjRouterMissCount.put(router, i);
    }

    public Map<Router, Integer> getAdjRouterMissCount() {
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
