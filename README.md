# Taller #6: TDD — Kata del Tres en Raya (Tic-Tac-Toe)

Implementación de la **Kata del Tres en Raya** aplicando la metodología **Test Driven Development (TDD)** con **Java 17**, **Maven** y **JUnit 5**. El proyecto sigue el ciclo *Red → Green → Refactor*: primero se escriben las pruebas que fallan, luego el código mínimo para pasarlas y finalmente se refactoriza manteniendo todas las pruebas en verde.

---

## Tabla de contenido

1. [Descripción general](#1-descripción-general)
2. [Requerimientos de la Kata](#2-requerimientos-de-la-kata)
3. [Estructura de carpetas y archivos](#3-estructura-de-carpetas-y-archivos)
4. [Instrucciones para clonar y ejecutar las pruebas](#4-instrucciones-para-clonar-y-ejecutar-las-pruebas)
5. [Prompts utilizados para la generación de pruebas con IA](#5-prompts-utilizados-para-la-generación-de-pruebas-con-ia)
6. [Comparación: pruebas TDD manuales vs. pruebas generadas por IA](#6-comparación-pruebas-tdd-manuales-vs-pruebas-generadas-por-ia)
7. [Entrega del PDF explicativo](#7-entrega-del-pdf-explicativo)
8. [Tecnologías utilizadas](#8-tecnologías-utilizadas)
9. [Autor](#9-autor)

---

## 1. Descripción general

El objetivo del taller es resolver la clásica **Kata del Tres en Raya** guiando todo el desarrollo mediante pruebas unitarias escritas **antes** que el código de producción. El proyecto demuestra:

- El dominio del ciclo **TDD** (Red-Green-Refactor).
- El uso de **JUnit 5** (anotaciones, aserciones, `@Nested`, `@DisplayName`).
- El manejo de casos borde: coordenadas fuera de rango, celdas ocupadas y validación de turnos.
- La detección de victoria por líneas **horizontales, verticales y diagonales**.
- La comparación entre pruebas escritas manualmente y pruebas generadas con **Inteligencia Artificial**.

---

## 2. Requerimientos de la Kata

| # | Requerimiento | Descripción |
|---|---------------|-------------|
| R1 | **Eje X/Y fuera de rango** | Toda jugada con coordenada menor que `0` o mayor o igual a `3` debe ser rechazada. |
| R2 | **Posición ocupada** | No se puede jugar en una celda que ya contiene un símbolo. |
| R3 | **Alternancia de jugadores** | El jugador `'X'` inicia siempre. Tras cada jugada válida el turno alterna entre `'X'` y `'+'`. |
| R4 | **Victoria horizontal** | Un jugador gana si completa una fila entera con su símbolo. |
| R5 | **Victoria vertical** | Un jugador gana si completa una columna entera con su símbolo. |
| R6 | **Victoria diagonal** | Un jugador gana si completa la diagonal principal o la diagonal inversa. |
| R7 | **Fin de partida** | Una vez existe un ganador (o el tablero está lleno), no se permiten más jugadas. |

**Reglas de negocio adicionales**

- Tablero de `3 x 3`.
- Símbolos de jugador: `'X'` y `'+'`.
- Una jugada inválida no consume el turno ni modifica el tablero.
- Si el tablero se llena sin ganador, el resultado es **empate**.

---

## 3. Estructura de carpetas y archivos

```text
Taller-6-TDD/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── taller
    │               └── tictactoe
    │                   └── TicTacToe.java
    └── test
        └── java
            └── com
                └── taller
                    └── tictactoe
                        └── TicTacToeTest.java
```

| Archivo | Propósito |
|---------|-----------|
| `pom.xml` | Configuración Maven: dependencias (JUnit 5), plugins (surefire, jar) y metadatos del proyecto. |
| `TicTacToe.java` | Código de producción con la lógica del juego. |
| `TicTacToeTest.java` | Suite de pruebas unitarias organizada por requerimiento. |

---

## 4. Instrucciones para clonar y ejecutar las pruebas

### Prerrequisitos

- **JDK 17** o superior (`java -version`).
- **Apache Maven 3.8+** (`mvn -version`).
- **Git** (`git --version`).

### Paso a paso

**1. Clonar el repositorio**

```bash
git clone https://github.com/<tu-usuario>/Taller-6-TDD.git
cd Taller-6-TDD
```

**2. Compilar el proyecto**

```bash
mvn clean compile
```

**3. Ejecutar las pruebas unitarias**

```bash
mvn test
```

**4. (Opcional) Empaquetar el JAR y ejecutar**

```bash
mvn clean package
java -jar target/tres-en-raya-tdd.jar
```

**5. (Opcional) Generar el reporte HTML de pruebas**

```bash
mvn test surefire-report:report
```

El reporte se genera en `target/site/surefire-report.html`.

> **Resultado esperado:** todas las pruebas deben aparecer en verde (`BUILD SUCCESS`).

---

## 5. Prompts utilizados para la generación de pruebas con IA

A continuación se documentan los prompts empleados para generar pruebas unitarias con Inteligencia Artificial (ChatGPT / GitHub Copilot), así como el resultado obtenido.

### Prompt 1 — Contexto del proyecto

```text
Actúa como un desarrollador Java senior experto en TDD.
Estoy resolviendo la Kata del Tres en Raya en Java 17 con Maven y JUnit 5.
Genera pruebas unitarias siguiendo el ciclo Red-Green-Refactor.
El tablero es de 3x3, los jugadores son 'X' y '+', y 'X' siempre inicia.
```

### Prompt 2 — Casos borde (fuera de rango y posición ocupada)

```text
Genera pruebas JUnit 5 que verifiquen que:
1. Una jugada con X o Y menor que 0 es rechazada.
2. Una jugada con X o Y mayor o igual a 3 es rechazada.
3. No se puede jugar en una posición ya ocupada.
4. Una jugada inválida no cambia el turno del jugador.
Usa aserciones assertTrue/assertFalse y nombres de test descriptivos.
```

### Prompt 3 — Alternancia de jugadores

```text
Genera pruebas que verifiquen la alternancia de turnos:
el jugador 'X' inicia, luego juega '+', luego 'X' y así sucesivamente.
Cada prueba debe comprobar currentPlayer tras una jugada válida.
```

### Prompt 4 — Condiciones de victoria

```text
Genera pruebas para las condiciones de victoria en el Tres en Raya:
- Línea horizontal (cualquier fila).
- Línea vertical (cualquier columna).
- Diagonal principal (0,0 - 1,1 - 2,2).
- Diagonal inversa (0,2 - 1,1 - 2,0).
Verifica tanto hasWinner() como getWinner() y el estado isFinished().
```

### Prompt 5 — Refinamiento de la suite

```text
Reorganiza todas las pruebas usando la anotación @Nested de JUnit 5
agrupadas por requerimiento, con @DisplayName en español.
Agrega un escenario adicional de empate (tablero lleno sin ganador).
```

> **Nota:** los prompts se conservan como evidencia del proceso y se incluyen en el PDF explicativo del punto 7.

---

## 6. Comparación: pruebas TDD manuales vs. pruebas generadas por IA

| Criterio | Pruebas TDD manuales | Pruebas generadas por IA |
|----------|----------------------|--------------------------|
| **Velocidad de escritura** | Mayor tiempo; requiere pensar cada caso. | Muy rápida; genera decenas de casos en segundos. |
| **Cobertura** | Centrada en los casos que el desarrollador imagina. | Amplia; suele incluir casos borde no obvios. |
| **Comprensión del código** | Alta: el autor domina cada línea. | Media: puede requerir revisión para entender la intención. |
| **Precisión de aserciones** | Ajustada exactamente al comportamiento esperado. | Puede asumir comportamientos incorrectos o inventar APIs. |
| **Mantenibilidad** | Nombres y estructura coherentes con el dominio. | Estructura uniforme, a veces verbosa o redundante. |
| **Ciclo Red-Green-Refactor** | Se respeta estrictamente. | Tiende a generar código y pruebas juntos; el ciclo se diluye. |
| **Detección de errores reales** | Alta: prueba el comportamiento definido por el desarrollador. | Variable: puede generar "pruebas verdes" que no validan nada relevante. |

### Conclusión

La **IA acelera** la creación de la suite y ayuda a descubrir casos borde, pero las pruebas **deben revisarse y adaptarse** manualmente para que reflejen fielmente las reglas del negocio. El mejor resultado se obtiene **combinando ambas**: la IA como borrador y el juicio del desarrollador como filtro, dentro del ciclo TDD.

---

## 7. Entrega del PDF explicativo

Como parte de la entrega del taller se debe adjuntar un **PDF explicativo** que contenga:

1. **Portada** con nombre del taller, integrantes y fecha.
2. **Descripción del ciclo TDD** aplicado paso a paso (Red-Green-Refactor).
3. **Capturas del proceso**: pruebas fallando (Red) y pruebas pasando (Green).
4. **Listado de los prompts** usados con la IA (sección 5 de este README).
5. **Análisis comparativo** entre pruebas manuales y generadas por IA (sección 6).
6. **Conclusiones** y aprendizajes del equipo.
7. **Enlace al repositorio público** de GitHub de este proyecto.

> **Formato de entrega:** PDF, nombrado como `Taller6_TDD_<Apellidos>.pdf`, subido a la plataforma del curso antes de la fecha límite.

---

## 8. Tecnologías utilizadas

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 17+ | Lenguaje de programación |
| Maven | 3.8+ | Gestión de dependencias y build |
| JUnit 5 | 5.10.2 | Framework de pruebas unitarias |
| Git | 2.x | Control de versiones |

---

## 9. Autor

**Nombre:** _Tu Nombre_
**Curso:** _Nombre del curso_
**Institución:** _Nombre de la institución_
**Fecha:** 2026

---

<p align="center">Proyecto desarrollado aplicando Test Driven Development (TDD) 🧪</p>
