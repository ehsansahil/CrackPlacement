import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        find(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    void find(List<List<Integer>> res, List<Integer> temp, int start, int n, int k) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++) {
            temp.add(i);
            find(res, temp, i + 1, n, k);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combine(4, 2));
    }
}