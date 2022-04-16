
class Problem {
    public static void main(String[] args) {
        int testIndex = -1;
        for (int i = 0; i < args.length; i++) {
            if ("test".equals(args[i])) {
                testIndex = i;
                break;
            }
        }
        System.out.println(testIndex);
    }
}