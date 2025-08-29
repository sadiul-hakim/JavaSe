void main() {
    Scanner INPUT = new Scanner(System.in);

    while (true) {
        println("Name: ");
        String name = INPUT.nextLine();
        name = name.toLowerCase()
                .replace(" ", "_")
                .replace(".", "")
                .replace("(", "")
                .replace(")", "")
                + "_2025";
        System.out.println(name);
    }
}