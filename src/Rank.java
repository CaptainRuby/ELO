public class Rank {
    public Ranker A;
    public Ranker B;
    public double Ea;
    public double Eb;
    public double Sa;
    public int k;
    public boolean finish;

    public Rank(Ranker a, Ranker b) {
        A = a;
        B = b;
        Ea = ELOUtils.winRate(a,b);
        Eb = 1-Ea;
    }

    public Rank(Ranker a, Ranker b, double ea) {
        A = a;
        B = b;
        Ea = ea;
        Eb = 1-Ea;
    }

    /**
     * 进行rank并结算
     *
     * @param sa   A的实际胜负值，胜=1，平=0.5，负=0
     */
    public void rank(int k,double sa) {
        this.Sa = sa;
        this.k = k;
        A.point += k*(Sa-Ea);
        B.point += k*(Sa-Eb);
        finish = true;
    }


}
