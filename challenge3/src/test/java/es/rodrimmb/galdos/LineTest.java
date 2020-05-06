package es.rodrimmb.galdos;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void validateWord1() {
        String lineStr = "Mi primer hijo--decía--nació cuando vino la tropa carlista";

        Line line = new Line(lineStr);

        assertThat(line.value(), is("mi primer hijo  decía  nació cuando vino la tropa carlista"));
        assertThat(line.getWords(), is(asList("primer", "hijo",  "decía",  "nació", "cuando", "vino", "tropa", "carlista")));
    }

    @Test
    public void validateWord2() {
        String lineStr = "«D. Plácido, ¿tiene usted pana azul?».--«¡Pana azul!";

        Line line = new Line(lineStr);

        assertThat(line.value(), is(" d  plácido   tiene usted pana azul       pana azul "));
        assertThat(line.getWords(), is(asList("plácido", "tiene", "usted", "pana", "azul", "pana", "azul")));
    }

    @Test
    public void validateWord3() {
        String lineStr = "--¿Vive aquí--le preguntó--el Sr. de EstupiÑá?";

        Line line = new Line(lineStr);

        assertThat(line.value(), is("   vive aquí  le preguntó  el sr  de estupiñá "));
        assertThat(line.getWords(), is(asList("vive", "aquí", "preguntó", "estupiñá")));
    }

    @Test
    public void validateWord4() {
        String lineStr = "--¿Vive aqüí--le preguntó--el Sr. de EstupiÑá?";

        Line line = new Line(lineStr);

        assertThat(line.value(), is("   vive aqüí  le preguntó  el sr  de estupiñá "));
        assertThat(line.getWords(), is(asList("vive", "aqüí", "preguntó", "estupiñá")));
    }

    @Test
    public void validateWordOfLines() {
        String lineStr1 = "Release Date: November 5, 2005 [EBook #17013]";
        String lineStr2 = "[Last updated on December 21, 2019]";
        String lineStr3 = "Mi primer hijo--decía--nació cuando vino la tropa carlista";
        String lineStr4 = "«D. Plácido, ¿tiene usted pana azul?».--«¡Pana azul!";
        String lineStr5 = "--¿Vive aquí--le preguntó--el Sr. de Estupiñá?";
        String lineStr6 = "--¡Qué poca vergüenza!";
        String lineStr7 = "Pues apechuguemos con las _novedades_» dijo Isabel a su marido";
        String lineStr8 = "Barbarita declaraba riendo que con estos teje-manejes se había vuelto, sin saberlo, una doña";
        String lineStr9 = "Beatriz Galindo";
        String lineStr10 = "¡Pero las niñas!... ¡Y con estas modas de ahora y este suponer!... ¿Viste la pieza de merino ";
        String lineStr11 = "azul?, pues no fue bastante";

        Line line1 = new Line(lineStr1);
        Line line2 = new Line(lineStr2);
        Line line3 = new Line(lineStr3);
        Line line4 = new Line(lineStr4);
        Line line5 = new Line(lineStr5);
        Line line6 = new Line(lineStr6);
        Line line7 = new Line(lineStr7);
        Line line8 = new Line(lineStr8);
        Line line9 = new Line(lineStr9);
        Line line10 = new Line(lineStr10);
        Line line11 = new Line(lineStr11);

        assertThat(line1.value(), is("release date  november          ebook        "));
        assertThat(line2.value(), is(" last updated on december          "));
        assertThat(line3.value(), is("mi primer hijo  decía  nació cuando vino la tropa carlista"));
        assertThat(line4.value(), is(" d  plácido   tiene usted pana azul       pana azul "));
        assertThat(line5.value(), is("   vive aquí  le preguntó  el sr  de estupiñá "));
        assertThat(line6.value(), is("   qué poca vergüenza "));
        assertThat(line7.value(), is("pues apechuguemos con las  novedades   dijo isabel a su marido"));
        assertThat(line8.value(), is("barbarita declaraba riendo que con estos teje manejes se había vuelto  sin saberlo  una doña"));
        assertThat(line9.value(), is("beatriz galindo"));
        assertThat(line10.value(), is(" pero las niñas      y con estas modas de ahora y este suponer      viste la pieza de merino "));
        assertThat(line11.value(), is("azul   pues no fue bastante"));
    }
}