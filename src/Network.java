import java.io.Serializable;
import java.util.*;

public class Network implements Serializable {
    private List<Router> routerList;

    public List<Router> getRouterList() {
        return routerList;
    }

    public void setRouterList(List<Router> routerList) {
        this.routerList = routerList;
    }

    public void initRouter() {
        this.routerList = new ArrayList<Router>();

        //Initial Router A
        Map<String, Information> informationA = new HashMap<String, Information>();
        informationA.put("Network1", new Information("Network1", 1, "null"));
        informationA.put("Network2", new Information("Network2", 1, "null"));
        informationA.put("Network3", new Information("Network3", 1, "null"));
        this.routerList.add(new Router("RouterA", informationA, true));

        //Initial Router B
        Map<String, Information> informationB = new HashMap<String, Information>();
        informationB.put("Network3", new Information("Network3", 1, "null"));
        informationB.put("Network4", new Information("Network4", 1, "null"));
        this.routerList.add(new Router("RouterB", informationB, true));

        //Initial Router C
        Map<String, Information> informationC = new HashMap<String, Information>();
        informationC.put("Network4", new Information("Network4", 1, "null"));
        informationC.put("Network5", new Information("Network5", 1, "null"));
        this.routerList.add(new Router("RouterC", informationC, true));

        //Initial Router D
        Map<String, Information> informationD = new HashMap<String, Information>();
        informationD.put("Network2", new Information("Network2", 1, "null"));
        informationD.put("Network5", new Information("Network5", 1, "null"));
        this.routerList.add(new Router("RouterD", informationD, true));

        //Initial Router E
        Map<String, Information> informationE = new HashMap<String, Information>();
        informationE.put("Network1", new Information("Network1", 1, "null"));
        informationE.put("Network6", new Information("Network6", 1, "null"));
        this.routerList.add(new Router("RouterE", informationC, true));

        //Initial Router F
        Map<String, Information> informationF = new HashMap<String, Information>();
        informationF.put("Network5", new Information("Network5", 1, "null"));
        informationF.put("Network6", new Information("Network6", 1, "null"));
        this.routerList.add(new Router("RouterF", informationF, true));
    }

    public void initAdjRouter() {
        for(int i = 0; i < routerList.size(); i++) {
        }
    }
}