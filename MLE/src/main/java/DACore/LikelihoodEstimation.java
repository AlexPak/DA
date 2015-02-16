package DACore;

import cern.jet.random.Normal;
import cern.jet.random.Uniform;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.RandomEngine;
import cern.jet.random.engine.RandomGenerator;
import cern.jet.stat.Probability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created by KhEquipment on 2/15/15.
 */
public class LikelihoodEstimation {
    public static void estimate() {
        double lambda = 0.4d;
        double mean = 1.2d;
        double std = 3d;
        double min = 0d;
        double max = 7d;
        int size = 200;

        Normal normal = new Normal(mean,std,new MersenneTwister(new Date()));
        Uniform uniform = new Uniform(min,max,new MersenneTwister(new Date()));
        List<Double> nvalues = new ArrayList<Double>();
        for (int i = 0; i < size; i++) {
            nvalues.add(normal.nextDouble());
        }
        List<Double> uvalues =  new ArrayList<Double>();
        for (int j = 0; j < size; j++) {
            uvalues.add(uniform.nextDouble());
        }

        Function<Collection<Double>,Double> ml1 = new LikelihoodFunction(normal::pdf);
        Function<Collection<Double>,Double> ml2 = new LikelihoodFunction(uniform::pdf);
        System.out.println("norm vs norm "+ml1.apply(nvalues));
        System.out.println("norm vs unif "+ml1.apply(uvalues));
        System.out.println("unif vs norm "+ml2.apply(nvalues));
        System.out.println("unif vs unif "+ml2.apply(uvalues));

    }
}
