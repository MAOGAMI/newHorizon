package pl.mkpk.newhorizon.cookbook;

public enum StackType {
    UNIT("sztuki"),
    GRAMS("gramy"),
    ML("ml");

    private String category;

    StackType(String envUrl) {
        this.category = envUrl;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
