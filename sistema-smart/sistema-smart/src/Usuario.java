public class Usuario {
    public static void main(String[] args) throws Exception {
        
        SmartTv smartTv = new SmartTv();

        smartTv.ligar();
        System.out.println("Novo status -> TV ligada ?" + smartTv.ligada);

        smartTv.mudarCanal(15);
        System.out.println("Canal Atual : " + smartTv.canal);

        smartTv.diminuirVolume();
        System.out.println("Volume Atual : " + smartTv.volume);
        
    }
}
