package DACore;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by KhEquipment on 2/15/15.
 */
public class LikelihoodFunction implements Function<Collection<Double>,Double> {
    public static final Boolean DEBUG = false;

    private Function<Double,Double> p = null;

    public LikelihoodFunction(Function<Double, Double> p) {
        this.p = p;
    }

    @Override
    public Double apply(Collection<Double> doubles) {
        return Math.log(doubles.stream().map(x ->{
            if (DEBUG) {
                System.out.println(x + ":" + p.apply(x));
            }
            return p.apply(x);
        }).reduce(1d, (p1, p2) -> p1 * p2));

    }
}
