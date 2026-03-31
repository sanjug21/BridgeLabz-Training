package data_export_feature;

class SalesReportExporter implements ReportExporter {

    @Override
    public void exportToCSV(String reportName) {
        System.out.println("Exporting " + reportName + " to CSV...");
    }

    @Override
    public void exportToPDF(String reportName) {
        System.out.println("Exporting " + reportName + " to PDF...");
    }
}
