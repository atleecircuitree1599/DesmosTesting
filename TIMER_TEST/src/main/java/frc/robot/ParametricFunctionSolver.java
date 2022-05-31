// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/** Add your docs here. */
public class ParametricFunctionSolver {

    private double[][] allCoordinates = 
  {{5,1, 0}, {2,-9, 1}, {-3,-3, 2}, {-10,10, 3}};


  private SimpleMatrix A = new SimpleMatrix(4, 4);
  private SimpleMatrix B = new SimpleMatrix(4, 1);
  private SimpleMatrix C = new SimpleMatrix(4,2);
    


  public ParametricFunctionSolver() {  

        for(int i = 0; i < allCoordinates.length; i++){

            for(int j = 0; j < allCoordinates.length; j++){
                A.set(i, j, Math.pow(allCoordinates[i][2], j));
            }
               
        }
    
        
        for(int i = 0; i < 2; i++){
            
            for(int j = 0; j < allCoordinates.length; j++){
                B.set(j, 0, allCoordinates[j][i]);
            }
           SimpleMatrix invA = A.invert();

           C.insertIntoThis(0, i, invA.mult(B)); 
        }


        SmartDashboard.putNumber("AX", C.get(0, 0));
        SmartDashboard.putNumber("BX", C.get(1, 0));
        SmartDashboard.putNumber("CX", C.get(2, 0));
        SmartDashboard.putNumber("DX", C.get(3, 0));
        
        SmartDashboard.putNumber("AY", C.get(0, 1));
        SmartDashboard.putNumber("BY", C.get(1, 1));
        SmartDashboard.putNumber("CY", C.get(2, 1));
        SmartDashboard.putNumber("DY", C.get(3, 1));

        //Opening Desmos

        
        try {

            URI desmosURI = new URI("https://dashguy999.github.io/DesmosTesting/");

            
            String queryString = "value= 3t^3 /+ 2t^2";

            //SmartDashboard.putString("desmosURI.getQuery before", desmosURI.getQuery());

            String newQuery = desmosURI.getQuery();
            if (newQuery == null) {
                newQuery = queryString;
            } else {
                newQuery += "&" + queryString;  
            }
            
            URI newDesmosURI = new URI(desmosURI.getScheme(), desmosURI.getAuthority(),
            desmosURI.getPath(), newQuery, desmosURI.getFragment());

            SmartDashboard.putString("Generated URL", newDesmosURI.toString());
            SmartDashboard.putString("desmosURI.getQuery after", newDesmosURI.getQuery());
            
            Desktop.getDesktop().browse(newDesmosURI);
            


        } 
        catch (URISyntaxException e) {
            SmartDashboard.putString("ERROR", "Something Went Wrong (catch1)");
        }
        catch(IOException e){
            SmartDashboard.putString("ERROR", "Something Went Wrong (catch2)");
        }
        
        

        

    }

}