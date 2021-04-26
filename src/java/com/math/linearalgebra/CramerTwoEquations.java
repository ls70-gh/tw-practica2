/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.math.linearalgebra;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Otero
 */
@WebServlet(name = "CramerTwoEquations", urlPatterns = {"/CramerTwoEquations"})
public class CramerTwoEquations extends HttpServlet {


    
    /**
     * Obtener el determinante de un sistema de dos ecuaciones
     *
     * @param columnVector1         el vector asociado con la primer variable
     * @param columnVector2         el vector asociado con la segunda variable
     */
    private double determinant2x2(double[] columnVector1, double[] columnVector2) {
        return columnVector1[0]*columnVector2[1] - columnVector1[1]*columnVector2[0];
    }
    
     /**
     * Obtener la solucion de una variable en un sistema de dos ecuaciones
     *
     * @param coefficientMatrix     la matriz de coeficientes donde estan los coeficientes de las variables
     * @param constantVector        el vector de constantes a los que se iguala la matriz de coeficientes
     */
    private double cramer2x2(double[][] coefficientMatrix, double[] constantVector, int variableToSolve) {
        return determinant2x2(variableToSolve==0?constantVector:coefficientMatrix[0],variableToSolve==1?constantVector:coefficientMatrix[1])/determinant2x2(coefficientMatrix[0],coefficientMatrix[1]);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double[][] coefficientMatrix;
        double[] constantVector;
        coefficientMatrix = new double[2][2];
        constantVector = new double[2];
        for(int i = 0;i<2;i++) {
            //LHS: Mano Izquierda de la ecuacion
            for(int j=0;j<2;j++) {
                coefficientMatrix[i][j] = Integer.parseInt(request.getParameter(String.valueOf(i+1)+String.valueOf(j)));
            }
            //RHS: Mano Derecha de la ecuacion
            constantVector[i] = Integer.parseInt(request.getParameter("0"+String.valueOf(i)));
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CramerTwoEquations</title>");            
            out.println("</head>");
            out.println("<body>");
            double answerX = cramer2x2(coefficientMatrix, constantVector, 0);
            double answerY = cramer2x2(coefficientMatrix, constantVector, 1);
            out.println("X: " + answerX + "<br>");
            out.println("Y: " + answerY + "<br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
