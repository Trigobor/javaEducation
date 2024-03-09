package DecoratorPattern;

public class Decorator implements HandyMan {

    HandyMan handyMan;

    public Decorator(HandyMan handyMan) {
        this.handyMan = handyMan;
    }

    @Override
    public String makeSomething() {
        return handyMan.makeSomething();
    }
}
