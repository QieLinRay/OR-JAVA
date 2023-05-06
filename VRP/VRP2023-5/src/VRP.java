import ilog.concert.*;
import ilog.cplex.CpxLinearExpr;
import ilog.cplex.CpxLinkedExpr;
import ilog.cplex.IloCplex;
import java.io.*;
public class VRP {
    public void run() throws IloException {
        IloCplex model=new IloCplex();
        //variables

        IloNumVar[][] gama=new IloNumVar[data.N+2][];
        IloNumVar[][] alpha=new IloNumVar[data.N+2][];
        IloNumVar[][][] beta=new IloNumVar[data.N+2][data.N+2][data.K];

        for(int i=0;i<data.N+2;i++){
            gama[i]=new IloNumVar[data.K];
            for(int k=0;k<data.K;k++){
                gama[i][k]=model.numVar(0,Float.MAX_VALUE);
            }
        }

        for(int i=0;i<data.N+2;i++){
            alpha[i]=new IloNumVar[data.K];
            for(int k=0;k<data.K;k++){
                alpha[i][k]=model.boolVar();
            }
        }

        //IloNumVar[][][] beta=new IloNumVar[data.N+2][data.N+2][];
        for(int i=0;i<data.N+2;i++){
            for(int j=0;j<data.N+2;j++){
                for (int k = 0; k < data.K; k++) {
                    beta[i][j][k]=model.boolVar();
                }
            }
        }

        /*CpxLinkedExpr cplexExpr= model.CpxLinearExpr();
        IloLinearNumExpr obj = cplexExpr.toNumExpr();*/
       /* IloLinearNumExpr[] obj = new IloLinearNumExpr[10];
        obj[0]= model.linearNumExpr();*/
        IloIntExpr obj = model.linearIntExpr();
        for(int k=0;k<data.K;k++){
            for(int n=0;n<data.N+1;n++){
                for(int n1=0;n1<data.N+2;n1++){
                    if(beta[n][n1][k]!=null) {
                        obj = (IloIntExpr) model.sum(obj, model.prod(data.T_n_n1[n][n1],
                                beta[n][n1][k]));
                    }
                }
            }
        }
        model.addMinimize(obj);

        //IloLinearNumExpr[] expr1=new IloLinearNumExpr[10];
        //IloIntExpr expr1 = model.linearIntExpr();
        for(int n=1;n<data.N+1;n++){
            IloIntExpr expr1 = model.linearIntExpr();
            //IloLinearNumExpr expr1 = model.linearNumExpr();
            for(int k=0;k<data.K;k++){
                expr1= (IloIntExpr) model.sum(expr1,alpha[n][k]);
            }
            model.addEq(expr1,1);
        }
        if(model.solve()){
            System.out.println("ok");
        }


    }



}
