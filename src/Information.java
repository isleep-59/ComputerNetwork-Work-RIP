import java.io.Serializable;

public class Information implements Serializable {
    private String targetNetwork;
    private int distance;
    private String nextRouter;

    public Information() {

    }

    public Information(String targetNetwork, int distance, String nextRouter) {
        this.targetNetwork = targetNetwork;
        this.distance = distance;
        this.nextRouter = nextRouter;
    }

    public boolean check(Information information) {
        return targetNetwork.equals(information.targetNetwork) && distance == information.distance && nextRouter.equals(information.nextRouter);
    }

    @Override
    public String toString() {
        return targetNetwork + "\t" + distance + "\t" + nextRouter + "\n";
    }

    public String getTargetNetwork() {
        return targetNetwork;
    }

    public void setTargetNetwork(String targetNetwork) {
        this.targetNetwork = targetNetwork;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getNextRouterName() {
        return nextRouter;
    }

    public void setNextRouterName(String nextRouter) {
        this.nextRouter = nextRouter;
    }
}
