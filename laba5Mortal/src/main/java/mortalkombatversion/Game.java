package mortalkombatversion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import mortalkombatversion.models.Human;
import mortalkombatversion.models.Result;
import mortalkombatversion.resources.ResourcesManager;
import mortalkombatversion.resources.ResourcesManagerImpl;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Game {
    private final ResourcesManager resources = new ResourcesManagerImpl();

    CharacterAction action = new CharacterAction();
    public Fight fight = new Fight();
    private ArrayList<Result> results = new ArrayList<>();

    public Game() {
        try {
            readFromExcel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void endGameTop(Human human, String name) throws IOException {
        results.add(new Result(name, human.getPoints()));
        results.sort(Comparator.comparing(Result::points).reversed());
        writeToExcel();
    }
    
    public void writeToExcel() throws IOException{
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i=0; i<results.size();i++){
            if (i<10){
                XSSFRow r2 = sheet.createRow(i+1);
                r2.createCell(0).setCellValue(i+1);
                r2.createCell(1).setCellValue(results.get(i).name());
                r2.createCell(2).setCellValue(results.get(i).points());
            }
        }
        File f = new File(resources.getResource("configs/results.xlsx").getFile());
        book.write(new FileOutputStream(f));
        book.close();
    }
    
    public ArrayList<Result> getResults(){
        return this.results;
    }

    public void readFromExcel() throws IOException {
        results = new ArrayList<>();
        try (XSSFWorkbook book = new XSSFWorkbook(resources.getResource("configs/results.xlsx").getFile())) {
            XSSFSheet sh = book.getSheetAt(0);
            for (int i=1; i<=sh.getLastRowNum();i++) {
                results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(),(int)sh.getRow(i).getCell(2).getNumericCellValue()));
            }
        }
    }
}
