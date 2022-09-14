import java.io.Serializable;
import java.util.*;

public class Network implements Serializable {
    List<String> adjRouterList;

    public Network() {

    }

    public Network(List<String> adjRouterList) {
        this.adjRouterList = adjRouterList;
    }

    public List<String> getAdjRouterList() {
        return adjRouterList;
    }

    public void setAdjRouterList(List<String> adjRouterList) {
        this.adjRouterList = adjRouterList;
    }
}