package at.jhorngacher.zerotohero.app.utils;

import at.jhorngacher.zerotohero.app.models.CarbonData;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;

import java.time.Year;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;

public class DataSourceImport {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("carbonU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();

    public void init(){
        try(Reader reader = new InputStreamReader(getClass().getResourceAsStream("/data/owid-co2-data.csv"));
            CSVReader csvReader = new CSVReader(reader)
        ){

            String[] line;
            boolean firstLine = true;
            Integer rowNumber = 0;
            LocalDate creationDate = LocalDate.now();
            tx.begin();

            while((line = csvReader.readNext()) != null){

                // Erste Zeile (=Header) wird übersprungen, diese muss nicht eingelesen werden!
                if(firstLine){
                    firstLine = false;
                    continue;
                }

                if(line[7].isEmpty()){
                    continue;
                }

                Double carbonEmission = Double.parseDouble(line[7]);

                // Einträge mit CO2 Emissionen von 0 können übersprungen werden, diese sind nicht relevant bzw. sind aus Jahren in denen es noch keine Messwerte dazu gab!
                if(carbonEmission == 0){
                    continue;
                }

                CarbonData carbonData = new CarbonData();
                carbonData.setCarbonEmission(carbonEmission);
                carbonData.setAuthor(1);
                carbonData.setCountryName(line[0]);
                carbonData.setCountryCode(line[2]);
                carbonData.setEmissionYear(Year.parse(line[1]));


                if(line[3].isEmpty()){
                    continue;
                }

                Long countryPopulation = Long.parseLong(line[3]);
                carbonData.setCountryPopulation(countryPopulation);

                Double carbonEmissionPerInhabitant = carbonEmission / countryPopulation;
                carbonData.setCarbonEmissionPerInhabitant(carbonEmissionPerInhabitant);

                // Die kumulierten Daten können bei einem ersten richtigen Eintrag durchaus leer sin
                if(line[24].isEmpty()){
                    continue;
                }

                carbonData.setCreationDate(creationDate);

                // Daten in den Persistence Context schreiben ( wird noch im Memory gehalten )
                em.persist(carbonData);
                rowNumber++;

                //carbonDataDAO.save(carbonData);
                if(rowNumber % 100 == 0){
                    System.out.println("100 Zeilen eingelesen!");
                    em.flush();
                    em.clear();
                }


            }

            em.getTransaction().commit();
            System.out.println("CSV import erledigt!");
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
