package data_export_feature;

public class DataExportFeature {

    public static void main(String[] args) {
        ReportExporter exporter = new SalesReportExporter();
        String reportName = "Quarterly Sales Report";

        System.out.println("Data Export Feature");
        System.out.println("===================");

        exporter.exportToCSV(reportName);
        exporter.exportToPDF(reportName);
        exporter.exportToJSON(reportName);
    }
}
