package DecoratorPattern;

public class Sticker extends Decorator {

    public Sticker(HandyMan handyMan) {
        super(handyMan);
    }

    public String makeWork() {
        return "wallpapering ";
    }

    @Override
    public String makeSomething() {
        return super.makeSomething() + makeWork();
    }
}
