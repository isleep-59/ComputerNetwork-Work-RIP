import javax.imageio.plugins.tiff.TIFFImageReadParam;
import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import java.io.Serializable;
import java.util.Map;

public class Router extends Thread implements Serializable {
    private Integer updateTime;
    private String routerName;
    private Map<String, Information> informationTable;   //Ŀ������Ϊ����
    private Map<Router, Integer> adjRouterMissCount;    //·����Ϊ����
    private Boolean routerStatus;

    public Router() {
        updateTime = 30;
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

    //���ö��ڸ���ʱ��
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
        if(routerStatus == false) {
            return;
        }

        adjRouterMissCount.put(router, 0);
        Map<String, Information> comeInformationTable = router.getInformationTable();
        for(String comeNetworkName : comeInformationTable.keySet()) {
            Information comeInformation = comeInformationTable.get(comeNetworkName);
            boolean flag = false;
            for(String networkName : this.informationTable.keySet()) {
                Information information = this.informationTable.get(networkName);
                if(information == comeInformation) {
                    flag = true;
                    continue;
                }

                if(comeNetworkName == networkName) {
                    flag = true;
                    if(comeInformation.getNextRouterName() == information.getNextRouterName()) {
                        information.setDistance(comeInformation.getDistance());
                    }
                    else {
                        if(comeInformation.getDistance() < information.getDistance()) {
                            information.setNextRouterName(comeInformation.getNextRouterName());
                            information.setDistance(comeInformation.getDistance());
                        }
                    }
                }
            }
            if(flag == false) {
                this.informationTable.put(comeInformation.getTargetNetwork(), comeInformation);
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
            if(adjRouterMissCount.get(adjRouter) == 6) {
                for(String networkName : informationTable.keySet()) {
                    Information information = informationTable.get(networkName);
                    if(information.getNextRouterName() == adjRouter.routerName) {
                        information.setDistance(16);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        if(routerStatus == true) {
            try {
                Thread.sleep(updateTime.longValue() * 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            update();
            System.out.println(this.routerName + "���³ɹ���" + "\n" + this);
        }
    }
}
