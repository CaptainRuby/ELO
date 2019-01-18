import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Ranker> list = new ArrayList<>();
        int t = 1000;
        Random random = new Random();
        while (t-->0){
            list.add(new Animate(1000+random.nextInt(1000)));
        }
//        long begin = System.currentTimeMillis();
//        List<Rank> result = ELOUtils.match(list,10);
//        long end = System.currentTimeMillis();
//        for (Rank rank:result) {
//            rank.rank(32,1);
//        }
//        System.out.println((end-begin));
//
//        System.out.println();
        for (Ranker ranker:list) {
            if (ranker.point == 1001){
                list.remove(ranker);
                System.out.println("y");
            }
        }
    }
}
