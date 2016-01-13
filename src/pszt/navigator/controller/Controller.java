package pszt.navigator.controller;

import pszt.navigator.AStar;
import pszt.navigator.Graph;
import pszt.navigator.Node;
import pszt.navigator.view.View;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

/**
 * Created by Marcin on 2015-12-30.
 */
//klasa łaczy w sumie funkcjonalność kotrolera i modelu - trzyma niezbedne dane, takie jak parametry dla grafu, astar itp
public class Controller
{
    private View view;
    private Graph graph;
    private AStar aAstarAlgorithm;

    private Vector<Node> solution;

    private Node start;
    private Node end;

    //parametry dla grafu, defaultowe wartosci
    private int numberOfNodes = 100;
    private double rate = 0.1;

    private char startEndSwich; //do zaznaczania pkt startowego i końcowego

    public Controller()
    {
        aAstarAlgorithm = new AStar();
    }

    public void setView(View view)
    {
        this.view = view;
    }

    public void generateNewGraph()
    {
        //prymitywne sprawdzenie poprawnosci danych
        if(view.getNumberOfNodes()!=0 && view.getRateValue()!=0)
        {
            numberOfNodes = view.getNumberOfNodes();
            rate = view.getRateValue();
        }

        graph = new Graph(numberOfNodes, rate);
        view.setGraph(graph);
        view.updateView();
    }

    public void solveUsingAStar()
    {
        graph.setStarter(start);
        graph.setFinish(end);
        if(graph != null && start != null && end != null)
        {

            //użycie algorytmu do rozwiązania zadania
            //TODO: 2015-12-30 aStarAlgorithm.setState,

            view.loadSolution(solution);
        }


        //aktualizacja widoku
        view.updateView();

        if(Desktop.isDesktopSupported())
        {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public char getStartEndSwich()
    {
        return startEndSwich;
    }

    public void resetStartEndSwich()
    {
        startEndSwich = 'N'; //N - none
        view.updateView();
    }

    public void setStartEndSwich(char startEndSwich)
    {
        this.startEndSwich = startEndSwich;
    }


    public void setEnd(Node end) {
        this.end = end;
    }


    public void setStart(Node start) {
        this.start = start;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
