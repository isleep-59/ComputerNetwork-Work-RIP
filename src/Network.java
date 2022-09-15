import java.io.Serializable;
import java.util.*;

public class Network implements Serializable {
    String networkName;
    List<Router> adjRouterList;

    public Network() {

    }

    public Network(String networkName, List<Router> adjRouterList) {
        this.networkName = networkName;
        this.adjRouterList = adjRouterList;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public List<Router> getAdjRouterList() {
        return adjRouterList;
    }

    public void setAdjRouterList(List<Router> adjRouterList) {
        this.adjRouterList = adjRouterList;
    }
}