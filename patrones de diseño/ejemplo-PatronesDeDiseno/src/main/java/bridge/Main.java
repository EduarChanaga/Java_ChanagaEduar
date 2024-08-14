package bridge;

public class Main {
    public static void main(String[] args){
        Device Televisor = new Televisor();

        RemoteControl controlTelevisor = new ControlRemotoTelevisor(Televisor);


        controlTelevisor.encender();
        controlTelevisor.apagar();




        Device Radio = new Radio();
        RemoteControl controlRadio = new ControlRemotoRadio(Radio);

        controlRadio.encender();
        controlRadio.apagar();
    }
}
