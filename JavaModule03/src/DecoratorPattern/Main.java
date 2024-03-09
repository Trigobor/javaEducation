package DecoratorPattern;

public class Main {
    public static void main(String[] args) {
        HandyMan handyMan = new Painter(new Sticker(new Worker()));

        System.out.println(handyMan.makeSomething());
    }
}
