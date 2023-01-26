package konovalchik.com;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipWithFiles {
    ClassLoader cl = ZipWithFiles.class.getClassLoader();

    @Test
    void parseZipFolder() throws Exception {

        try (
                InputStream is = cl.getResourceAsStream("folder.zip");
                ZipInputStream zis = new ZipInputStream(is);
        ) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("PDF");
                } else if (entry.getName().contains(".xlsx")) {
                    XLS xlsx = new XLS(zis);
                    assertThat(xlsx.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue()).isEqualTo("Last Name");
                } else {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csv = csvReader.readAll();
                    assertThat(csv.get(1)[1]).contains("1.7 Cubic Foot Compact \"Cube\" Office Refrigerators");
                }
            }
        }
    }
}