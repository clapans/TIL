class Building{}

interface Liftable{
    void liftoff();
    void move(int x,int y);
    void stop();
    void land();
}

class LiftableImpl implements Liftable{
    public void liftoff(){};
    public void move(int x, int y){};
    public void stop(){};
    public void land(){};
}

class Academy extends Building{}
class Bunker extends Building{}
class Factory extends Building implements Liftable{
    LiftableImpl impl = new LiftableImpl();
    public void liftoff(){ impl.liftoff(); };
    public void move(int x, int y){ impl.move(x,y); };
    public void stop(){ impl.stop(); };
    public void land(){ impl.land(); };
}
class Barrack extends Building implements Liftable {
    LiftableImpl impl = new LiftableImpl();
    public void liftoff() { impl.liftoff(); };
    public void move(int x, int y) {impl.move(x, y);};
    public void stop() { impl.stop(); };
    public void land() { impl.land(); };
}

public class Relation {
    public static void main(String[] args) {

    }
}
