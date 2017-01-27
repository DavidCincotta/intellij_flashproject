import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.MLMethod;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.ml.train.strategy.Greedy;
import org.encog.ml.train.strategy.RequiredImprovementStrategy;
import org.encog.ml.train.strategy.Strategy;
import org.encog.ml.train.strategy.end.EarlyStoppingStrategy;
import org.encog.neural.flat.FlatNetwork;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.platformspecific.j2se.data.image.ImageMLData;
import org.encog.platformspecific.j2se.data.image.ImageMLDataSet;
import org.encog.util.downsample.Downsample;
import org.encog.util.downsample.RGBDownsample;
import org.encog.util.downsample.SimpleIntensityDownsample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class NetworkMain {
    public static double XOR_INPUT[][] = { { 0.0, 0.0 },
            { 1.0, 0.0 },
            { 0.0, 1.0 },
            { 1.0, 1.0 } };
    public static double XOR_IDEAL [ ] [ ] = { { 0.0 },
            { 1.0 },
            { 1.0 },
            { 0.0 } };
    public static int length = 500;

    public static double CIRCLE_INPUT[][] = new double[length][2];
    public static double CIRCLE_IDEAL[][] = new double[length][1];
    public static void main(String[] args) throws IOException{
        for (int i = 0; i < length; i++) {
            Random random = new Random(69);
            boolean signx = random.nextBoolean();
            boolean signy = random.nextBoolean();
            double signX,signY;
            if (signx){
                signX = 1;
            }else{
                signX = -1;
            }
            if (signy){
                signY= 1;
            }else {
                signY = -1;
            }
            double x = signX*Math.random()*5;
            double y = signY*Math.random()*5;
            CIRCLE_INPUT[i][0] = x;
            CIRCLE_INPUT[i][1] = y;
            if (Math.hypot(x,y)>Math.sqrt(5)){
                CIRCLE_IDEAL[i][0] = 0;
            }else{
                CIRCLE_IDEAL[i][0] = 1;
            }

        }



        int size = 5;
        File[] files = new File[size];
        System.out.println(new File("../../downloads/Cats/cats0.jpg"));
        ImageMLDataSet imageTraining = new ImageMLDataSet(new RGBDownsample(), false, 1, -1);
        for (int i = 0; i < size; i++) {
            files[i] = new File("../../downloads/Cats/cats"+i+".jpg");
            Image img = ImageIO.read(files[i]);
            double[] idealOutput = {1};
            imageTraining.add(new BasicMLDataPair(new ImageMLData(img),new BasicMLData(idealOutput)));
        }
        for (MLDataPair a: imageTraining.getData()
        ) {
            for (double b  :
            a.getInput().getData()    ) {
                System.out.print(b);
            }
            System.out.println("");
        }
        imageTraining.downsample(50,50);

        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null,false,imageTraining.getHeight()*imageTraining.getWidth()));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, 100));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, 20));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, 5));
        network.addLayer(new BasicLayer(new ActivationTANH() ,false ,1));
        network.getStructure().finalizeStructure();
        network.reset();
        MLDataSet trainingSet = new BasicMLDataSet(CIRCLE_INPUT, CIRCLE_IDEAL);
        //org.encog.neural.networks.training.
        MLTrain train = new ResilientPropagation(network,imageTraining);
        train.addStrategy(new RequiredImprovementStrategy(.001,30));
        System.out.println(train.getStrategies());

        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
            //} while( train.getError () > 0.01) ;
        }while( epoch <= 1000) ;

        train.finishTraining();

        //double[] input = {1,0};
        //double[] output = new double[1];


        //network.compute(input,output);
        //System.out.println(output[0]);
        //System.out.println(network.dumpWeights());
        System.out.println(network.dumpWeights().split(",").length);

        double[][] weights = new double[network.getLayerCount()][];
        String[] weightsString = (network.dumpWeights()).split(",");
        for (int i = 0; i < weightsString.length; i++) {

        }
        for (int i = 0; i < weights.length; i++) {
            weights[i] = new double[network.getLayerNeuronCount(i)];
            for (int j = 0; j < weights[i].length; j++) {

            }
        }

        /*
        BasicNetwork copyNetworkFromWeights = new BasicNetwork() ;
        network.addLayer(new BasicLayer(null,false,2));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 100));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 100));
        network.addLayer(new BasicLayer(new ActivationSigmoid() ,false ,1));
        network.getStructure().finalizeStructure();
        network.reset();
        */

        //copyNetworkFromWeights.addWeight();


        /*
        System.out.println("Neural Network Results:");
        for (MLDataPair pair : trainingSet ) {
            final MLData output = network.compute( pair.getInput() ) ;
            System.out.println ( pair.getInput() . getData(0) + "," + pair.getInput().getData(1)
                    + " , actual=" + output.getData(0) + " , ideal=" + pair.getIdeal().getData (0) ) ;
        }
           goatse
        */


    }
}
