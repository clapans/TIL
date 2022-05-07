package AbstractPackage;

abstract class AbstractUnit{
    abstract void move();
}

class AbstractMarine extends AbstractUnit{
    void move(){};
}

class AbstractTank extends AbstractUnit{
    void move(){};
}

class AbstractDropship extends AbstractUnit{
    void move(){};
}
