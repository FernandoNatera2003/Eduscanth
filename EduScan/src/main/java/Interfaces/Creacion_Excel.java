package Interfaces;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;

public class Creacion_Excel {
    private Workbook libro;
    private Sheet hoja;
    private String[] Datos_1P;
    private String[] Datos_2P;

    private void Crear_Libro_Hoja() {
        this.libro = new XSSFWorkbook();
        this.hoja = this.libro.createSheet("HojaTrabajo");
    }
    private void Primera_Parte2() {
        Row fila = this.hoja.createRow(0);

        // Insertar texto "Logo Edomex"
        Cell celda1 = fila.createCell(0);
        //celda1.setCellValue("Logo Edomex");
        celda1.setCellStyle(crearEstiloEncabezadoPrincipal());

        // Insertar imagen para "Logo Edomex"
        try {
            insertarImagenEnCelda(0, 0, Registro_Alumnos.class.getResource("/Imagenes/LE2.png").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fusionar celdas y agregar título
        Cell celda2 = fila.createCell(1);
        for (int col = 1; col <= 5; col++) {
            Cell celdaFusionada = fila.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloEncabezadoPrincipal());
        }
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 1, 5)); // Merge cells for title
        celda2.setCellValue("Registro de Uso de Talleres y Laboratorios FO-TESH-69");
        celda2.setCellStyle(crearEstiloEncabezadoPrincipal());

        // Insertar texto "Logo TESH"
        Cell celda3 = fila.createCell(6);
        //celda3.setCellValue("Logo TESH");
        celda3.setCellStyle(crearEstiloEncabezadoPrincipal());

        // Insertar imagen para "Logo TESH"
        try {
            insertarImagenEnCelda(0, 6, Registro_Alumnos.class.getResource("/Imagenes/LT2.png").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void Primera_Parte() {
        Row fila = this.hoja.createRow(0);

        // Insertar texto "Logo Edomex"
        Cell celda1 = fila.createCell(0);
        celda1.setCellStyle(crearEstiloEncabezadoPrincipal());

        // Insertar imagen para "Logo Edomex"
        try {
            insertarImagenEnCelda(0, 0, "/Imagenes/LE2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fusionar celdas y agregar título
        Cell celda2 = fila.createCell(1);
        for (int col = 1; col <= 5; col++) {
            Cell celdaFusionada = fila.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloEncabezadoPrincipal());
        }
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 1, 5)); // Fusionar celdas para el título
        celda2.setCellValue("Registro de Uso de Talleres y Laboratorios FO-TESH-69");
        celda2.setCellStyle(crearEstiloEncabezadoPrincipal());

        // Insertar texto "Logo TESH"
        Cell celda3 = fila.createCell(6);
        celda3.setCellStyle(crearEstiloEncabezadoPrincipal());

        // Insertar imagen para "Logo TESH"
        try {
            insertarImagenEnCelda(0, 6, "/Imagenes/LT2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertarImagenEnCelda2(int fila, int columna, String rutaImagen) throws IOException {
        FileInputStream fis = new FileInputStream(rutaImagen);
        byte[] bytesImagen = fis.readAllBytes();
        fis.close();

        // Agregar la imagen al workbook
        int pictureIdx = this.libro.addPicture(bytesImagen, Workbook.PICTURE_TYPE_JPEG);

        // Crear dibujo y anclar la imagen
        Drawing<?> drawing = this.hoja.createDrawingPatriarch();
        CreationHelper helper = this.libro.getCreationHelper();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(columna);  // Columna de inicio
        anchor.setRow1(fila);     // Fila de inicio
        anchor.setCol2(columna + 1); // Columna de fin (opcional, para ajustar tamaño)
        anchor.setRow2(fila + 1);    // Fila de fin (opcional, para ajustar tamaño)

        // Crear la imagen en la hoja
        drawing.createPicture(anchor, pictureIdx);
    }

    private void insertarImagenEnCelda(int fila, int columna, String resourcePath) throws IOException {
        InputStream inputStream = Registro_Alumnos.class.getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el recurso: " + resourcePath);
        }

        // Leer la imagen
        byte[] bytes = inputStream.readAllBytes();
        int pictureIdx = this.libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        inputStream.close();

        CreationHelper helper = this.libro.getCreationHelper();
        Drawing<?> drawing = this.hoja.createDrawingPatriarch();

        // Crear un anclaje basado en la celda
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(columna);
        anchor.setRow1(fila);
        anchor.setCol2(columna + 1); // Una celda de ancho
        anchor.setRow2(fila + 1);   // Una celda de alto

        // Insertar la imagen
        Picture pict = drawing.createPicture(anchor, pictureIdx);

        // Ajustar la imagen al tamaño exacto de la celda
        pict.resize(1.0); // El parámetro 1.0 asegura que la imagen ocupa exactamente el espacio definido por el anclaje
    }



    public void Recopilacion_D1P(String Profesor, String Asignatura, String Fecha, String HE, String HS, String H) {
        String[] Datos = {Profesor, Asignatura, Fecha, HE, HS, H};
        this.Datos_1P = Datos;
    }

    private void Segunda_Parte() {
        String[] Datos = {"Profesor", "Asignatura", "Fecha", "Hora Entrada", "Hora Salida", "Horas"};
        Row fila = this.hoja.createRow(1);
        Row fila2 = this.hoja.createRow(2);
        for (int i = 0; i < Datos.length-1; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellValue(Datos[i]);
            celda.setCellStyle(crearEstiloEncabezado());
        }

        for (int col = 5; col <= 6; col++) {
            Cell celdaFusionada = fila.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloEncabezado());
        }
        Cell celdan = fila.createCell(5);
        celdan.setCellValue("Horas");
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 5, 6)); // Merge cells for title
        celdan.setCellStyle(crearEstiloEncabezado());



        for (int i = 0; i < this.Datos_1P.length-1; i++) {
            Cell celda = fila2.createCell(i);
            celda.setCellValue(this.Datos_1P[i]);
            celda.setCellStyle(crearEstiloContenido());
        }
        for (int col = 5; col <= 6; col++) {
            Cell celdaFusionada = fila2.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloContenido());
        }
        Cell celdan2 = fila2.createCell(5);
        celdan2.setCellValue(this.Datos_1P[this.Datos_2P.length-1]);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 5, 6)); // Merge cells for title
        celdan2.setCellStyle(crearEstiloContenido());


    }

    public void Recopilacion_D2P(String Grupo, String Semestre, String Carrera, String Laboratorio, String Nombre, String num) {
        String[] Datos = {Grupo, Semestre, Carrera, Laboratorio, Nombre, num};
        this.Datos_2P = Datos;
    }

    private void Tercera_Parte() {
        String[] Datos = {"Grupo", "Semestre", "Carrera", "Laboratorio", "Nombre Práctica", "No.Práctica"};
        Row fila = this.hoja.createRow(3);
        Row fila2 = this.hoja.createRow(4);
        for (int i = 0; i < Datos.length-1; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellValue(Datos[i]);
            celda.setCellStyle(crearEstiloEncabezado());
        }
        for (int col = 5; col <= 6; col++) {
            Cell celdaFusionada = fila.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloEncabezado());
        }
        Cell celdan = fila.createCell(5);
        celdan.setCellValue("No.Práctica");
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 5, 6)); // Merge cells for title
        celdan.setCellStyle(crearEstiloEncabezado());



        for (int i = 0; i < this.Datos_2P.length-1; i++) {
            Cell celda = fila2.createCell(i);
            celda.setCellValue(this.Datos_2P[i]);
            celda.setCellStyle(crearEstiloContenido());
        }
        for (int col = 5; col <= 6; col++) {
            Cell celdaFusionada = fila2.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloContenido());
        }
        Cell celdan2 = fila2.createCell(5);
        celdan2.setCellValue(this.Datos_2P[this.Datos_2P.length-1]);
        hoja.addMergedRegion(new CellRangeAddress(4, 4, 5, 6)); // Merge cells for title
        celdan2.setCellStyle(crearEstiloContenido());

    }

    private void Cuarta_Parte() {
        String[] Datos = {"No", "Nombre Alumno", "Apellido Paterno", "Apellido Materno", "Matricula", "Numero Equipo","Observacion"};
        Row fila = this.hoja.createRow(7);
        for (int i = 0; i < Datos.length; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellValue(Datos[i]);
            celda.setCellStyle(crearEstiloEncabezado());
        }
    }
    private void Quinta_Parte() {
        Row fila = this.hoja.createRow(5);
        for (int col = 0; col <= 6; col++) {
            Cell celdaFusionada = fila.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloEncabezado());
        }
        Cell celdan = fila.createCell(0);
        celdan.setCellValue("Observacion Docente");
        hoja.addMergedRegion(new CellRangeAddress(5, 5, 0, 6)); // Merge cells for title
        celdan.setCellStyle(crearEstiloEncabezado());

        }
    private void sexta_Parte(String observacion){
            Row fila = this.hoja.createRow(6);
        for (int col = 0; col <= 6; col++) {
            Cell celdaFusionada = fila.createCell(col);
            celdaFusionada.setCellStyle(crearEstiloContenido());
        }
            Cell celdan = fila.createCell(0);
            celdan.setCellValue(observacion);
            hoja.addMergedRegion(new CellRangeAddress(6, 6, 0, 6)); // Merge cells for title
            celdan.setCellStyle(crearEstiloContenido());

        }


    public void Informacion_Alumnos(JTable Alumnos) {
        int pos = 8;
        for (int i = 1; i < Alumnos.getRowCount(); i++) {
            Row fila = hoja.createRow(pos);
            for (int j = 0; j < Alumnos.getColumnCount(); j++) {
                Cell celda = fila.createCell(j);
                String valor = Alumnos.getValueAt(i, j).toString();
                if (valor != null) {
                    celda.setCellValue(valor);
                    celda.setCellStyle(crearEstiloContenido());
                }
            }
            pos++;
        }
    }

    private void Generar_Archivos(String nombre) {
        try (FileOutputStream fileOut = new FileOutputStream(nombre + ".xlsx")) {
            for (int i = 0; i < hoja.getRow(0).getLastCellNum(); i++) {
                hoja.autoSizeColumn(i);
            }
            this.libro.write(fileOut);
            JOptionPane.showMessageDialog(null, "El archivo se ha creado con éxito.", "Mensaje de Exito", JOptionPane.INFORMATION_MESSAGE);
            this.libro.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Mensaje de Error", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }
    private void Generar_Archi(String nombre, String laboratorio) {
        // Obtener la ruta del directorio actual
        String rutaBase = System.getProperty("user.dir");
        String rutaCarpeta = rutaBase + File.separator + laboratorio;

        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            if (!carpeta.mkdirs()) {
                JOptionPane.showMessageDialog(null, "No se pudo crear la carpeta.", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si no se pudo crear la carpeta
            }
        }

        String rutaArchivo = rutaCarpeta + File.separator + nombre + ".xlsx";

        try (FileOutputStream fileOut = new FileOutputStream(rutaArchivo)) {
            // Ajustar el tamaño de las columnas automáticamente
            for (int i = 0; i < hoja.getRow(0).getLastCellNum(); i++) {
                hoja.autoSizeColumn(i);
            }

            this.libro.write(fileOut);
            JOptionPane.showMessageDialog(null, "El archivo se ha creado con éxito en: " + rutaArchivo, "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.libro.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    private CellStyle crearEstiloEncabezadoPrincipal() {
        CellStyle estilo = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 20); // Tamaño de fuente grande
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        estilo.setFont(font);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Asegurar bordes negros en todas las direcciones
        estilo.setBorderBottom(BorderStyle.THICK);
        estilo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderTop(BorderStyle.THICK);
        estilo.setTopBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderRight(BorderStyle.THICK);
        estilo.setRightBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderLeft(BorderStyle.THICK);
        estilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        return estilo;
    }

    private CellStyle crearEstiloEncabezado() {
        CellStyle estilo = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // Tamaño ajustado para encabezados secundarios
        font.setColor(IndexedColors.WHITE.getIndex());
        estilo.setFont(font);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Asegurar bordes negros en todas las direcciones
        estilo.setBorderBottom(BorderStyle.THICK);
        estilo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderTop(BorderStyle.THICK);
        estilo.setTopBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderRight(BorderStyle.THICK);
        estilo.setRightBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderLeft(BorderStyle.THICK);
        estilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        return estilo;
    }

    private CellStyle crearEstiloContenido() {
        CellStyle estilo = libro.createCellStyle();
        Font font = libro.createFont();
        font.setFontHeightInPoints((short) 12); // Tamaño para el contenido
        estilo.setFont(font);
        estilo.setAlignment(HorizontalAlignment.LEFT);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Asegurar bordes negros en todas las direcciones
        estilo.setBorderBottom(BorderStyle.THICK);
        estilo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderTop(BorderStyle.THICK);
        estilo.setTopBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderRight(BorderStyle.THICK);
        estilo.setRightBorderColor(IndexedColors.BLACK.getIndex());
        estilo.setBorderLeft(BorderStyle.THICK);
        estilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        return estilo;
    }

    public void Crear_Excel(JTable Alumnos, String Nombre,String laboratorio,String obs) {
        this.Crear_Libro_Hoja();
        this.Primera_Parte();
        this.Segunda_Parte();
        this.Tercera_Parte();
        this.Quinta_Parte();
        this.sexta_Parte(obs);
        this.Cuarta_Parte();
        this.Informacion_Alumnos(Alumnos);

        this.Generar_Archi(Nombre,laboratorio);
    }
}