import javax.imageio.plugins.tiff.TIFFImageReadParam;
import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router extends Thread implements Serializable {
    private Integer updateTime;
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

    @Override
    public String toString() {
        return "RouterName: " + routerName + "\n" +
                "Information: \n" + informationTable.values() + "\n";
    }

    //设置定期更新时间
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
    public void setAdjRouterMissCount(Map<Router, Integer> adjRouterMissCount) {
        this.adjRouterMissCount = adjRouterMissCount;
    }

    public Integer getAdjRouterMissCount(Router router) {
        return this.adjRouterMissCount.get(router);
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public Map<String, Information> getInformationTable() {
        return informationTable;
    }

    public void setInformationTable(Map<String, Information> informationTable) {
        this.informationTable = informationTable;
    }

    public Boolean getRouterStatus() {
        return routerStatus;
    }

    public void setRouterStatus(Boolean routerStatus) {
        this.routerStatus = routerStatus;
    }

    public void receiveInformationTable(Router router) {
        if (routerStatus == false) {
            return;
        }

        adjRouterMissCount.put(router, 0);

        //改造收到的路由表
        Map<String, Information> comeInformationTable = new HashMap<>();
        for (String networkName : router.getInformationTable().keySet()) {
            int distance = router.getInformationTable().get(networkName).getDistance();
            if(distance < 16) {
                distance += 1;
            }
            comeInformationTable.put(networkName, new Information(networkName, distance, router.routerName));
        }

        for (String comeNetworkName : comeInformationTable.keySet()) {
            Information comeInformation = comeInformationTable.get(comeNetworkName);
            for (String networkName : this.informationTable.keySet()) {
                Information information = this.informationTable.get(networkName);

                //如果目的网络相同
                if(comeInformation.getTargetNetwork().equals(information.getTargetNetwork())) {
                    //如果下一跳相同
                    if(comeInformation.getNextRouterName().equals(information.getNextRouterName())) {
                        information.setDistance(comeInformation.getDistance());
                    }
                    //如果下一跳不相同
                    else {
                        //判断是否更优
                        if(comeInformation.getDistance() < information.getDistance()) {
                            information.setDistance(comeInformation.getDistance());
                            information.setNextRouterName(comeInformation.getNextRouterName());
                        }
                    }
                }
            }
        }
    }

    public void update() {
        if(routerStatus == false) {
            return;
        }

        for (Router adjRouter : adjRouterMissCount.keySet()) {
            adjRouter.receiveInformationTable(this);
            adjRouterMissCount.put(adjRouter, getAdjRouterMissCount(adjRouter) + 1);
        }

        for (Router adjRouter : adjRouterMissCount.keySet()) {
            if(adjRouterMissCount.get(adjRouter) >= 6) {
                for(String networkName : informationTable.keySet()) {
                    Information information = informationTable.get(networkName);
                    if(information.getNextRouterName().equals(adjRouter.routerName)) {
                        information.setDistance(16);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void run() {
        while(routerStatus == true) {
            synchronized (this) {
                update();
            }
            System.out.println(this.routerName + "更新成功！" + "\n" + this);

            try {
                Thread.sleep(updateTime.longValue() * 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
