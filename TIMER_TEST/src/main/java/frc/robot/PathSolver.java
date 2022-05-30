// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class PathSolver {
    
  private static final double[] endChunk = {-1,-1};
  private static final double[] endPath = {-2,-2};

  private double[][] allCoordinates = 
  {{0,0}, {3,2}, {1,4}, {5,5}, endChunk,
  endPath};

  private double[][] chunk = new double[10][10];
  //output[chunk][X or Y][Coefficent]
  //output[chunk][X or Y][0] = U when chunk ends
  //For example, output[2][1][0] = 3rd chunk, Y function, 1st coeffecient
  private double[][][] output = new double[10][2][10];
  private int outputTicker = 0;


    public PathSolver(){


        
        //Run Matrix Solver for all chunks
        for(int u = 0; u < allCoordinates.length; u++){
            
            SmartDashboard.putString("Trip1", "1");

            if(allCoordinates[u] == endChunk){
          
                SmartDashboard.putString("Trip2", "2");

                //output[outputTicker][0][0] = u;
                //output[outputTicker][1][0] = u;
  
                SimpleMatrix AmatrixConstructor = new SimpleMatrix(chunk.length, chunk.length);
                SimpleMatrix BmatrixConstructor = new SimpleMatrix(chunk.length, 1);
  
                Matrix A = new Matrix<>(AmatrixConstructor);
                Matrix B = new Matrix<>(BmatrixConstructor);
  
                //run X and Y solvers for a single chunk
                for(int XorY = 0; XorY <= 1; XorY++){
                    SmartDashboard.putString("Trip3", "3");
                    //For every row in Matrix A
                    for(int row = 0; row < A.getNumRows(); row++){
                        SmartDashboard.putString("Trip4", "4");
                        //for every column in Matrix A
                        for(int column = 0; column < A.getNumRows(); column++){
                            SmartDashboard.putString("Trip5", "5");
                            //Set the number in there
                            A.set(row, column, Math.pow(chunk[row][2], column));
                        }
  
                    }
  
                //for every row in Matrix B
                for(int row = 0; row < B.getNumRows(); row++){
                    //Set the number in there
                    B.set(row, 0, chunk[row][XorY]);
                }
            
                SmartDashboard.putNumberArray("Matrix A", A.getData());

                //Matrix invA = A.inv();
  
                //Matrix C = invA.times(B);
                Matrix C = A;
  
                //for every row in Matrix C
                for(int row = 0; row < C.getNumRows(); row++){
                    //Set the number to the output
                    output[outputTicker][XorY][row] = C.get(row, 0);
                }
  
            }
  
          outputTicker++;
          return;
        }
  
        //U imprint
        chunk[u][0] = allCoordinates[u][0];
        chunk[u][1] = allCoordinates[u][1];
        chunk[u][2] = u;
  
        if(allCoordinates[u] == endPath){
          break;
        }
        SmartDashboard.putString("output", output.toString());
        SmartDashboard.putNumber("u", u);
      }

      //SmartDashboard.putString("output", output.toString());

    }



}
