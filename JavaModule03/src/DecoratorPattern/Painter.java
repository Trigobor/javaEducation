package DecoratorPattern;

public class Painter extends Decorator{

    public Painter(HandyMan handyMan) {
        super(handyMan);
    }

    public String makeWork() {
        return "painting walls ";
    }

    @Override
    public String makeSomething() {
        return super.makeSomething() + makeWork();
    }
}
