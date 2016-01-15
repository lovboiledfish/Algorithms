package solutions;

/**
 * Created by PPlovboiledfish on 1/14/16.
 */
public class IntervalSumII {
    private long[] _BIT;
    private int[] _data;

    /**
     * @param A: An integer array
     */
    public IntervalSumII(int[] A) {
        _BIT = new long[A.length + 1];
        _data = A;
        for (int i = 0; i < A.length; ++i)
            _modify(i, A[i]);
    }

    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        if (start > end)
            return -1;
        return _query(end) - _query(start - 1);
    }

    private long _query(int range) {
        ++range;
        long sum = 0;
        for (; range > 0; range -= (range & (-range)))
            sum += _BIT[range];
        return sum;
    }

    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        int delta = value - _data[index];
        _data[index] = value;
        _modify(index, delta);
    }

    private void _modify(int index, int delta) {
        ++index;
        for (; index < _BIT.length; index += (index & (-index)))
            _BIT[index] += delta;
    }

    static public class Test{
        static public void randomTest() {
            int[] A = {6997,6794,5375,4057,5654,5566,5816,6153,4369,5207,2659,9291,9188,8350,9555,7796,7662,5849,9215,2096,2720,2788,3527,6994,1272,7857,7446,2937,7960,8751,9020,5997,2946,2466,9569,8268,7711,2236,3587,7152,9467,5455,7866,4276,3250,2840,2123,4897,5612,3005,7140,5945,1321,8850,6174,7406,4135,9586,8786,2669,4971,5437,5838,3277,8071,7034,4998,6000,1764,9318,7860,7599,2008,6887,4705,9013,4444,6651,2435,2533,4470,7504,5975,1700,7260,9648,3779,9672,7745,2692,2754,4441,7048,9180,3108,7872,7767,3714,8513,3653,1334,9855,3936,1357,1495,6775,8438,5234,2094,5254,2561,4373,7245,7461,5486,2380,7906,6735,5572,9638,4626,7446,1214,7523,1018,4120,4383,6436,2966,5350,3433,9389,2275,4819,6843,2173,5824,9176,9188,1343,5797,6061,7976,3024,3766,2379,7868,2951,4605,6464,6280,3060,7042,1325,3918,7511,9826,9260,7133,8459,6588,2033,6563,5477,9715,3199,2105,9154,9727,1697,5601,4438,1554,1228,2758,4939,7869,9823,6739,4962,1995,1491,7498,9682,6944,4854,5211,5622,8654,8450,8128,3984,7170,1607,3981,3575,6429,5911,7708,2288,9818,7029,8380,7283,7920,7075,3128,8220,2306,2805,4846,2268,8878,7313,3670,8283,4367,8096,3693,5642,6035,8752,8602,8753,3186,9691,3872,1962,2372,8715,3002,3252,8028,5927,9049,3894,5978,5055,9246,5851,4318,2208,2589,2116,8467,8537,6175,9293,6747,9367,8049,7487,3788,5625,6893,6149,1817,8516,9133,8048,7759,5165,3038,2447,5269,4682,8813,6393,6420,7055,1101,2040,5789,1988,5537,9180,2654,6269,2641,9844,6305,1688,8250,7568,7168,6293,8945,7434,2818,2285,2989,5301,7990,7350,9652,6803,7608,4397,6586,7515,6817,6064,9573,7757,3975,6374,3936,3611,6000,4141,5844,9657,4917,3943,7098,8821,6867,9114,5782,9010,1765,2650,7021,3977,6720,4508,9864,3448,7509,7649,6061,4620};
            IntervalSumII _solution = new IntervalSumII(A);
            _solution.modify(255,4606);
            _solution.modify(33,4085);
            _solution.modify(91,6006);
            _solution.modify(209,8613);
            _solution.modify(115,3538);
            _solution.modify(245,8621);
            _solution.query(0,331);
            _solution.modify(114,4910);
            _solution.query(2,331);
            _solution.modify(76,5102);
            _solution.query(2,331);
            _solution.modify(73,9815);
            _solution.modify(159,7871);
            _solution.modify(248,7116);
            _solution.modify(82,5469);
            _solution.modify(205,5377);
            _solution.modify(15,3413);
            _solution.modify(307,5687);
            _solution.modify(324,5312);
            _solution.modify(140,1790);
            _solution.query(1,331);
            _solution.modify(161,9475);
            _solution.modify(87,2109);
            _solution.modify(159,9304);
            _solution.modify(85,8227);
            _solution.modify(23,9747);
            _solution.query(0,330);
            _solution.modify(10,9900);
            _solution.modify(160,2423);
            _solution.modify(272,7458);
            _solution.modify(24,3575);
            _solution.modify(30,1744);
            _solution.modify(43,5764);
        }

        static public long sum(int[] A, int start, int end) {
            long sum = 0;
            for (; start <= end; ++start)
                sum += A[start];
            return sum;
        }
    }
}
