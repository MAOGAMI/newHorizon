package pl.mkpk.newhorizon.cookbook;

public enum StackType {
    UNIT("sztuka","sztuki"),
    GRAMS("g","g"),
    ML("ml","ml"),
    GlASS("szklanka","szklanki"),
    SPOON("łyżka","łyżki"),
    TEASPOON("łyżeczka","łyżeczki"),
    PINCH("szczypta","szczypty");

    private String category;
    private String category_many;

    StackType(String category, String category_many) {
        this.category = category;
        this.category_many = category_many;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
