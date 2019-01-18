import java.util.ArrayList;
import java.util.List;

public class ELOUtils {
    public static final int K = 32;


    /**
     * 匹配出n组进行rank的对象
     *
     * @param n
     */
    public static List<Rank> match(List<Ranker> rankers, int n) {
        List<Rank> result = new ArrayList<>(n);
        double[] deltas = new double[n];
        for (int i = 0; i < rankers.size(); ++i) {
            for (int j = i + 1; j < rankers.size(); ++j) {
                double Ea = winRate(rankers.get(i), rankers.get(j));
                double delta = Math.abs(Ea - 0.5);
                if (result.size()<n){
                    deltas[result.size()] = delta;
                    result.add(new Rank(rankers.get(i), rankers.get(j), Ea));
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (delta < deltas[k]) {
                        System.arraycopy(deltas, k, deltas, k + 1, n - k - 1);
                        deltas[k] = delta;
                        if (k<result.size()) {
                            result.set(k, new Rank(rankers.get(i), rankers.get(j), Ea));
                        }
                        break;
                    }
                }
            }
        }
        return result;
    }


    /**
     * 计算a与b进行rank的胜率,为避免精度问题，由积分高的一方计算胜率
     *
     * @param a Ranker A
     * @param b Ranker B
     * @return a的胜率
     */
    public static double winRate(Ranker a, Ranker b) {
        if (a.point > b.point) {
            return 1 / (1 + Math.pow(10, (b.point - a.point) / 400.0));
        } else {
            return 1 - (1 / (1 + Math.pow(10, (a.point - b.point) / 400.0)));
        }
    }

}
