public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        if(n < 2L) return 1L;
        long min = 0L;
        long max = n;

        long test = n;
        boolean test_fails = isBadVersion.isFailingVersion(test);
        boolean test_before_fails = isBadVersion.isFailingVersion(test-1);
        while(!(test_fails && !test_before_fails)) {
            if(test_fails) {
                max = test;
                test = (max + min) / 2;
            }
            else {
                min = test;
                test = (max + min) / 2;
            }
            test_fails = isBadVersion.isFailingVersion(test);
            test_before_fails = isBadVersion.isFailingVersion(test-1);
        }
        return test;
    }
}
