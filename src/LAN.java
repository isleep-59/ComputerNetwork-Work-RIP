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

    //设置定期更新时间
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
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

        //初始化路由器A
        Map<String, Information> informationA = new HashMap<String, Information>();
        informationA.put("Network1", new Information("Network1", 0, "null"));
        informationA.put("Network2", new Information("Network2", 0, "null"));
        informationA.put("Network3", new Information("Network3", 0, "null"));
        routerA.setRouterName("RouterA"); routerA.setInformation(informationA); routerA.setRouterStatus(true);
        routerA.setAdjRouterMissCount(routerB, 0);
        routerA.setAdjRouterMissCount(routerD, 0);
        routerA.setAdjRouterMissCount(routerE, 0);
        this.routerMap.put("RouterA", routerA);

        //初始化路由器B
        Map<String, Information> informationB = new HashMap<String, Information>();
        informationB.put("Network3", new Information("Network3", 0, "null"));
        informationB.put("Network4", new Information("Network4", 0, "null"));
        routerB.setRouterName("RouterB"); routerB.setInformation(informationB); routerB.setRouterStatus(true);
        routerB.setAdjRouterMissCount(routerA, 0);
        routerB.setAdjRouterMissCount(routerC, 0);
        this.routerMap.put("RouterB", routerB);

        //初始化路由器C
        Map<String, Information> informationC = new HashMap<String, Information>();
        informationC.put("Network4", new Information("Network4", 0, "null"));
        informationC.put("Network5", new Information("Network5", 0, "null"));
        routerC.setRouterName("RouterC"); routerC.setInformation(informationC); routerC.setRouterStatus(true);
        routerC.setAdjRouterMissCount(routerB, 0);
        routerC.setAdjRouterMissCount(routerF, 0);
        this.routerMap.put("RouterC", routerC);

        //初始化路由器D
        Map<String, Information> informationD = new HashMap<String, Information>();
        informationD.put("Network2", new Information("Network2", 0, "null"));
        informationD.put("Network5", new Information("Network5", 0, "null"));
        routerD.setRouterName("RouterD"); routerD.setInformation(informationD); routerD.setRouterStatus(true);
        routerD.setAdjRouterMissCount(routerA, 0);
        routerD.setAdjRouterMissCount(routerE, 0);
        routerD.setAdjRouterMissCount(routerF, 0);
        this.routerMap.put("RouterD", routerD);

        //初始化路由器E
        Map<String, Information> informationE = new HashMap<String, Information>();
        informationE.put("Network1", new Information("Network1", 0, "null"));
        informationE.put("Network6", new Information("Network6", 0, "null"));
        routerE.setRouterName("RouterE"); routerE.setInformation(informationE); routerE.setRouterStatus(true);
        routerE.setAdjRouterMissCount(routerA, 0);
        routerE.setAdjRouterMissCount(routerD, 0);
        routerE.setAdjRouterMissCount(routerF, 0);
        this.routerMap.put("RouterE", routerE);

        //初始化路由器F
        Map<String, Information> informationF = new HashMap<String, Information>();
        informationF.put("Network5", new Information("Network5", 0, "null"));
        informationF.put("Network6", new Information("Network6", 0, "null"));
        routerF.setRouterName("RouterF"); routerF.setInformation(informationF); routerF.setRouterStatus(true);
        routerF.setAdjRouterMissCount(routerC, 0);
        routerF.setAdjRouterMissCount(routerD, 0);
        routerF.setAdjRouterMissCount(routerE, 0);
        this.routerMap.put("RouterF", routerF);
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
    public void addNetwork(Network network, List<Router> adjRouterList) {
        networkMap.put(network.getNetworkName(), new Network(network.getNetworkName(), adjRouterList));
    }
    
    //网络删除
    public void deleteNetwork(Network network) {
        for(Router router : network.getAdjRouterList()) {
            for(String targetNetworkName : router.getInformation().keySet()) {
                if(targetNetworkName == network.networkName) {
                    Information information = router.getInformation().get(targetNetworkName);
                    information.setDistance(16);
                    router.getInformation().put(targetNetworkName, information);
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
        for(String targetNetworkName : router.getInformation().keySet()) {
            System.out.println(router.getInformation().get(targetNetworkName));
        }
    }
}
