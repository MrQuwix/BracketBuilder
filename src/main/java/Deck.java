public class Deck {
    public  String cPlaneswalker;
    public  String cType;

    public Deck (String name, String type) {
        cPlaneswalker = name;
        cType = type;
    }

    public  String getPlaneswalker() {
        return cPlaneswalker;
    }

    public  void setPlaneswalker(String planeswalker) {
        cPlaneswalker = planeswalker;
    }

    public  String getType() {
        return cType;
    }

    public  void setType(String type) {
        cType = type;
    }


    public boolean canPlay(Deck opponent) {
        return !cType.equals(opponent.getType());
    }

}
