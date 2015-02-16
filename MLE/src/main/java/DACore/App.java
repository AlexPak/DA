package DACore;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.stat.Probability;
import com.github.plot.Plot;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        plot();
        LikelihoodEstimation.estimate();
    }

    public static void plot() throws IOException {
        double mean = 1.2d;
        double std = 3d;
        double start = -4d;
        double delta = 0.01;
        Normal normal = new Normal(mean,std, new MersenneTwister(new Date()));
        List<Double> x = new ArrayList<Double>();
        List<Double> y = new ArrayList<Double>();
        for(int i = 0; i < 10000; i++) {
            x.add(start + i * delta);
            y.add(normal.pdf(start + i * delta));
        }
        Plot plot = Plot.plot(Plot.plotOpts().
                title("Normal").
                legend(Plot.LegendFormat.BOTTOM)).
                xAxis("x", Plot.axisOpts().
                        range(start, 10)).
                yAxis("y", Plot.axisOpts().
                        range(0, 5)).series("data", Plot.data().xy(x,y),Plot.seriesOpts().
                marker(Plot.Marker.NONE).
                markerColor(Color.GREEN).
                color(Color.BLACK))
       ;
        plot.save("normal","png");
    }
}
