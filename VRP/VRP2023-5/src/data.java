import java.util.Random;
public class data {
    static int K=10;
    static int N=10;
    static int Q=100;
    static int[]Qn=new int[N];
    static int[][]T_n_n1=new int[N+2][N+2];

   data(){
        Random random=new Random(1);
        for(int i=0;i<N;i++){
            Qn[i]=random.nextInt(30);
        }
       //static int[][]T_n_n1=new int[N+2][];
        for(int i=0;i< T_n_n1.length;i++){
            //T_n_n1[i]=new int[N+2];
            for(int j=0;j<T_n_n1[i].length;j++){
                //int tempt=random.nextInt(100)+5;

                T_n_n1[i][j]=random.nextInt(6)+5;
                //T_n_n1[j][i]=tempt;
            }
        }
    }

}
