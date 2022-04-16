class Problem {
    public static void main(String[] args) {
        boolean foundMode = false;

        for (int i = 0; i < args.length; i += 2) {
            if ("mode".equals(args[i])) {
                System.out.println(args[i + 1]);
                foundMode = true;
            }
        }

        if (!foundMode) {
            System.out.println("default");
        }
    }
}