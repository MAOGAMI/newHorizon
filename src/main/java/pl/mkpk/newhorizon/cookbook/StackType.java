package pl.mkpk.newhorizon.cookbook;

public enum StackType {
    UNIT("sztuka","sztuki"),
    PACKAGE("paczka","paczki"),
    BOX("opakowanie","opakowania"),
    BAR("tabliczka","tabliczki"),
    GRAMS("g","g"),
    ML("ml","ml"),
    GLASS("szklanka","szklanki"),
    SPOON("łyżka","łyżki"),
    TEASPOON("łyżeczka","łyżeczki"),
    PINCH("szczypta","szczypty");

    private String category;
    private String category_many;

    StackType(String category, String category_many) {
        this.category = category;
        this.category_many = category_many;
    }

    public static StackType fromString(String text) {
        for (StackType b : StackType.values()) {
            if (b.category.equalsIgnoreCase(text)) {
                return b;
            }
            if (b.category_many.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
