package eval.construction.construction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.web.multipart.MultipartFile;

public class Constante {
    public static Time toTimeOfTimestamp(Timestamp timestamp) {
        long timeInMillis = timestamp.getTime();
        Time time = new Time(timeInMillis);
        return time;
    }

    public static Timestamp stringToTimestamp(String timestampString)
    throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        java.util.Date parsedDate = sdf.parse(timestampString);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());
        return timestamp;
    }

    public static String getHeureMinuteOfTimestamp(Timestamp timestamp) {
        return timestamp.getHours()+":"+timestamp.getMinutes();
    }

    public static String getDateOfTimestamp(Timestamp timestamp) {
        String annee = String.valueOf(timestamp.getYear() + 1900);
        String mois = String.valueOf(timestamp.getMonth() + 1) ; 
        String jour = String.valueOf(timestamp.getDate());
        while(annee.length()!=4) {
            annee="0"+annee;
        }
        while(mois.length()!=2) {
            mois="0"+mois;
        }
        while(jour.length()!=2) {
            jour="0"+jour;
        }
        return annee+"-"+mois+"-"+jour;
    }

    public static String getHTMLPDFTemplate(String contenu, String baseUrl, double widthSize) {
        return "<!DOCTYPE html>"+
                "<html lang=\"en\">"+
                    "<head>"+
                        "<meta charset=\"UTF-8\"/>"+
                        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>"+
                        "<title>Skydash Admin</title>"+
                        "<link rel=\"stylesheet\" href=\""+baseUrl+"/assets/css/pdf.css\"/>"+
                    "</head>"+
                    "<body>"+
                        "<div  style=\"width : "+widthSize+"px;\">"+
                            contenu+
                        "</div>"+
                    "</body>"+
                "</html>";
    }

    public static Date dateUtilToDateSql(java.util.Date dateUtil) {
        return new Date(dateUtil.getTime());
    }

    public static Timestamp dateUtilToTimestampSql(java.util.Date dateUtil) {
        return new Timestamp(dateUtil.getTime());
    }

    public static Time dateUtilToTimeSql(java.util.Date dateUtil) {
        return new Time(dateUtil.getTime());
    }

    // public static List<String[]> readCSVFile(MultipartFile file) 
    // throws IOException {
    //     List<String[]> dataList = new ArrayList<>();
    //     int lineNumber=0;
    //     try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             String[] data = line.split(",");
    //             if(lineNumber!=0) {
    //                 dataList.add(data);
    //             }
    //             lineNumber++;
    //         }
    //     }
    //     return dataList;
    // }

    public static List<String[]> readCSVFile(MultipartFile file) 
    throws IOException, CsvValidationException {
        List<String[]> dataList = new ArrayList<>();
        int lineNumber = 0;
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        // Créer un CSVReaderBuilder en utilisant le CSVParser
        CSVReaderBuilder readerBuilder = new CSVReaderBuilder(new InputStreamReader(file.getInputStream()));
        readerBuilder.withCSVParser(parser);

        // Créer un CSVReader à partir du CSVReaderBuilder
        CSVReader reader = readerBuilder.build();
        try  {
            String[] line;
            
            while ((line = reader.readNext()) != null) {
                if (lineNumber != 0) { 
                    dataList.add(line);
                }
                for(int i=0; i<line.length; i++) {
                    System.out.println(line[i]);
                }
                System.out.println();
                lineNumber++;
            }
        } catch(Exception e) {
            throw e;
        }
        
        return dataList;
    }
    // public static List<String[]> readCSVFile(MultipartFile file) 
    // throws IOException, CsvValidationException {
    //     List<String[]> dataList = new ArrayList<>();
    //     int lineNumber = 0;
    //     CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
    //     try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
    //         String[] line;
            
    //         while ((line = reader.readNext()) != null) {
    //             if (lineNumber != 0) { 
    //                 dataList.add(line);
    //             }
    //             for(int i=0; i<line.length; i++) {
    //                 System.out.println(line[i]);
    //             }
    //             lineNumber++;
    //         }
    //     }
        
    //     return dataList;
    // }

    public static Date getDateFormatOfFrenchDate(String frenchDate) 
    throws Exception {
        frenchDate=frenchDate.replace("/", "-");
        String[] listeSplit=frenchDate.split("-");
        if(listeSplit.length!=3) {
            throw new Exception("Format de date invalide");
        }
        return Date.valueOf(listeSplit[2]+"-"+listeSplit[1]+"-"+listeSplit[0]);
    }
}
