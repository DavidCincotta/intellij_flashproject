import java.util.ArrayList;

public class IntegralEstimator {
    public IntegralEstimator(int sections, double low, double high) {
        System.out.println("Tram area: "+ tRam(sections,low,high));
        System.out.println("Rram area: "+ rRam(sections,low,high));
        System.out.println("Lram area: "+ lRam(sections,low,high));
        System.out.println("Mram area: "+ mRam(sections,low,high));
        System.out.println("Rram and Lram average: "+randlram(sections,low,high));
    }

    public double f(double x){
        return x;
    }
    public double tRam(int sections, double low, double high){
        double rectangeArea = 0;
        double deltaX = Math.abs((high - low)/ ((double)(sections)));
        ArrayList<Double> fxArray = new ArrayList<>();
        for (double i = low; i <= high; i+=deltaX) {
            fxArray.add(f(i));
        }
        for (int i = 0; i <fxArray.size()-1; i++) {
            double a = fxArray.get(i);
            double b = fxArray.get(i+1);
            rectangeArea += (a+b)*deltaX/2;
        }
        return rectangeArea;
    }
    public double lRam(int sections, double low, double high){
        double rectangeArea = 0;
        double deltaX = Math.abs((high - low)/ ((double)(sections)));
        ArrayList<Double> fxArray = new ArrayList<>();
        for (double i = low; i < high; i+=deltaX) {
            fxArray.add(f(i));
        }
        for (int i = 1; i <fxArray.size(); i++) {
            double a = fxArray.get(i);
            rectangeArea += (a)*deltaX;
        }
        return rectangeArea;
    }
    public double rRam(int sections, double low, double high){
        double rectangeArea = 0;
        double deltaX = Math.abs((high - low)/ ((double)(sections)));
        ArrayList<Double> fxArray = new ArrayList<>();
        for (double i = low; i <= high; i+=deltaX) {
            fxArray.add(f(i));
        }
        for (int i = 0; i <=fxArray.size()-1; i++) {
            double a = fxArray.get(i);
            rectangeArea += (a)*deltaX;
        }
        return rectangeArea;
    }
    public double mRam(int sections, double low, double high){
        double rectangeArea = 0;
        double deltaX = Math.abs((high - low)/ ((double)(sections)));
        ArrayList<Double> fxArray = new ArrayList<>();
        for (double i = low+deltaX/2; i <= high; i+=deltaX) {
            fxArray.add(f(i));
        }
        for (double a: fxArray) {
            rectangeArea += (a)*deltaX;
        }
        return rectangeArea;
    }
    public double randlram(int sections, double low, double high){
        return ((rRam(sections, low, high)+ lRam(sections, low, high))/2);
    }
}
