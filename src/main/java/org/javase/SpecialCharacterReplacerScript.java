void main() {
    Scanner INPUT = new Scanner(System.in);

    while (true) {
        println("Text: ");
        String text = INPUT.nextLine();
        text = text.replace("~", "")
                .replace(",", "")
                .replace(".", "")
                .replace("(", "")
                .replace(")", "")
                .replace("{", "")
                .replace("}", "")
                .replace("[", "")
                .replace("]", "")
                .replace(";", "")
                .replace(":", "")
                .replace("'", "")
                .replace("/", "")
                .replace("\\", "")
                .replace("?", "")
                .replace("=", "")
                .replace("*", "")
                .replace("<", "")
                .replace(">", "")
                .replace("`", "")
                .replace("-", "")
                .replace("`", "")
                .replace("\"", "");
        println(text);
    }
}