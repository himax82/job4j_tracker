package ru.job4j.tracker.oop;

public class ReportUsage {

    public static void main(String[] args) {
        JSONReport report = new JSONReport();
        System.out.println(report.generate("name", "body"));
    }

}
