import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class ConwaysGameOfLife extends JPanel {

    private int[][] grid;
    private static final Random rnd = new Random();
    private int generationCounter;

    public ConwaysGameOfLife(int width, int height) {
        this.grid = new int[width / 4][height / 4];
        gliderGun();
    }

    private void setupGrid() {
        for (int[] row : grid) {
            for (int j = 0; j < row.length; j++) {
                if (rnd.nextDouble() < 0.92)
                    continue;
                row[j] = rnd.nextInt(2);
            }
        }
    }

    private void gliderGun(){
        for(int i=0;i<grid.length;i++){
            for(int j=0; j<grid[i].length; j++){
                if(j==26 && i==2){
                    grid[i][j]=1;
                }
                if(i==3){
                    if(j==24){
                        grid[i][j]=1;
                    }
                    if(j==26){
                        grid[i][j]=1;
                    }
                }

                if(i==4){
                    if(j==14){
                        grid[i][j]=1;
                    }
                    if(j==15){
                        grid[i][j]=1;
                    }
                    if(j==22){
                        grid[i][j]=1;
                    }
                    if(j==23){
                        grid[i][j]=1;
                    }
                    if(j==36){
                        grid[i][j]=1;
                    }
                    if(j==37){
                        grid[i][j]=1;
                    }

                }

                if(i==5){
                    if(j==13){
                        grid[i][j]=1;
                    }
                    if(j==17){
                        grid[i][j]=1;
                    }
                    if(j==22){
                        grid[i][j]=1;
                    }
                    if(j==23){
                        grid[i][j]=1;
                    }
                    if(j==36){
                        grid[i][j]=1;
                    }
                    if(j==37){
                        grid[i][j]=1;
                    }

                }

                if(i==6){
                    if(j==2){
                        grid[i][j]=1;
                    }
                    if(j==3){
                        grid[i][j]=1;
                    }
                    if(j==12){
                        grid[i][j]=1;
                    }
                    if(j==18){
                        grid[i][j]=1;
                    }
                    if(j==22){
                        grid[i][j]=1;
                    }
                    if(j==23){
                        grid[i][j]=1;
                    }

                }
                if(i==7){
                    if(j==2){
                        grid[i][j]=1;
                    }
                    if(j==3){
                        grid[i][j]=1;
                    }
                    if(j==12){
                        grid[i][j]=1;
                    }
                    if(j==16){
                        grid[i][j]=1;
                    }
                    if(j==18){
                        grid[i][j]=1;
                    }
                    if(j==19){
                        grid[i][j]=1;
                    }
                    if(j==24){
                        grid[i][j]=1;
                    }
                    if(j==26){
                        grid[i][j]=1;
                    }

                }

                if(j==12 && i==8){
                    grid[i][j]=1;
                }
                if(j==18 && i==8){
                    grid[i][j]=1;
                }
                if(j==26 && i==8){
                    grid[i][j]=1;
                }
                if(j==13 && i==9){
                    grid[i][j]=1;
                }
                if(j==17 && i==9){
                    grid[i][j]=1;
                }
                if(j==14 && i==10){
                    grid[i][j]=1;
                }
                if(j==15 && i==10){
                    grid[i][j]=1;
                }


            }
        }

    }

    public void updateGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                applyRule(i, j);
            }
        }
    }


    private void applyRule(int i, int j) {
        int left = 0, right = 0, up = 0, down = 0;
        int dUpperLeft = 0, dUpperRight = 0, dLowerLeft = 0, dLowerRight = 0;

        if (j < grid.length - 1) {
            right = grid[i][j + 1];
            if(i>0)
                dUpperRight = grid[i - 1][j + 1];
            if (i < grid.length - 1)
                dLowerRight = grid[i + 1][j + 1];
        }

        if (j > 0) {
            left = grid[i][j - 1];
            if (i > 0)
                dUpperLeft = grid[i - 1][j - 1];
            if (i< grid.length-1)
                dLowerLeft = grid[i + 1][j - 1];
        }

        if (i > 0)
            up = grid[i - 1][j];
        if (i < grid.length - 1)
            down = grid[i + 1][j];

        int sum = left + right + up + down + dUpperLeft + dUpperRight
                + dLowerLeft
                + dLowerRight;

        if (grid[i][j] == 1) {
            if (sum < 2)
                grid[i][j] = 0;
            if (sum > 3)
                grid[i][j] = 0;
        }

        else {
            if (sum == 3)
                grid[i][j] = 1;
        }

    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(grid.length * 4, grid[0].length * 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.drawString("Generation: " + generationCounter++, 0, 10);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.red);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
            }
        }

        g.setColor(gColor);
    }

    public static void main(String[] args) {
        final ConwaysGameOfLife c = new ConwaysGameOfLife(800, 800);
        JFrame frame = new JFrame();
        frame.getContentPane().add(c);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c.updateGrid();
                c.repaint();
            }
        }).start();
    }
}