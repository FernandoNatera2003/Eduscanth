# EduScan: Sistema de Acceso Escolar

EduScan es una aplicación de escritorio desarrollada en Java con Maven para la gestión y control de acceso escolar. Permite a administrativos y docentes gestionar alumnos, grupos, materias, laboratorios y credenciales de acceso de manera eficiente y segura.

## Características principales
- Gestión de alumnos, maestros y administrativos
- Asignación de materias y grupos
- Control de acceso a laboratorios
- Exportación de bases de datos
- Interfaz gráfica intuitiva (Swing)
- Integración con bases de datos MySQL

## Requisitos
- Java 8 o superior
- Maven 3.6+
- MySQL Server

## Instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/FernandoNatera2003/sistema-acceso-escolar.git
   ```
2. Entra al directorio del proyecto:
   ```bash
   cd sistema-acceso-escolar
   ```
3. Compila el proyecto con Maven:
   ```bash
   mvn clean install
   ```
4. Configura la base de datos MySQL usando los scripts en `src/main/resources/`.

## Uso
- Ejecuta la aplicación desde tu IDE favorito o usando Maven:
  ```bash
  mvn exec:java -Dexec.mainClass="Administrativo.Principal"
  ```
- Accede con las credenciales proporcionadas por el administrador.

## Estructura del proyecto
- `src/main/java/Administrativo/`: Lógica y formularios administrativos
- `src/main/java/Interfaces/`: Interfaces gráficas y utilidades
- `src/main/resources/`: Scripts SQL, imágenes y recursos

## Capturas de pantalla

A continuación se muestran algunas vistas de la aplicación:

### Vista de Inicio de Sesión
![Inicio de sesión](EduScan/doc/Inicio-sesion.png)

### Panel principal Administrativo
![Panel administrativo](EduScan/doc/Barra_tareas.png)   

### Registro de Profesores
![Registro de profesores](EduScan/doc/Registro-profesores.png)

### Registro de Materias
![Registro de materias](EduScan/doc/Registro-materias.png)

### Registro de Laboratorios
![Registro de laboratorios](EduScan/doc/Registro-laboratorios.png)

### Registro de Grupos
![Registro de grupos](EduScan/doc/Registro-grupos.png)

### Registro de Carreras
![Registro de carreras](EduScan/doc/Registro-carreras.png)

### Asignacion de Materias y Grupos
![Asignacion de materias y grupos](EduScan/doc/Asignacion-materia-grupo.png)

### Creacion Bitacora
![Creacion de pase de lista](EduScan/doc/Creacion-bitacora.png)

### Registro de Alumnos
![Registro de Alumnos](EduScan/doc/bitacora-alumnos.png)






> Coloca tus imágenes en la carpeta `docs/` y reemplaza los nombres de archivo por los correspondientes a tus capturas.

## Créditos
Desarrollado por Fernando Natera y colaboradores.

## Licencia
Este proyecto es de uso académico. Para otros usos, contactar al autor.
