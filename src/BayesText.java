
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class BayesText {

    public static void main(String[] args) {

        bacaData("E:\\KULIAH\\SEMESTER 6\\PENAMBANGAN DATA\\data.txt");
//        tampilDataIBayes();
        System.out.println("TABEL PROBABILITAS");

        System.out.println("===================================================="
                + "============================================================="
                + "====");

        System.out.println("\t\tOUTLOOK\t\t\tTEMPERATURE\t\tHUMIDITY\t\tWINDY\t\tPLAY");
        System.out.println("\t\tYES\tNO\t\tYES\tNO\t\tYES\tNO\t\tYES\tNO\tYES\tNO");

        System.out.println("Sunny\t\t" + SunnyYes() + "\t" + SunnyNo() + "\tHot\t" + HotYes()
                + "\t" + HotNo() + "\tHigh\t" + HighYes() + "\t" + HighNo() + "\tTrue\t"
                + TrueYes() + "\t" + TrueNo() + "\t" + PlayYes() + "\t" + PlayNo());

        System.out.println("Overcast\t" + OvercastYes() + "\t" + OvercastNo() + "\tMild\t"
                + MildYes() + "\t" + MildNo() + "\tNormal\t" + NormalYes() + "\t" + NormalNo()
                + "\tFalse\t" + FalseYes() + "\t" + FalseNo());

        System.out.println("Rainy\t\t" + RainyYes() + "\t" + RainyNo() + "\tCool\t" + CoolYes()
                + "\t" + CoolNo());

        System.out.println("===================================================="
                + "============================================================="
                + "====");

        System.out.println("Sunny\t\t" + ProbabilitasSunnyYes() + "\t" + ProbabilitasSunnyNo()
                + "\tHot\t" + ProbabilitasHotYes() + "\t" + ProbabilitasHotNo() + "\tHigh\t"
                + ProbabilitasHighYes() + "\t" + ProbabilitasHighNo() + "\tTrue\t"
                + ProbabilitasTrueYes() + "\t" + ProbabilitasTrueNo() + "\t" + ProbabilitasYes()
                + "\t" + ProbabilitasNo());

        System.out.println("Overcast\t" + ProbabilitasOvercastYes() + "\t" + ProbabilitasOvercastNo()
                + "\tMild\t" + ProbabilitasMildYes() + "\t" + ProbabilitasMildNo()
                + "\tNormal\t" + ProbabilitasNormalYes() + "\t" + ProbabilitasNormalNo()
                + "\tFalse\t" + ProbabilitasFalseYes() + "\t" + ProbabilitasFalseNo());

        System.out.println("Rainy\t\t" + ProbabilitasRainyYes() + "\t" + ProbabilitasRainyNo()
                + "\tCool\t" + ProbabilitasCoolYes() + "\t" + ProbabilitasCoolNo());

        System.out.println("===================================================="
                + "============================================================="
                + "====");

        Scanner input = new Scanner(System.in);
        String outlook, temperature, humidity, windy;
        System.out.println("\nPERHITUNGAN BAYES");
        System.out.print("OUTLOOK (SUNNY, OVERCAST, RAINY)\t: ");
        outlook = input.next().toUpperCase();
        System.out.print("TEMPERATURE (HOT, MILD, COOL)\t\t: ");
        temperature = input.next().toUpperCase();
        System.out.print("HUMIDITY (HIGH, NORMAL)\t\t\t: ");
        humidity = input.next().toUpperCase();
        System.out.print("WINDY (TRUE, FALSE)\t\t\t: ");
        windy = input.next().toUpperCase();

        PerhitunganBayes(outlook, temperature, humidity, windy);
    }

    private static ArrayList<Data> dataBayes;

    public static ArrayList<Data> bacaData(String path) {
        dataBayes = new ArrayList();
        String dataString = null, dataArray[];
        int data, baris = 0;
        FileInputStream inputStream;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            inputStream = new FileInputStream(path);
            while ((data = inputStream.read()) != -1) {
                stringBuilder.append((char) data);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (("" + (stringBuilder.charAt(i))).equals("\n")) {
                if (baris == 0) {
                    dataString = "";
                } else {
                    dataArray = dataString.split(",");
                    dataString = "";
                    dataBayes.add(new Data(dataArray[0], dataArray[1], dataArray[2],
                            dataArray[3], dataArray[4]));
                }
                baris++;
            } else {
                dataString += "" + stringBuilder.charAt(i);
            }
        }
        return dataBayes;
    }

    public static void tampilDataIBayes() {
        System.out.println("OUTLOOK\t\tTEMPERATURE\tHUMIDITY\tWINDY\tPLAY");
        for (int i = 0; i < dataBayes.size(); i++) {
            System.out.println(dataBayes.get(i).getOutlook()
                    + "\t\t" + dataBayes.get(i).getTemperature()
                    + "\t\t" + dataBayes.get(i).getHumidity()
                    + "\t\t" + dataBayes.get(i).getWindy()
                    + "\t" + dataBayes.get(i).getPlay());
        }

    }

    private static double PlayYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double PlayNo() {

        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double TotalData() {
        return dataBayes.size();
    }

    private static double SunnyYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getOutlook().equals("SUNNY")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double SunnyNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getOutlook().equalsIgnoreCase("SUNNY")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double OvercastYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getOutlook().equalsIgnoreCase("OVERCAST")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double OvercastNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getOutlook().equalsIgnoreCase("OVERCAST")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double RainyYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getOutlook().equalsIgnoreCase("RAINY")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double RainyNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getOutlook().equalsIgnoreCase("RAINY")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double HotYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getTemperature().equalsIgnoreCase("HOT")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double HotNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getTemperature().equalsIgnoreCase("HOT")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double MildYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getTemperature().equalsIgnoreCase("MILD")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double MildNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getTemperature().equalsIgnoreCase("MILD")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double CoolYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getTemperature().equalsIgnoreCase("COOL")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double CoolNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getTemperature().equalsIgnoreCase("COOL")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double HighYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getHumidity().equalsIgnoreCase("HIGH")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double HighNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getHumidity().equalsIgnoreCase("HIGH")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double NormalYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getHumidity().equalsIgnoreCase("NORMAL")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double NormalNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getHumidity().equalsIgnoreCase("NORMAL")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double TrueYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getWindy().equalsIgnoreCase("TRUE")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double TrueNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getWindy().equalsIgnoreCase("TRUE")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double FalseYes() {
        int yes = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getWindy().equalsIgnoreCase("FALSE")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("YES\r")) {
                yes = yes + 1;
            }
        }
        return yes;
    }

    private static double FalseNo() {
        int no = 0;
        for (int i = 0; i < dataBayes.size(); i++) {
            if (dataBayes.get(i).getWindy().equalsIgnoreCase("FALSE")
                    && dataBayes.get(i).getPlay().equalsIgnoreCase("NO\r")) {
                no = no + 1;
            }
        }
        return no;
    }

    private static double ProbabilitasYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(PlayYes() / TotalData()));
    }

    private static double ProbabilitasNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(PlayNo() / TotalData()));
    }

    private static double ProbabilitasSunnyYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(SunnyYes() / PlayYes()));
    }

    private static double ProbabilitasSunnyNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(SunnyNo() / PlayNo()));
    }

    private static double ProbabilitasOvercastYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(OvercastYes() / PlayYes()));
    }

    private static double ProbabilitasOvercastNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(OvercastNo() / PlayNo()));
    }

    private static double ProbabilitasRainyYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(RainyYes() / PlayYes()));
    }

    private static double ProbabilitasRainyNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(RainyNo() / PlayNo()));
    }

    private static double ProbabilitasHotYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(HotYes() / PlayYes()));
    }

    private static double ProbabilitasHotNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(HotNo() / PlayNo()));
    }

    private static double ProbabilitasMildYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(MildYes() / PlayYes()));
    }

    private static double ProbabilitasMildNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(MildNo() / PlayNo()));
    }

    private static double ProbabilitasCoolYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(CoolYes() / PlayYes()));
    }

    private static double ProbabilitasCoolNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(CoolNo() / PlayNo()));
    }

    private static double ProbabilitasHighYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(HighYes() / PlayYes()));
    }

    private static double ProbabilitasHighNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(HighNo() / PlayNo()));
    }

    private static double ProbabilitasNormalYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(NormalYes() / PlayYes()));
    }

    private static double ProbabilitasNormalNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(NormalNo() / PlayNo()));
    }

    private static double ProbabilitasTrueYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(TrueYes() / PlayYes()));
    }

    private static double ProbabilitasTrueNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(TrueNo() / PlayNo()));
    }

    private static double ProbabilitasFalseYes() {
        return Double.parseDouble(new DecimalFormat("##.##").format(FalseYes() / PlayYes()));
    }

    private static double ProbabilitasFalseNo() {
        return Double.parseDouble(new DecimalFormat("##.##").format(FalseNo() / PlayNo()));
    }

    public static void PerhitunganBayes(String outlook, String temperature,
            String humidity, String windy) {
        double outlYes = 0, tempYes = 0, humYes = 0, winYes = 0, outlNo = 0,
                tempNo = 0, humNo = 0, winNo = 0, yes = 0, no = 0;
        switch (outlook) {
            case "SUNNY":
                outlYes = ProbabilitasSunnyYes();
                outlNo = ProbabilitasSunnyNo();
                break;
            case "OVERCAST":
                outlYes = ProbabilitasOvercastYes();
                outlNo = ProbabilitasOvercastNo();
                break;
            case "RAINY":
                outlYes = ProbabilitasRainyYes();
                outlNo = ProbabilitasRainyNo();
                break;
            default:
                break;
        }

        switch (temperature) {
            case "HOT":
                tempYes = ProbabilitasHotYes();
                tempNo = ProbabilitasHotNo();
                break;
            case "MILD":
                tempYes = ProbabilitasMildYes();
                tempNo = ProbabilitasMildNo();
                break;
            case "COOL":
                tempYes = ProbabilitasCoolYes();
                tempNo = ProbabilitasCoolNo();
                break;
            default:
                break;
        }

        switch (humidity) {
            case "HIGH":
                humYes = ProbabilitasHighYes();
                humNo = ProbabilitasHighNo();
                break;
            case "NORMAL":
                humYes = ProbabilitasNormalYes();
                humNo = ProbabilitasNormalNo();
                break;
            default:
                break;
        }

        switch (windy) {
            case "TRUE":
                winYes = ProbabilitasHotYes();
                winNo = ProbabilitasHotNo();
                break;
            case "FALSE":
                winYes = ProbabilitasMildYes();
                winNo = ProbabilitasMildNo();
                break;
            default:
                break;
        }

        yes = outlYes + tempYes + humYes + winYes;
        no = outlNo + tempNo + humNo + winNo;
        System.out.println("\nJawaban:\n\tOUTLOOK\tTEMPERATURE\tHUMIDITY\tWINDY\t"
                + "PLAY\tJUMLAH");
        System.out.println("YES\t" + outlYes + "\t" + tempYes + "\t\t" + humYes + "\t\t"
                + winYes + "\t" + ProbabilitasYes() + "\t"
                + Double.parseDouble(new DecimalFormat("##.##").format(yes)));
        System.out.println("NO\t" + outlNo + "\t" + tempNo + "\t\t" + humNo + "\t\t" + winNo
                + "\t" + ProbabilitasNo() + "\t"
                + Double.parseDouble(new DecimalFormat("##.##").format(no)));
        System.out.println("\n===================================================="
                + "============================================================="
                + "====");
        System.out.println("P(YES) = "
                + Double.parseDouble(new DecimalFormat("##.##").format(yes / (yes + no))));
        System.out.println("P(NO) = "
                + Double.parseDouble(new DecimalFormat("##.##").format(no / (yes + no))));

        String jawaban;
        String play;
        if (yes > no) {
            jawaban = "YES";
            play = "diadakan";
        } else {
            jawaban = "NO";
            play = "tidak diadakan";
        }

        System.out.print("===================================================="
                + "============================================================="
                + "====");
        System.out.println("\nKESIMPULAN");
        System.out.println("Jadi jawabanya adalah " + jawaban + ". Artinya jika "
                + "keadaan cuaca dengan outlook " + outlook + ", temperature "
                + temperature + ", humidity " + humidity + ", dan windy \n" + windy
                + " maka circus tersebut " + play);

    }

}
