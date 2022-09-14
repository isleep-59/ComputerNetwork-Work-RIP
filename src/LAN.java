import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LAN {
    private Integer updateTime;
    private Map<String, Router> routerMap;
    private Map<String, Network> networkMap;

    public LAN() {
        initRouter();
        initNetwork();
    }

    public Map<String, Router> getRouterMap() {
        return routerMap;
    }

    public void setRouterMap(Map<String, Router> routerMap) {
        this.routerMap = routerMap;
    }

    public void initRouter() {
        this.routerMap = new HashMap<String, Router>();

        //Initial Router A
        Map<String, Information> informationA = new HashMap<String, Information>();
        informationA.put("Network1", new Information("Network1", 0, "null"));
        informationA.put("Network2", new Information("Network2", 0, "null"));
        informationA.put("Network3", new Information("Network3", 0, "null"));
        Router routerA = new Router("RouterA", informationA, true);
        routerA.setAdjRouterMissCount("RouterB", 0);
        routerA.setAdjRouterMissCount("RouterD", 0);
        routerA.setAdjRouterMissCount("RouterE", 0);
        this.routerMap.put("RouterA", routerA);

        //Initial Router B
        Map<String, Information> informationB = new HashMap<String, Information>();
        informationB.put("Network3", new Information("Network3", 0, "null"));
        informationB.put("Network4", new Information("Network4", 0, "null"));
        Router routerB = new Router("RouterB", informationB, true);
        routerB.setAdjRouterMissCount("RouterA", 0);
        routerB.setAdjRouterMissCount("RouterC", 0);
        this.routerMap.put("RouterB", routerB);

        //Initial Router C
        Map<String, Information> informationC = new HashMap<String, Information>();
        informationC.put("Network4", new Information("Network4", 0, "null"));
        informationC.put("Network5", new Information("Network5", 0, "null"));
        Router routerC = new Router("RouterC", informationC, true);
        routerC.setAdjRouterMissCount("RouterB", 0);
        routerC.setAdjRouterMissCount("RouterF", 0);
        this.routerMap.put("RouterC", routerC);

        //Initial Router D
        Map<String, Information> informationD = new HashMap<String, Information>();
        informationD.put("Network2", new Information("Network2", 0, "null"));
        informationD.put("Network5", new Information("Network5", 0, "null"));
        Router routerD = new Router("RouterD", informationD, true);
        routerD.setAdjRouterMissCount("RouterA", 0);
        routerD.setAdjRouterMissCount("RouterE", 0);
        routerD.setAdjRouterMissCount("RouterF", 0);
        this.routerMap.put("RouterD", routerD);

        //Initial Router E
        Map<String, Information> informationE = new HashMap<String, Information>();
        informationE.put("Network1", new Information("Network1", 0, "null"));
        informationE.put("Network6", new Information("Network6", 0, "null"));
        Router routerE = new Router("RouterE", informationE, true);
        routerE.setAdjRouterMissCount("RouterA", 0);
        routerE.setAdjRouterMissCount("RouterD", 0);
        routerE.setAdjRouterMissCount("RouterF", 0);
        this.routerMap.put("RouterE", routerE);

        //Initial Router F
        Map<String, Information> informationF = new HashMap<String, Information>();
        informationF.put("Network5", new Information("Network5", 0, "null"));
        informationF.put("Network6", new Information("Network6", 0, "null"));
        Router routerF = new Router("RouterF", informationB, true);
        routerF.setAdjRouterMissCount("RouterC", 0);
        routerF.setAdjRouterMissCount("RouterD", 0);
        routerF.setAdjRouterMissCount("RouterE", 0);
        this.routerMap.put("RouterF", routerF);
    }

    public void initNetwork() {
        this.networkMap = new HashMap<String, Network>();

        //Initial Network1
        List<String> adj1 = new ArrayList<String>();
        adj1.add("RouterA");
        adj1.add("RouterE");
        networkMap.put("Network1", new Network(adj1));

        //Initial Network2
        List<String> adj2 = new ArrayList<String>();
        adj2.add("RouterA");
        adj2.add("RouterD");
        networkMap.put("Network2", new Network(adj2));

        //Initial Network3
        List<String> adj3 = new ArrayList<String>();
        adj3.add("RouterA");
        adj3.add("RouterB");
        networkMap.put("Network3", new Network(adj3));

        //Initial Network4
        List<String> adj4 = new ArrayList<String>();
        adj4.add("RouterB");
        adj4.add("RouterC");
        networkMap.put("Network4", new Network(adj4));

        //Initial Network5
        List<String> adj5 = new ArrayList<String>();
        adj5.add("RouterC");
        adj5.add("RouterF");
        networkMap.put("Network5", new Network(adj5));

        //Initial Network6
        List<String> adj6 = new ArrayList<String>();
        adj6.add("RouterD");
        adj6.add("RouterE");
        adj6.add("RouterF");
        networkMap.put("Network6", new Network(adj6));
    }
}
