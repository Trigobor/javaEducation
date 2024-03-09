package BulderPattern;

public class Pizza {
    Sauce sauce;
    Boolean pepperoni;
    Boolean bacon;
    Boolean beefBalls;
    Boolean chickenBites;
    Boolean tomato;
    Boolean olive;
    Boolean chillyPepper;
    Boolean jalapenoPepper;

    public static class Backer {
        private final Pizza pizza;

        Backer(){
            pizza = new Pizza();
        }

        public Backer sauce(Sauce sauce){
            pizza.sauce = sauce;
            return this;
        }

        public Backer pepperoni(boolean pepperoni){
            pizza.pepperoni = pepperoni;
            return this;
        }

        public Backer bacon(boolean bacon){
            pizza.bacon = bacon;
            return this;
        }

        public Backer beefBalls(boolean beefBalls){
            pizza.beefBalls = beefBalls;
            return this;
        }

        public Backer chickenBites(boolean chickenBites){
            pizza.chickenBites = chickenBites;
            return this;
        }

        public Backer tomato(boolean tomato){
            pizza.tomato = tomato;
            return this;
        }

        public Backer olive(boolean olive){
            pizza.olive = olive;
            return this;
        }

        public Backer chillyPepper(boolean chillyPepper){
            pizza.chillyPepper = chillyPepper;
            return this;
        }

        public Backer jalapenoPepper(boolean jalapenoPepper){
            pizza.jalapenoPepper = jalapenoPepper;
            return this;
        }

        public Pizza bake(){
            return pizza;
        }
    }
}
