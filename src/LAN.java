import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LAN {
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

    //初始化路由器
    public void initRouter() {
        this.routerMap = new HashMap<String, Router>();
        Router routerA = new Router();
        Router routerB = new Router();
        Router routerC = new Router();
        Router routerD = new Router();
        Router routerE = new Router();
        Router routerF = new Router();
        this.routerMap.put("RouterA", routerA);
        this.routerMap.put("RouterB", routerB);
        this.routerMap.put("RouterC", routerC);
        this.routerMap.put("RouterD", routerD);
        this.routerMap.put("RouterE", routerE);
        this.routerMap.put("RouterF", routerF);

        //初始化路由器A
        Map<String, Information> informationTableA = new HashMap<>();
        Map<Router, Integer> adjRouterMissCountA = new HashMap<>();
        informationTableA.put("Network1", new Information("Network1", 1, "null"));
        informationTableA.put("Network2", new Information("Network2", 1, "null"));
        informationTableA.put("Network3", new Information("Network3", 1, "null"));
        informationTableA.put("Network4", new Information("Network4", 16, "null"));
        informationTableA.put("Network5", new Information("Network5", 16, "null"));
        informationTableA.put("Network6", new Information("Network6", 16, "null"));
        adjRouterMissCountA.put(routerB, 0);
        adjRouterMissCountA.put(routerD, 0);
        adjRouterMissCountA.put(routerE, 0);
        routerA.setRouterName("RouterA");
        routerA.setInformationTable(informationTableA);
        routerA.setAdjRouterMissCount(adjRouterMissCountA);
        routerA.setRouterStatus(true);

        //初始化路由器B
        Map<String, Information> informationTableB = new HashMap<>();
        Map<Router, Integer> adjRouterMissCountB = new HashMap<>();
        informationTableB.put("Network1", new Information("Network1", 16, "null"));
        informationTableB.put("Network2", new Information("Network2", 16, "null"));
        informationTableB.put("Network3", new Information("Network3", 1, "null"));
        informationTableB.put("Network4", new Information("Network4", 1, "null"));
        informationTableB.put("Network5", new Information("Network5", 16, "null"));
        informationTableB.put("Network6", new Information("Network6", 16, "null"));

        adjRouterMissCountB.put(routerA, 0);
        adjRouterMissCountB.put(routerC, 0);
        routerB.setRouterName("RouterB");
        routerB.setInformationTable(informationTableB);
        routerB.setAdjRouterMissCount(adjRouterMissCountB);
        routerB.setRouterStatus(true);

        //初始化路由器C
        Map<String, Information> informationTableC = new HashMap<>();
        Map<Router, Integer> adjRouterMissCountC = new HashMap<>();
        informationTableC.put("Network1", new Information("Network1", 16, "null"));
        informationTableC.put("Network2", new Information("Network2", 16, "null"));
        informationTableC.put("Network3", new Information("Network3", 16, "null"));
        informationTableC.put("Network4", new Information("Network4", 1, "null"));
        informationTableC.put("Network5", new Information("Network5", 1, "null"));
        informationTableC.put("Network6", new Information("Network6", 16, "null"));
        adjRouterMissCountC.put(routerB, 0);
        adjRouterMissCountC.put(routerF, 0);
        routerC.setRouterName("RouterC");
        routerC.setInformationTable(informationTableC);
        routerC.setAdjRouterMissCount(adjRouterMissCountC);
        routerC.setRouterStatus(true);

        //初始化路由器D
        Map<String, Information> informationTableD = new HashMap<>();
        Map<Router, Integer> adjRouterMissCountD = new HashMap<>();
        informationTableD.put("Network1", new Information("Network1", 16, "null"));
        informationTableD.put("Network2", new Information("Network2", 1, "null"));
        informationTableD.put("Network3", new Information("Network3", 16, "null"));
        informationTableD.put("Network4", new Information("Network4", 16, "null"));
        informationTableD.put("Network5", new Information("Network5", 1, "null"));
        informationTableD.put("Network6", new Information("Network6", 16, "null"));
        adjRouterMissCountD.put(routerA, 0);
        adjRouterMissCountD.put(routerE, 0);
        adjRouterMissCountD.put(routerF, 0);
        routerD.setRouterName("RouterD");
        routerD.setInformationTable(informationTableD);
        routerD.setAdjRouterMissCount(adjRouterMissCountD);
        routerD.setRouterStatus(true);

        //初始化路由器E
        Map<String, Information> informationTableE = new HashMap<>();
        Map<Router, Integer> adjRouterMissCountE = new HashMap<>();
        informationTableE.put("Network1", new Information("Network1", 1, "null"));
        informationTableE.put("Network2", new Information("Network2", 16, "null"));
        informationTableE.put("Network3", new Information("Network3", 16, "null"));
        informationTableE.put("Network4", new Information("Network4", 16, "null"));
        informationTableE.put("Network5", new Information("Network5", 16, "null"));
        informationTableE.put("Network6", new Information("Network6", 1, "null"));
        adjRouterMissCountE.put(routerA, 0);
        adjRouterMissCountE.put(routerD, 0);
        adjRouterMissCountE.put(routerF, 0);
        routerE.setRouterName("RouterE");
        routerE.setInformationTable(informationTableE);
        routerE.setAdjRouterMissCount(adjRouterMissCountE);
        routerE.setRouterStatus(true);

        //初始化路由器F
        Map<String, Information> informationTableF = new HashMap<>();
        Map<Router, Integer> adjRouterMissCountF = new HashMap<>();
        informationTableF.put("Network1", new Information("Network1", 16, "null"));
        informationTableF.put("Network2", new Information("Network2", 16, "null"));
        informationTableF.put("Network3", new Information("Network3", 16, "null"));
        informationTableF.put("Network4", new Information("Network4", 16, "null"));
        informationTableF.put("Network5", new Information("Network5", 1, "null"));
        informationTableF.put("Network6", new Information("Network6", 1, "null"));
        adjRouterMissCountF.put(routerC, 0);
        adjRouterMissCountF.put(routerD, 0);
        adjRouterMissCountF.put(routerE, 0);
        routerF.setRouterName("RouterF");
        routerF.setInformationTable(informationTableF);
        routerF.setAdjRouterMissCount(adjRouterMissCountF);
        routerF.setRouterStatus(true);
    }

    //初始化网络及邻接路由器关系
    public void initNetwork() {
        this.networkMap = new HashMap<String, Network>();

        //初始化网络1
        List<Router> adj1 = new ArrayList<Router>();
        adj1.add(routerMap.get("RouterA"));
        adj1.add(routerMap.get("RouterE"));
        networkMap.put("Network1", new Network("Network1", adj1));

        //初始化网络2
        List<Router> adj2 = new ArrayList<Router>();
        adj2.add(routerMap.get("RouterA"));
        adj2.add(routerMap.get("RouterD"));
        networkMap.put("Network2", new Network("Network2", adj2));

        //初始化网络3
        List<Router> adj3 = new ArrayList<Router>();
        adj3.add(routerMap.get("RouterA"));
        adj3.add(routerMap.get("RouterB"));
        networkMap.put("Network3", new Network("Network3", adj3));

        //初始化网络4
        List<Router> adj4 = new ArrayList<Router>();
        adj4.add(routerMap.get("RouterB"));
        adj4.add(routerMap.get("RouterC"));
        networkMap.put("Network4", new Network("Network4", adj4));

        //初始化网络5
        List<Router> adj5 = new ArrayList<Router>();
        adj5.add(routerMap.get("RouterC"));
        adj5.add(routerMap.get("RouterF"));
        networkMap.put("Network5", new Network("Network5", adj5));

        //初始化网络6
        List<Router> adj6 = new ArrayList<Router>();
        adj6.add(routerMap.get("RouterD"));
        adj6.add(routerMap.get("RouterE"));
        adj6.add(routerMap.get("RouterF"));
        networkMap.put("Network6", new Network("Network6", adj6));
    }

    //网络加入
    public void addNetwork(String networkName, List<Router> adjRouterList) {
        networkMap.put(networkName, new Network(networkName, adjRouterList));
    }
    
    //网络删除
    public void deleteNetwork(Network network) {
        for(Router router : network.getAdjRouterList()) {
            for(String targetNetworkName : router.getInformationTable().keySet()) {
                if(targetNetworkName == network.networkName) {
                    Information information = router.getInformationTable().get(targetNetworkName);
                    information.setDistance(16);
                    //router.getInformationTable().put(targetNetworkName, informationTable);
                }
            }
        }
    }

    //路由器故障
    public void routerFault(Router router) {
        router.setRouterStatus(false);
    }

    //输出指定路由器的路由表
    public void displayRouterInformation(Router router) {
        for(String targetNetworkName : router.getInformationTable().keySet()) {
            System.out.println(router.getInformationTable().get(targetNetworkName));
        }
    }

    public void setRouterUpdateTime(int t) {
        for(String routerName : routerMap.keySet()) {
            routerMap.get(routerName).setUpdateTime(t * 10);
        }
    }
}
