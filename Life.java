import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.Random;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Life extends JPanel{
    private static int[][] grid;
    public static int cols;
    public static int rows;
    private static final Random rand = new Random();
    public static int count=0;
    
    
    public Life(int r, String type){
        cols= r  ;
        rows= r ;

        this.grid= new int[cols/4][rows/4];
        if (type.equals("R") || type.equals("r")){
            randomGrid();
        }
        else if(type.equals("G") || type.equals("g")){
            gliderGun();
        }
        
    }

    private void randomGrid() {
        for (int[] row : grid) {
            for (int j = 0; j < row.length; j++) {
                
                row[j] = rand.nextInt(2);
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
    public void updateGrid(){
        for(int i=0; i<grid.length-1; i++){
            for (int j=0; j< grid[i].length-1; j++){
                applyRule(i,j);
            }
        }
    }

    public void applyRule(int i, int j){
       
        int sum=0;
        int[][] newGrid;
        newGrid=grid;

                if(i==0 || i==cols-1 || j==0|| j== rows-1){
                    grid[i][j]= newGrid[i][j];
                }
                else{
                    sum+= grid[i-1][j-1];
                    sum+= grid[i][j-1];
                    sum+= grid[i+1][j-1];
                    sum+= grid[i+1][j];
                    sum+= grid[i+1][j+1];
                    sum+= grid[i][j+1];
                    sum+= grid[i-1][j+1];
                    sum+= grid[i-1][j];
                    
                }

                if(grid[i][j]==1 && sum<2){
                    grid[i][j]=0;
                }

                if(grid[i][j]==1 && sum>3){
                    grid[i][j]=0;
                }
                if(grid[i][j]==0 & sum==3){
                    grid[i][j]=1;
                }
                if(grid[i][j]==1 && (sum==2 || sum==3)){
                    grid[i][j]=grid[i][j];
                }



                


            
        

    }

    public Dimension getPreferredSize() {
        return new Dimension(grid.length*10, grid[0].length *10 );
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

      
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.black);
                    g.fillRect(j * 10, i * 10, 10, 10);
                }
                if(grid[i][j]==0){
                    g.setColor(Color.WHITE);
                    g.fillRect(j * 10, i * 10, 10, 10);
                }
            }
        }

        g.setColor(gColor);
    }


    public static void main (String[] args){
        int size= Integer.parseInt(args[0]);
        int iterations= Integer.parseInt(args[1]);
        String type= args[2];
        int d= size *10;
        final Life l= new Life(size*10,type);
        String s= Integer.toString(d);

        JFrame frame= new JFrame(s +" by " + s);
        frame.setSize(size*10,size*10);
        frame.getContentPane().add(l);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        new Timer(100, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (count<iterations){
                    l.updateGrid();
                    l.repaint();
                    count++;
                }
            }

        }).start();
    }   
}

