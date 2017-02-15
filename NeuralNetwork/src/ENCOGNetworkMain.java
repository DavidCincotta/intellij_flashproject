import org.encog.engine.network.activation.*;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.data.versatile.NormalizationHelper;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.normalizers.strategies.BasicNormalizationStrategy;
import org.encog.ml.data.versatile.normalizers.strategies.NormalizationStrategy;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.train.MLTrain;
import org.encog.neural.freeform.FreeformNetwork;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.EncogUtility;

import java.io.File;
import java.io.IOException;
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
            CIRCLE_INPUT[i][0] = x/5;// divide by five to geet data between -1 and 1 to normalize(?) it
            CIRCLE_INPUT[i][1] = y/5;// 5 is max value
            if (Math.hypot(x,y)>Math.sqrt(5)){
                CIRCLE_IDEAL[i][0] = -1;
            }else{
                CIRCLE_IDEAL[i][0] = 1;
            }

        }

        /*
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.board[1][1].click(minesweeper.board);
        while (minesweeper.gameActive){
            int x = minesweeper.getInt("X; ");
            int y = minesweeper.getInt("Y; ");
            minesweeper.board[x][y].click(minesweeper.board);
            if (minesweeper.backgroundBoard[x+1][y+1] == 9) minesweeper.gameActive = false;
            minesweeper.printBoard();
        }
        minesweeper.explorationFittness();
        */
        BasicNetwork basicNetwork = new BasicNetwork();
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),false,10));
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),false,10));
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),false,10));
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),false,10));
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(),false,1));
        basicNetwork.getStructure().finalizeStructure();basicNetwork.reset();
        FreeformNetwork network = new FreeformNetwork(basicNetwork);
        network.reset();

        //CalculateScore score = new TrainingSetScore(trainingSet);
        //BasicPopulation population = new BasicPopulation(10, new DoubleArrayGenomeFactory(100));
        //MLTrain train = new NeuralGeneticAlgorithm(, score, population.getPopulationSize());
        //MLTrain train = new FreeformResilientPropagation(network,trainingSet);
        //train.addStrategy(new StopTrainingStrategy(.001,30));
        //int epoch = 0;
        //while(train.getError() > .0001){
        //    train.resume(new TrainingContinuation());
        //    train.iteration();
        //    System.out.println("Epoch #" + epoch + " Error:" + train.getError());
        //    epoch++;
        //}
        DataParse data = new DataParse("NeuralNetwork/data/poker-hand-training-true.data");
        DataParse testData = new DataParse("NeuralNetwork/data/poker-hand-testing.data");
        MLDataSet trainingSet = new BasicMLDataSet(data.input,data.ideal);
        //MLDataSet testingSet = new BasicMLDataSet(testData.input, data.ideal);
        //MLDataSet trainingSet = new BasicMLDataSet(CIRCLE_INPUT,CIRCLE_IDEAL);
        MLTrain train = new ResilientPropagation(basicNetwork,trainingSet);
        EncogUtility.trainToError(train,.01);
        //EncogUtility.trainToError(network,trainingSet,.01);
        //System.out.println("Network Training Results:");
        //EncogUtility.evaluate(basicNetwork,trainingSet);

        double[] input = {.2,.2,.2,.2,.2,.2,.2,.2,.2,.2};
        double[] output = new double[1];
        System.out.println(network.compute(new BasicMLData(input)));

        /*
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
        System.out.println(new File("../../downloads/Cats/cats0.jpg").getName());
        ImageMLDataSet imageTraining = new ImageMLDataSet(new SimpleIntensityDownsample(), false, 1, -1);
        for (int i = 0; i < size; i++) {
            files[i] = new File("../../downloads/Cats/cats"+i+".jpg");
            Image img = ImageIO.read(files[i]);
            double[] idealOutput = {1};
            imageTraining.add(new ImageMLData(img),new BasicMLData(idealOutput));
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
        network.addLayer(new BasicLayer(null,false,2500));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 100));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 20));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 5));
        network.addLayer(new BasicLayer(new ActivationSigmoid() ,false ,1));
        network.getStructure().finalizeStructure();
        network.reset();
        //MLDataSet trainingSet = new BasicMLDataSet(CIRCLE_INPUT, CIRCLE_IDEAL);
        //org.encog.neural.networks.training.
        MLTrain train = new ResilientPropagation(network,imageTraining);
        //train.addStrategy(new RequiredImprovementStrategy(.001,30));
        //System.out.println(train.getStrategies());

        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
            //} while( train.getError () > 0.01) ;
        }while( epoch <= 100) ;

        train.finishTraining();

        double[] input = new double[2500];
        for (int i = 0; i < input.length; i++) {
            input[i] = Math.random()*2-1;
        }
        double[] output = new double[1];


        network.compute(input,output);
        System.out.println(output[0]);
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
