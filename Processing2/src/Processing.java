
//doesn't create processing window. something wrong.

import processing.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Processing extends PApplet{

    PImage photo;
    int N;

    public void setup(){
        size(1400,420);
        photo = loadImage("mountain.jpg");
        photo.loadPixels();
        //Arrays.sort(photo.pixels);
        N = photo.pixels.length;
        //heapSort(photo.pixels);
        for(int i = 0; i<photo.pixels.length;i++){
            if(brightness(photo.pixels[i]) == 0.1 && i<700*420-100){
                int temp;
                for(int j = i; j<i+50; j++){
                    for(int k = i+1;k<i+49;k++){
                        if(brightness(photo.pixels[k-1])>brightness(photo.pixels[k])){
                            temp = photo.pixels[k-1];
                            photo.pixels[k-1] = photo.pixels[k];
                            photo.pixels[k] = temp;
                        }
                    }
                }
            }
        }
        //randomize();
        photo.updatePixels();
    }
    void randomize(){
        ArrayList pixels = new ArrayList<Integer>();
        for(int a: photo.pixels){
            pixels.add(a);
        }
        Collections.shuffle(pixels);
        for(int i = 0; i<pixels.size(); i++){
            photo.pixels[i] = color((int)(pixels.get(i)));
        }
    }
    public void draw(){
        image(photo,0,0);
        image(loadImage("mountain.jpg"),700,0);
    }


    void heapSort(int arr[])
    {
        heapify(arr);
        for (int i = N; i > 0; i--)
        {
            swap(0, i);
            N = N-1;
            maxheap( 0);
        }
    }
    // Function to build a heap
    void heapify(int arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(i);
    }
    // Function to swap largest element in heap
    void maxheap( int i)
    {
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && brightness(photo.pixels[left]) > brightness(photo.pixels[i]))
            max = left;
        if (right <= N && photo.pixels[right] > photo.pixels[max])
            max = right;

        if (max != i)
        {
            swap(i, max);
            maxheap(max);
        }
    }
    // Function to swap two numbers in an array
    void swap( int i, int j)
    {
        int tmp = photo.pixels[i];
        photo.pixels[i] = photo.pixels[j];
        photo.pixels[j] = tmp;
    }
}
