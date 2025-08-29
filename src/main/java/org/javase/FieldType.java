package org.javase;


public enum FieldType {
    TEXT(1, "text"),
    ENUM(2, "enum"),
    DATE(3, "date"),
    JSON_EDITOR(4, "json-editor"),
    BOOLEAN(5, "bool"),
    INTEGER(6, "int"),
    CHIPS(7, "chips"),
    TIME(9, "time"),
    RADIO(10, "radio"),
    COMMA_SEPARATED(11, "comma-separated"),
    ARRAY_STRING(12, "arrayString"),
    PASSWORD(13, "password"),
    LIST(14, "list");

    private final int id;
    private final String name;

    FieldType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static boolean contains(String name) {
        for (FieldType value : values()) {
            if (value.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
