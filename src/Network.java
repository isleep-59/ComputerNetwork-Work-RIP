import java.io.Serializable;
import java.util.*;

public class Network implements Serializable {
    List<Router> adjRouterList;

    public Network() {

    }

    public Network(List<Router> adjRouterList) {
        this.adjRouterList = adjRouterList;
    }

    public List<Router> getAdjRouterList() {
        return adjRouterList;
    }

    public void setAdjRouterList(List<Router> adjRouterList) {
        this.adjRouterList = adjRouterList;
    }
}