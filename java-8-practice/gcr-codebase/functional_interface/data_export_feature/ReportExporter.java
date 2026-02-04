package data_export_feature;

public interface ReportExporter {
    void exportToCSV(String reportName);
    void exportToPDF(String reportName);

    default void exportToJSON(String reportName) {
        System.out.println("JSON export not customized. Generating default JSON for: " + reportName);
    }
}
