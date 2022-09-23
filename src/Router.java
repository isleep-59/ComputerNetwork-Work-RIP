import java.io.Serializable;
import java.util.Map;

public class Router implements Serializable {
    private String routerName;
    private Map<String, Information> informationTable;   //目的网络为主键
    private Map<Router, Integer> adjRouterMissCount;    //路由器为主键
    private Boolean routerStatus;

    public Router() {

    }

    public Router(String routerName, Map<String, Information> informationTable, Boolean routerStatus) {
        this.routerName = routerName;
        this.informationTable = informationTable;
        this.routerStatus = routerStatus;
    }

    public void receiveInformation() {
        
    }

    public void update() {
        for (Router router : adjRouterMissCount.keySet()) {

        }
    }

    @Override
    public String toString() {
        return "RouterName: " + routerName + "\n" +
                "Information: " + informationTable.values() + "\n";
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

    public Map<String, Information> getInformation() {
        return informationTable;
    }

    public void setInformation(Map<String, Information> informationTable) {
        this.informationTable = informationTable;
    }

    public Boolean getRouterStatus() {
        return routerStatus;
    }

    public void setRouterStatus(Boolean routerStatus) {
        this.routerStatus = routerStatus;
    }
}
