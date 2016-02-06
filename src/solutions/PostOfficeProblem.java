package solutions;

import java.util.Arrays;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 2/5/16.
 */
class Solution {
	int [][]init(int []A)
	{
		int n = A.length;
		int [][]dis = new int [n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = i+1 ;j <= n;++j)
			{
				int mid = (i+j) /2;
				for(int k = i;k <= j;++k)
					dis[i][j] += Math.abs(A[k - 1] - A[mid - 1]);
			}
		}
		return dis;
	}

	public int postOffice(int[] A, int k) {
		// Write your code here
		int n = A.length;
		Arrays.sort(A);

		int [][]dis = init(A);
		int [][]dp = new int[n + 1][k + 1];
		if(n == 0 || k >= A.length)
			return 0;
		for(int i = 0;i <= n;++i)  {
			dp[i][1] = dis[1][i];

		}


		for(int nk = 2; nk <= k; nk++) {

			for(int i = nk; i <= n; i++) {
				dp[i][nk] = Integer.MAX_VALUE;
				for(int j = 0; j < i; j++) {
					if(dp[i][nk] == Integer.MAX_VALUE || dp[i][nk] > dp[j][nk-1] + dis[j+1][i])
						dp[i][nk] = dp[j][nk-1] + dis[j+1][i];
				}
			}
		}
		return dp[n][k];
	}
}

public class PostOfficeProblem {
	/**
	 * @param A an integer array
	 * @param k an integer
	 * @return an integer
	 */
	public int postOffice(int[] A, int k) {
		if (A == null || k >= A.length)
			return 0;
		Arrays.sort(A);

		final int n = A.length;
		int[][] euclidDist = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = i + 1; j <= n; ++j) {
				euclidDist[i][j] = euclidDist[i][j - 1] + Math.abs(A[i - 1] - A[j - 1]);
			}
			for (int j = i - 1; j > 0; --j) {
				euclidDist[i][j] = euclidDist[i][j + 1] + Math.abs(A[i - 1] - A[j - 1]);
			}
		}

		int[][][] minCost = new int[n + 1][k + 1][2]; // 0 - min cost, 1 - where last PO is
		for (int i = 1; i <= n; ++i) {
			int PO = (i + 1) >> 1;
			minCost[i][1][0] = euclidDist[PO][1] + euclidDist[PO][i];
			if (i <= k) {
				minCost[i][i][1] = i - 1;
			}
		}
		for (int iK = 2; iK <= k; ++iK) {
			for (int iN = n; iN >= iK; --iN) {
				int min = Integer.MAX_VALUE;
				int PO = -1;
				int lastPO = (iN == n) ? Integer.MAX_VALUE : minCost[iN + 1][iK][1];
				for (int margin = iK - 1; margin <= Math.min(iN - 1, lastPO); ++margin) {
					int curPO = (margin + 1 + iN) >> 1;
					int cost = minCost[margin][iK - 1][0] + euclidDist[curPO][margin + 1] + euclidDist[curPO][iN];
					if (min > cost) {
						min = cost;
						PO = curPO;
					}
				}
				if (PO >= 0) {
					minCost[iN][iK][0] = min;
					minCost[iN][iK][1] = PO;
				}
			}
		}
		return minCost[n][k][0];
	}

	static public class Test {
		static private PostOfficeProblem _solution = new PostOfficeProblem();
		static private Solution _ans = new Solution();

		static public void test(int[] A, int k) {
			Assert.check(_solution.postOffice(A, k) == _ans.postOffice(A, k));
		}

		static public void randomTest() {
			int[] A = {112,122,360,311,85,225,405,53,405,43,342,13,588,424,299,37,104,289,404,414};
			test(A, 3);
		}
	}
}
