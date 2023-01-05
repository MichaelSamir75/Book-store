package com.DBO;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;

public class ShowReport {

    private Connection connection;

    public ShowReport() {
        connection = DBConnection.createConnection();
    }

    public void runTotalSales() {
        try {
            String reportpath = "TotalSalesForBooks.jrxml";
            JasperReport JR = JasperCompileManager.compileReport(reportpath);
            JasperPrint Jp = JasperFillManager.fillReport(JR,null,connection);
            JasperViewer.viewReport(Jp,false);
        }catch (JRException e){
            System.out.println(e.getMessage());
        }
    }
    public void runTop5() {
        try {
            String reportpath = "Top5CustomersPurchaseTheMost.jrxml";
            JasperReport JR = JasperCompileManager.compileReport(reportpath);
            JasperPrint Jp = JasperFillManager.fillReport(JR,null,connection);
            JasperViewer.viewReport(Jp,false);
        }catch (JRException e){
            System.out.println(e.getMessage());
        }
    }
    public void runTop10() {
        try {
            String reportpath = "Top10SellingBooks.jrxml";
            JasperReport JR = JasperCompileManager.compileReport(reportpath);
            JasperPrint Jp = JasperFillManager.fillReport(JR,null,connection);
            JasperViewer.viewReport(Jp,false);

        }catch (JRException e){
            System.out.println(e.getMessage());
        }
    }

}
