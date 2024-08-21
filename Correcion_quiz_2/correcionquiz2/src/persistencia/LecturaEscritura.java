package persistencia;

import logica.Joven;
import logica.Nomina;
import logica.Tarjeta;
import logica.Visa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LecturaEscritura {
    private BufferedReader reader;
    private BufferedWriter writer;
    private String filename;
    private List<Tarjeta> list;

    public LecturaEscritura(String filename) {
        this.filename = filename;
        list=new ArrayList<>();
    }

    public List<Tarjeta> getList() {
        return list;
    }

    public void setList(List<Tarjeta> list) {
        this.list = list;
    }

    public void leerTarjetas(){
        try{
            reader = new BufferedReader(new FileReader("./archivos/"+this.filename+".txt"));
            String line = reader.readLine();
            while(line!=null){
                String[] datos = line.split(";");
                switch (datos[2]){
                    case "Joven" -> list.add(new Joven(datos[0],Double.parseDouble(datos[1]),this.filename));
                    case "Nomina" -> list.add(new Nomina(datos[0],Double.parseDouble(datos[1]),this.filename));
                    case "Visa" -> list.add(new Visa(datos[0],Double.parseDouble(datos[1]),this.filename));
                    default -> {}
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void escribirTarjetas(){
        try{
            writer = new BufferedWriter(new FileWriter("./archivos/reporte.txt"));
            for(Tarjeta t: list){
                writer.write(t.toString()+"\n");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
